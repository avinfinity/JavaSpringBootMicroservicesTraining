package com.walmart.api.coupouns.repository.Impl;

import com.walmart.api.coupouns.model.Coupoun;
import com.walmart.api.coupouns.repository.CoupounRepository;

public class CoupounRepositoryImpl implements CoupounRepository {
	
	@Override
	public String insertNewCoupoun(Coupoun c)
	{
		System.out.println("Coupoun inserted using JDBC");
		return "CI";
	}
	
	
}
