package com.walmart.api.product.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.walmart.api.product.feign.client.CoupounFeignClient;
import com.walmart.api.product.model.Product;
import com.walmart.api.product.repository.ProductRepository;
import com.walmart.api.product.rest.dto.Coupoun;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ProductServiceImpl {

	@Autowired
	private CoupounFeignClient coupounRestClient;

	@Autowired
	private ProductRepository coupounRepo;

	@CircuitBreaker(fallbackMethod = "fallbackForHello", name = "cs")
	public int hello() {
	
		coupounRestClient.getKnowCoupounInstance();
		return 100;
		
	}
	
	public int fallbackForHello(Throwable t) {
		return -100;
	}
	
	@CircuitBreaker(fallbackMethod = "handlecreateProductFailure", name = "cs") // cs name is mentioned in
	public Product createProduct(Product product) {

		Coupoun coupoun = coupounRestClient.getCoupoun(product.getCoupounId()).get();

		product.setPrice(product.getPrice() - coupoun.getDiscount());

		return this.coupounRepo.save(product);
	}

	public Product handlecreateProductFailure(Product product, Throwable t) {

		System.out.println("Fallback for creating product called");

		return product ;
	}
}
