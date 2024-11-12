package com.limitedTimeDeal.LTD.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Buyer {
	
	@Id
	private String userId;
	private String userName;
	
	@OneToMany
	private Set<Deal> deals;
	public Buyer() {
		userId= UUID.randomUUID().toString();
		deals = new HashSet<>();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public Set<Deal> getDeals() {
		return deals;
	}
}