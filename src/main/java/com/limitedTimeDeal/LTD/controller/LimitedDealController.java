package com.limitedTimeDeal.LTD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.limitedTimeDeal.LTD.model.Deal;
import com.limitedTimeDeal.LTD.service.LimitedDealManager;

@RestController
public class LimitedDealController {
	
	@Autowired
	LimitedDealManager manager;
	
	@PostMapping("/create/deal")
	ResponseEntity createDeal(@RequestBody Deal deal) {
		
		return manager.handleDealCreation(deal);
	}

	@GetMapping("/register/seller")
	String createSeller() 
	{
		return manager.createSeller();
	}
	
	@GetMapping("/register/user/{name}")
	String createUser(@PathVariable("name") String name)
	{
		return manager.registerUser(name);
	}
	
	@GetMapping("/claim/deal/{dealName}/user/{userId}")
	ResponseEntity claimDeal(@PathVariable("dealName") String dealName, @PathVariable("userId") String userId)
	{
		return manager.claimDeal(dealName, userId);
	}
	
	@GetMapping("/modify/deal/{dealName}/seller/{sellerId}") 
	ResponseEntity modifyDeal(@PathVariable("dealName") String dealName, @PathVariable("sellerId") String sellerId,
			@RequestParam(value="noOfItems",required = false) Integer noOfItems, @RequestParam(value="noOfHours",required = false) Integer noOfHours) {
		return manager.updateNoOfItemsOrNoOfHours(dealName, sellerId, noOfItems, noOfHours);
	}
	
	@GetMapping("/end/deal/{dealName}/seller/{sellerId}")
	ResponseEntity endDeal(@PathVariable("dealName") String dealName, @PathVariable("sellerId") String sellerId) {
		return manager.endDeal(dealName, sellerId);
	}
	
	
}