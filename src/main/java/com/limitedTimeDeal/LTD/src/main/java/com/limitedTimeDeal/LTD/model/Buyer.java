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
	private String buyerId;
	private String buyerName;

	@OneToMany
	private Set<Deal> deals;

	public Buyer() {
		buyerId = UUID.randomUUID().toString();
		deals = new HashSet<>();
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public void setDeals(Set<Deal> deals) {
		this.deals = deals;
	}

	public Set<Deal> getDeals() {
		return deals;
	}
}