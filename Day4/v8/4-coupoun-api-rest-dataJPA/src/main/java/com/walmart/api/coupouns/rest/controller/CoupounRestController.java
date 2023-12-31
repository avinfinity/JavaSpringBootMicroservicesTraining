package com.walmart.api.coupouns.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.api.coupouns.model.Coupoun;
import com.walmart.api.coupouns.repository.CoupounRepository;

@RestController
public class CoupounRestController {
	@Autowired
	private CoupounRepository coupounRepo;

	@PostMapping("api/coupoun") // This is URI used by client to call this method
	public Coupoun createCoupoun(@RequestBody Coupoun coupoun ) {

		//Coupoun coupoun = new Coupoun();
		//coupoun.setId("testCoupoun");
		//coupoun.setDiscount(20);
		return this.coupounRepo.save(coupoun);

		//System.out.println("Coupounc Created");
		//return "CI";

	}
	
	@RequestMapping("api/getCoupoun/{id}")
	
	public Optional<Coupoun> findByCoupounId(@PathVariable String id) {
		return this.coupounRepo.findById(id);
	}
}
