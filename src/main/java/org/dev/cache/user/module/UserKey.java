package org.dev.cache.user.module;

import java.util.Objects;

public class UserKey implements Comparable<UserKey>{
	private final String country;
	private final long id;
	
	public UserKey(String country, long id) {
		super();
		this.country = country;
		this.id = id;
	}
	
	public String getCountry() {
		return country;
	}
	public long getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(country, id);
	}
	
	@Override
	public int compareTo(UserKey o) {
		int cmp = this.country.compareTo(o.getCountry());
		if(cmp != 0) { return cmp; }
		
		return Long.compare(this.id, o.getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserKey other = (UserKey) obj;
		return Objects.equals(country, other.country) && id == other.id;
	}
	
	@Override
	public String toString() {
		return "UserKey [country=" + country + ", id=" + id + "]";
	}
}
