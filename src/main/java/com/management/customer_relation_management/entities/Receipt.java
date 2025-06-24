package com.management.customer_relation_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.customer_relation_management.paymentType.PaymentType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String date;

    private long recievedAmount;

    private PaymentType paymentType = PaymentType.NOT_SELECTED;

    private String sender;
    
    private String towards;

    @JsonIgnore
    @ManyToOne
    private RegistrationForm registrationForm;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getRecievedAmount() {
		return recievedAmount;
	}

	public void setRecievedAmount(long recievedAmount) {
		this.recievedAmount = recievedAmount;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTowards() {
		return towards;
	}

	public void setTowards(String towards) {
		this.towards = towards;
	}

	public RegistrationForm getRegistrationForm() {
		return registrationForm;
	}

	public void setRegistrationForm(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}
    
    
}
