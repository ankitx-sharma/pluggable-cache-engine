package org.dev.cache.user.service;

import java.util.Objects;

import org.dev.cache.core.Cache;
import org.dev.cache.user.module.User;
import org.dev.cache.user.module.UserKey;

public class UserCacheService {
	private final Cache<UserKey, User> cache;
	
	public UserCacheService(Cache<UserKey, User> cache) {
		this.cache = Objects.requireNonNull(cache);
	}
	
	public void putUser(UserKey key, User value) {
		this.cache.put(key, value);
	}
	
	public User getUser(UserKey key) {
		return this.cache.get(key);
	}
	
	public User removeUser(UserKey key) {
		return this.cache.remove(key);
	}
	
	public boolean containsKey(UserKey key) {
		return this.cache.containsKey(key);
	}
	
	public int size() {
		return this.cache.size();
	}
}
