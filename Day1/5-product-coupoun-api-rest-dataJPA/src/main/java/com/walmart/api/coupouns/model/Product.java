package com.walmart.api.coupouns.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private String id;
	private String name;
	private double price;
	private String coupounId;

}
