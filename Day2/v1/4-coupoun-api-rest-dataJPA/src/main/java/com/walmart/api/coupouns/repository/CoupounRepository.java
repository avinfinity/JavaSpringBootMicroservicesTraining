package com.walmart.api.coupouns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walmart.api.coupouns.model.Coupoun;

public interface CoupounRepository extends JpaRepository<Coupoun, String> {
	//String insertNewCoupoun(Coupoun c);

	//Coupoun findById(String id);

}
