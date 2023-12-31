package com.walmart.api.product.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.api.product.feign.client.CoupounFeignClient;
import com.walmart.api.product.model.Product;
import com.walmart.api.product.repository.ProductRepository;
import com.walmart.api.product.rest.dto.Coupoun;

@RestController
public class ProductRestController {

	@Autowired
	private ProductRepository coupounRepo;

	@Autowired
	private CoupounFeignClient coupounRestClient;

	@PostMapping("api/product") // This is URI used by client to call this method
	public Product createProduct(@RequestBody Product product) {

		// before saving the product to the database, we have to check based on copoun
		// id the discount to be offered.
		// After offering the discount accordingly we must be saving the product in
		// database.

		// Making REST call is common requirement in Microservice architecture which we
		// can implement using Rest Template
		// but this is traditional approach

		// To make REST call declarative, spring cloud offers library known as OpenFeign

		Coupoun coupoun = coupounRestClient.getCoupoun(product.getCoupounId()).get();

		product.setPrice(product.getPrice() - coupoun.getDiscount());

		return this.coupounRepo.save(product);
	}

	@RequestMapping("api/getProduct/{id}")

	public Optional<Product> findByCoupounId(@PathVariable String id) {
		return this.coupounRepo.findById(id);
	}

}
