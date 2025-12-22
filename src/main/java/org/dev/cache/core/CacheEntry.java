package org.dev.cache.core;

import java.util.Objects;

final class CacheEntry<V> {
	private final V value;
	
	public CacheEntry(V value) {
		this.value = Objects.requireNonNull(value);
	}
	
	V value() {
		return this.value;
	}
}
