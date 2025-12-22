package org.dev.cache.core;

public interface Cache<K, V> {
	void put(K key, V value);
	V get(K key);
	V remove(K key);
	
	int size();
	boolean containsKey(K key);
	void clear();
}
