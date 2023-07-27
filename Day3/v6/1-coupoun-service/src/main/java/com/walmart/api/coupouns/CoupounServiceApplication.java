package com.walmart.api.coupouns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

import com.walmart.api.coupouns.model.Coupoun;
import com.walmart.api.coupouns.repository.CoupounRepository;

@SpringBootApplication
@EnableEurekaClient
public class CoupounServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = SpringApplication.run(CoupounServiceApplication.class, args);

		// CoupounRepository repo = container.getBean(CoupounRepository.class);
		// Coupoun coupoun = new Coupoun();
		// repo.insertNewCoupoun(coupoun);
	}
}
