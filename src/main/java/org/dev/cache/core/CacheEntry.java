package org.dev.cache.core;

import java.util.Objects;

final class CacheEntry<V> {
	private final V value;
	private final long expiresAtMillis;
	private final long version;
	
	public CacheEntry(V value, long expiresAtMillis, long version) {
		this.value = Objects.requireNonNull(value);
		this.expiresAtMillis = expiresAtMillis;
		this.version = version;
	}

	public V value() {
		return value;
	}

	public long expiresAtMillis() {
		return expiresAtMillis;
	}

	public long version() {
		return version;
	}
	
	boolean isExpired(long nowMillis) {
		return nowMillis >= expiresAtMillis;
	}
}
