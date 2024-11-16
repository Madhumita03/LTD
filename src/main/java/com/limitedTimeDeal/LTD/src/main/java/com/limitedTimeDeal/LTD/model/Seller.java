package com.limitedTimeDeal.LTD.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Seller {

	@Id
	private String sellerId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Deal> deals;

	public Seller() {
		this.sellerId = UUID.randomUUID().toString();
	}

	public List<Deal> getDeals() {
		return deals;
	}

	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}

	public String getSellerId() {
		return sellerId;
	}

}
