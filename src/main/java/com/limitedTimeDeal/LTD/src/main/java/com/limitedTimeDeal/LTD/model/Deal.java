package com.limitedTimeDeal.LTD.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Deal {

	@Id
	private String dealId;
	private String dealName;
	private double itemPrice;
	private int noOfItems;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD'T'HH:mm:ss'Z'")
	private Date startTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Date endTime;

	private Deal() {
		dealId = UUID.randomUUID().toString();
		this.startTime = new Date();
		this.endTime = new Date();
	}

	public Deal(double itemPrice, int noOfItems, String dealName) {
		dealId = UUID.randomUUID().toString();
		this.itemPrice = itemPrice;
		this.noOfItems = noOfItems;
		this.startTime = new Date();
		this.endTime = new Date();
		this.dealName = dealName;
		// this.seller = seller;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(int numberOfHours) {
		endTime = new Date();
	}

	public void setEndTimeToZero() {
		endTime = new Date();
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public Date getStartTime() {
		return startTime;
	}

	public String getDealId() {
		return dealId;
	}
	// public Seller getSeller() {
	// return seller;
	// }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Deal [itemPrice=");
		builder.append(itemPrice);
		builder.append(", noOfItems=");
		builder.append(noOfItems);
		builder.append(", startTime=");
		builder.append(getStartTime());
		builder.append(", endTime=");
		builder.append(getEndTime());
		builder.append(", dealId=");
		builder.append(dealId);
		builder.append(", dealName=");
		builder.append(dealName);
		builder.append(", sellerId=");
		// builder.append(seller);
		builder.append("]");
		return builder.toString();
	}

}