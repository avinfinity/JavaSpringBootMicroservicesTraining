package com.walmart.api.coupouns.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coupoun {
	@Id
	private String id;
	private int discount;
	
	
	public String getId() {
		return id;
	}
	public void setId(String s) {
		this.id = s;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
}
