package com.management.customer_relation_management.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;


@Entity
public class EnquiryForm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_seq")
    @SequenceGenerator(name = "custom_seq", sequenceName = "custom_sequence", initialValue = 100000, allocationSize = 1)
    private long id;

    private String enquiryDate;

    private String name;

    private String contact;

    private String email;

    private String college;

    private String branch;

    private String source;

    private String qualification;

    @OneToMany(mappedBy = "enquiryForm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();

    @JsonIgnoreProperties("enquiryForm")
    @OneToMany(mappedBy = "enquiryForm", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EnquiryDetail> enquiryDetails = new ArrayList<>();

    @JsonIgnoreProperties("equiries")
    @ManyToOne
    private Manager manager;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEnquiryDate() {
		return enquiryDate;
	}

	public void setEnquiryDate(String enquiryDate) {
		this.enquiryDate = enquiryDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<EnquiryDetail> getEnquiryDetails() {
		return enquiryDetails;
	}

	public void setEnquiryDetails(List<EnquiryDetail> enquiryDetails) {
		this.enquiryDetails = enquiryDetails;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
    
    

}
