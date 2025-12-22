package org.dev.cache.order.module;

import java.math.BigDecimal;

public class Order {
	private String orderId;
	private BigDecimal totalAmount;
	private int itemCount;
	
	public Order(String orderId, BigDecimal totalAmount, int itemCount) {
		this.orderId = orderId;
		this.totalAmount = totalAmount;
		this.itemCount = itemCount;
	}

	public String getOrderId() {
		return orderId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public int getItemCount() {
		return itemCount;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", totalAmount=" + totalAmount + ", itemCount=" + itemCount + "]";
	}
}
