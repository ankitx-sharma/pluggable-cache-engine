package org.dev.cache.eviction;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {
	private final Map<K, Boolean> order = new LinkedHashMap<>(16, 0.75f, true);
	
	@Override
	public void onPut(K key) {
		order.put(key, Boolean.TRUE);
	}

	@Override
	public void onGet(K key) {
		if(order.containsKey(key)) {
			order.get(key);
		}
	}

	@Override
	public void onRemove(K key) {
		order.remove(key);
	}
	
	@Override
	public void onClear() {
		order.clear();
	}

	@Override
	public K evictKey() {
		if(order.isEmpty()) return null;
		return order.keySet().iterator().next();
	}

}
