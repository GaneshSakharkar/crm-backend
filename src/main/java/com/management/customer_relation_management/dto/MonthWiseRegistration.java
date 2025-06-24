package com.management.customer_relation_management.dto;

public class MonthWiseRegistration {
    private String month;
    private long count;
    

    public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public long getCount() {
		return count;
	}


	public void setCount(long count) {
		this.count = count;
	}


	// Constructor to initialize month and count
    public MonthWiseRegistration(int month, long count) {
        // You can use a list of month names (e.g., January, February, etc.)
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        this.month = months[month - 1]; // Adjust because months are 1-indexed
        this.count = count;
    }
}
