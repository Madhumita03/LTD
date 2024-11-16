package com.limitedTimeDeal.LTD.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.limitedTimeDeal.LTD.model.Buyer;
import com.limitedTimeDeal.LTD.model.Deal;
import com.limitedTimeDeal.LTD.model.Seller;
import com.limitedTimeDeal.LTD.service.LimitedDealManager;
import com.limitedTimeDeal.LTD.service.SchedulerTask;

@RestController
public class LimitedDealController {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerTask.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
	@Autowired
	LimitedDealManager manager;

	//@Scheduled(cron = "* * * 1 * ?")
	@PostMapping("/create/deal")
	ResponseEntity createDeal(@RequestBody Deal deal) {
		logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
		return manager.handleDealCreation(deal);
	}

	@PostMapping("/register/seller")
	ResponseEntity createSeller(@RequestBody Seller seller) {
		return manager.createSeller(seller);
	}

	@PostMapping("/register/buyer/{name}")
	ResponseEntity createBuyer(@RequestBody Buyer buyer, @PathVariable("name") String name) {
		return manager.registerBuyer(name);
	}

	@GetMapping("/claim/deal/{dealName}/user/{userId}")
	ResponseEntity claimDeal(@PathVariable("dealName") String dealName, @PathVariable("userId") String userId) {
		return manager.claimDeal(dealName, userId);
	}

	@GetMapping("/modify/deal/{dealName}/seller/{sellerId}")
	ResponseEntity modifyDeal(@PathVariable("dealName") String dealName, @PathVariable("sellerId") String sellerId,
			@RequestParam(value = "noOfItems", required = false) Integer noOfItems,
			@RequestParam(value = "noOfHours", required = false) Integer noOfHours) {
		return manager.updateNoOfItemsOrNoOfHours(dealName, sellerId, noOfItems, noOfHours);
	}

	@GetMapping("/end/deal/{dealName}/seller/{sellerId}")
	ResponseEntity endDeal(@PathVariable("dealName") String dealName, @PathVariable("sellerId") String sellerId) {
		return manager.endDeal(dealName, sellerId);
	}

}