package org.dev.cache.eviction;

public interface EvictionPolicy<K> {
	void onPut(K key);
	void onGet(K key);
	void onRemove(K key);
	void onClear();
	
	/** Returns the key to evict, or null if nothing to evict. */
	K evictKey();
}
