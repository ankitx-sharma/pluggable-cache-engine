package org.dev.cache.user.module;

import java.util.Set;

public class User {
	private String userId;
	private String name;
	private String email;
	private Set<String> roles;
	
	public User(String userId, String name, String email, Set<String> roles) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
	
	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public Set<String> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", roles=" + roles + "]";
	}
}
