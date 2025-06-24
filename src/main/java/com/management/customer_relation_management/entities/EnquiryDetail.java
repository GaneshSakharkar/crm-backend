package com.management.customer_relation_management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class EnquiryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long enquiryDetailId;

    private String enquiryType;

    private String enquiryDescription;

    private String studentName;

    private String enquiryDate;

    @ManyToOne
    @JsonIgnore
    private EnquiryForm enquiryForm;

	public long getEnquiryDetailId() {
		return enquiryDetailId;
	}

	public void setEnquiryDetailId(long enquiryDetailId) {
		this.enquiryDetailId = enquiryDetailId;
	}

	public String getEnquiryType() {
		return enquiryType;
	}

	public void setEnquiryType(String enquiryType) {
		this.enquiryType = enquiryType;
	}

	public String getEnquiryDescription() {
		return enquiryDescription;
	}

	public void setEnquiryDescription(String enquiryDescription) {
		this.enquiryDescription = enquiryDescription;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEnquiryDate() {
		return enquiryDate;
	}

	public void setEnquiryDate(String enquiryDate) {
		this.enquiryDate = enquiryDate;
	}

	public EnquiryForm getEnquiryForm() {
		return enquiryForm;
	}

	public void setEnquiryForm(EnquiryForm enquiryForm) {
		this.enquiryForm = enquiryForm;
	}
    
    
}
