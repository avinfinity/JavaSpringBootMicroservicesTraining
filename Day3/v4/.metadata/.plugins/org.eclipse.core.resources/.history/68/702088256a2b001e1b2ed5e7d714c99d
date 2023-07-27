package com.walmart.api.product.feign.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walmart.api.product.rest.dto.Coupoun;

@FeignClient(value="coupoun-service")
public interface CoupounFeignClient {


	@RequestMapping("api/getCoupoun/{id}")
	public Optional<Coupoun> getCoupoun(@PathVariable String id);
	
	@RequestMapping("api/coupoun/instance")
	public String getKnowCoupounInstance();
}
