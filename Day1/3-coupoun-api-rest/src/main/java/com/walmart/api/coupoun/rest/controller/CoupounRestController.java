package com.walmart.api.coupoun.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.api.coupouns.model.Coupoun;
import com.walmart.api.coupouns.repository.CoupounRepository;

@RestController
public class CoupounRestController {
	@Autowired
	private CoupounRepository coupounRepo;
	
	
	@RequestMapping("api/coupoun") // This is URI used by client to call this method
	public String createCoupoun() {

		this.coupounRepo.insertNewCoupoun(new Coupoun());
		
		System.out.println("Coupounc Created");
		return "CI";
		
	}
	
	
}
