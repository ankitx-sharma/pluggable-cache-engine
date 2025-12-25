package org.dev.cache.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

import org.dev.cache.eviction.EvictionPolicy;

public class InMemoryCache<K, V> implements Cache<K, V> {
	private final Map<K, CacheEntry<V>> map = new HashMap<>();
	private final PriorityQueue<ExpiryNode<K>> queue = new PriorityQueue<>();
	private final EvictionPolicy<K> evictionPolicy;
	
	private final int capacity;
	private final long defaultTtlMillis;
	private long versionCounter = 0L;
	
	public InMemoryCache(EvictionPolicy<K> evictionPolicy, int capacity, long defaultTtlMillis) {
		if(capacity <= 0) { throw new IllegalArgumentException("Capacity must be more than 0"); }
		if(defaultTtlMillis <= 0) { throw new IllegalArgumentException("DefaultTtlMillis must be more than 0"); }
		
		this.capacity = capacity;
		this.defaultTtlMillis = defaultTtlMillis;
		this.evictionPolicy = Objects.requireNonNull(evictionPolicy, "Eviction Policy can not be null");
	}

	@Override
	public void put(K key, V value) {
		put(key, value, defaultTtlMillis);
	}
	
	private void put(K key, V value, long ttlMillis) {
		Objects.requireNonNull(key, "Key can not be null");
		Objects.requireNonNull(value, "Value can not be null");
		if(ttlMillis <= 0) { throw new IllegalArgumentException("TtlMillis must be more than 0"); }
		
		long now = System.currentTimeMillis();
		evictExpired(now);
		
		boolean existed = map.containsKey(key);
		if(!existed && map.size() >= capacity) {
			K victim = evictionPolicy.evictKey();
			if(victim != null) {
				removeInternal(key);
			}
		}
		
		long nowMillis = now + ttlMillis;
		map.put(key, new CacheEntry<V>(value, nowMillis, ++versionCounter));
		queue.add(new ExpiryNode<K>(nowMillis, key, versionCounter));
		evictionPolicy.onPut(key);
	}

	@Override
	public V get(K key) {
		Objects.requireNonNull(key, "Key can not be null");
		
		long now = System.currentTimeMillis();
		evictExpired(now);
		
		CacheEntry<V> value = map.get(key);
		if(value == null) { return null; }
		
		if(value.isExpired(now)) {
			removeInternal(key);
			return null;
		}
		
		evictionPolicy.onGet(key);
		return value.value();
	}

	@Override
	public V remove(K key) {
		Objects.requireNonNull(key, "Key can not be null");
		return removeInternal(key);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean containsKey(K key) {
		return map.containsKey(key);
	}

	@Override
	public void clear() {
		map.clear();
		queue.clear();
		evictionPolicy.onClear();
	}
	
	private V removeInternal(K key) {		
		evictionPolicy.onRemove(key);
		CacheEntry<V> value = map.remove(key);
		
		if(value == null) { return null; }
		return value.value();
	}
	
	private void evictExpired(long nowMillis) {
		while(!queue.isEmpty()) {
			ExpiryNode<K> top = queue.peek();
			if(top.expiresAtMillis() > nowMillis) {
				return;
			}
			
			queue.poll();
			
			CacheEntry<V> value = map.get(top.key());
			
			if(value == null) { continue; }
			if(value.version() != top.version()) { continue; }
			if(value.isExpired(nowMillis)) { removeInternal(top.key()); }
		}
	}
}
