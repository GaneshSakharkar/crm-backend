package com.management.customer_relation_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String courseName;

    private String courseDuration;

    private long price;
    
    

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public RegistrationForm getRegistrationForm() {
		return registrationForm;
	}

	public void setRegistrationForm(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}

	public EnquiryForm getEnquiryForm() {
		return enquiryForm;
	}

	public void setEnquiryForm(EnquiryForm enquiryForm) {
		this.enquiryForm = enquiryForm;
	}

	@JsonIgnore
    @ManyToOne
    private RegistrationForm registrationForm ;

    @JsonIgnore
    @ManyToOne
    private EnquiryForm enquiryForm;

}
