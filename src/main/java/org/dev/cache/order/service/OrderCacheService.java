package org.dev.cache.order.service;

import java.util.Objects;

import org.dev.cache.core.Cache;
import org.dev.cache.order.module.Order;
import org.dev.cache.order.module.OrderKey;

public class OrderCacheService {
	private final Cache<OrderKey, Order> cache;
	
	public OrderCacheService(Cache<OrderKey, Order> cache) {
		this.cache = Objects.requireNonNull(cache);
	}
	
	public void putOrder(OrderKey key, Order value) {
		cache.put(key, value);
	}
	
	public Order getOrder(OrderKey key) {
		return cache.get(key);
	}
	
	public Order removeOrder(OrderKey key) {
		return cache.remove(key);
	}
	
	public int size() {
		return cache.size();
	}
}