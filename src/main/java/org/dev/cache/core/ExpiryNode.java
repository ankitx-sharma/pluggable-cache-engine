package org.dev.cache.core;

public record ExpiryNode<K>(long expiresAtMillis, K key, long version) 
	implements Comparable<ExpiryNode<K>>{
	
	@Override
	public int compareTo(ExpiryNode<K> other) {
		return Long.compare(expiresAtMillis, other.expiresAtMillis);
	}
}
