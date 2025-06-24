package com.management.customer_relation_management.dto;

public class UpdateAmount {
    private long id;
    private double amountPaid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
    
}
