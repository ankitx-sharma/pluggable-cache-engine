package org.dev.cache.order.module;

import java.time.LocalDate;
import java.util.Objects;

public class OrderKey implements Comparable<OrderKey>{
	private LocalDate date;
	private int sequence;
	
	public OrderKey(LocalDate date, int sequence) {
		this.date = Objects.requireNonNull(date);
		this.sequence = sequence;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getSequence() {
		return sequence;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, sequence);
	}
	
	@Override
	public int compareTo(OrderKey o) {
		int cmp = this.date.compareTo(o.getDate());
		if(cmp != 0) { return cmp; }
		
		return Integer.compare(this.sequence, o.getSequence());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderKey other = (OrderKey) obj;
		return Objects.equals(date, other.date) && sequence == other.sequence;
	}
}
