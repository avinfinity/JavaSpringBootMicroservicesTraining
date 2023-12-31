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
import com.walmart.api.product.service.impl.ProductServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class ProductRestController {

	

	
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	
	@PostMapping("api/product") // This is URI used by client to call this method
	public Product createProduct(@RequestBody Product product) {

		return productServiceImpl.createProduct(product);
	}
	
//	@RequestMapping("api/getProduct/{id}")
//
//	public Optional<Product> findByCoupounId(@PathVariable String id) {
//		return this.coupounRepo.findById(id);
//	}
//	
	@RequestMapping("api/getKnownInstances")
	
	public String getKnownInstances() {
		System.out.println( productServiceImpl.hello());
		return productServiceImpl.hello() + "";
	}	
	
	
	
	
}
