package com.management.customer_relation_management.response;

import org.springframework.http.HttpStatus;


public class SuccessResponse {

    private HttpStatus status;
    private String message;
    private int statusCode;
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
    
    
}
