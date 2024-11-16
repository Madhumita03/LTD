package com.limitedTimeDeal.LTD.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limitedTimeDeal.LTD.model.Buyer;
import com.limitedTimeDeal.LTD.model.Deal;
import com.limitedTimeDeal.LTD.model.Seller;
import com.limitedTimeDeal.LTD.repository.BuyerRepository;
import com.limitedTimeDeal.LTD.repository.DealRepository;
import com.limitedTimeDeal.LTD.repository.SellerRepository;

@Service
@Transactional
public class LimitedDealManager {

	Map<String, Seller> sellers = new HashMap<>();
	Map<String, Buyer> buyers = new HashMap<>();
	Map<String, Set<String>> userDeal = new HashMap<>();
	Map<String, Deal> dealsList = new HashMap<>();

	@Autowired
	DealRepository dealRepository;
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	BuyerRepository buyerRepository;
	
	public ResponseEntity handleDealCreation(Deal deal) {

		if (deal == null)
			return ResponseEntity.badRequest().body("Invalid Seller/Deal");

		// Map<Seller, Deal> deals = sellers.getDeals();
		// if(deals.containsKey(deal.getDealName())) {
		// return ResponseEntity.badRequest().body("Deal already exists");
		// }
		// deals.add(deal.getDealName(), deal);
		dealsList.put(deal.getDealName(), deal);
		userDeal.put(deal.getDealName(), new HashSet<>());
		
		dealRepository.save(deal);
		
		return ResponseEntity.ok("Your deal is created successfully!!!. Reference Id - " + deal);
	}

	public ResponseEntity createSeller(Seller seller) {
		/// seller.setDeals(new HashMap<>());
		sellers.put(seller.getSellerId(), seller);
		
		Seller newSeller = sellerRepository.save(seller);
		return new ResponseEntity<>(newSeller, HttpStatus.CREATED);
	}

	public ResponseEntity registerBuyer(String buyerName) {
		Buyer buyer = new Buyer();
		buyer.setBuyerName(buyerName);
		buyers.put(buyer.getBuyerId(), buyer);
		
		buyerRepository.save(user);
		return new ResponseEntity<>(user, );
	}

	public ResponseEntity claimDeal(String dealName, String userId) {
		if (Objects.isNull(dealName) || Objects.isNull(userId) || !dealsList.containsKey(dealName)
				|| !buyers.containsKey(userId)) {
			return ResponseEntity.badRequest().body("Invalid User/Deal");
		}

		int count = userDeal.get(dealName).size();
		int max = dealsList.get(dealName).getNoOfItems();
		if (userDeal.get(dealName).contains(userId) || count >= max
				|| dealsList.get(dealName).getEndTime().before(new Date())) {
			return ResponseEntity.badRequest().body("Deal Not available");
		}
		userDeal.get(dealName).add(userId);
		buyers.get(userId).getDeals().add(dealsList.get(dealName));
		return ResponseEntity.ok("Sucessful");
	}

	public ResponseEntity updateNoOfItemsOrNoOfHours(String dealName, String sellerId, Integer noOfItems,
			Integer hoursToIncrease) {
		if (Objects.isNull(dealName) || Objects.isNull(sellerId) || !sellers.containsKey(sellerId)
				|| !dealsList.containsKey(dealName)) {
			return ResponseEntity.badRequest().body("Invalid Seller/Deal");
		}
		if (hoursToIncrease == null && noOfItems == null)
			return ResponseEntity.badRequest().body("Nothing to update");

		if (hoursToIncrease != null) {

			dealsList.get(dealName).setEndTime(hoursToIncrease.intValue());
		}

		if (noOfItems != null)
			dealsList.get(dealName).setNoOfItems(noOfItems);
		return ResponseEntity.ok("Your deal is updated successfully!!!. Reference Id - " + dealsList.get(dealName));
	}

	public ResponseEntity endDeal(String dealName, String sellerId) {
		if (Objects.isNull(dealName) || Objects.isNull(sellerId) || !sellers.containsKey(sellerId)
				|| !dealsList.containsKey(dealName)) {
			return ResponseEntity.badRequest().body("Invalid Seller/Deal");
		}
		dealsList.get(dealName).setEndTimeToZero();
		return ResponseEntity.ok("Your deal is updated successfully!!!. Reference Id - " + dealsList.get(dealName));
	}

}