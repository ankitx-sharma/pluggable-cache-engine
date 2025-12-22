package org.dev.cache.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryCache<K, V> implements Cache<K, V> {
	private final Map<K, CacheEntry<V>> map = new HashMap<>();
	
	@Override
	public void put(K key, V value) {
		Objects.requireNonNull(key, "Key must not be null");
		Objects.requireNonNull(value, "Value must not be null");
		
		map.put(key, new CacheEntry<>(value));
	}

	@Override
	public V get(K key) {
		Objects.requireNonNull(key, "Key must not be null");
		CacheEntry<V> value = map.get(key); 
		
		return value == null ? null : value.value();
	}

	@Override
	public V remove(K key) {
		Objects.requireNonNull(key, "Key must not be null");
		CacheEntry<V> value = map.remove(key); 
		
		return value == null ? null : value.value();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean containsKey(K key) {
		Objects.requireNonNull(key, "Key must not be null");
		
		return map.containsKey(key);
	}

	@Override
	public void clear() {
		map.clear();
	}
	
}
