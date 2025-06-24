package com.management.customer_relation_management.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.management.customer_relation_management.paymentType.PaymentType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class RegistrationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_seq")
    @SequenceGenerator(name = "custom_seq", sequenceName = "custom_sequence", initialValue = 2615500, allocationSize = 1)
    private long id;

    private String registrationDate;

    private String name;

    private String contact;

    private String email;

    private String college;

    private String branch;

    private String imageUrl;

    private String source;

    private String qualification;

    private long totalFees;

    private long amountPaid;

    private int installmentsMonths;

    private double installments;

    private String deuDate;

    private PaymentType paymentType = PaymentType.NOT_SELECTED;

    @JsonIgnoreProperties("registrations")
    @ManyToOne
    private Manager manager;

    @OneToMany(mappedBy = "registrationForm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> registeredCourses= new ArrayList<>();

    @OneToMany(mappedBy = "registrationForm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Receipt> receipts = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public long getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(long totalFees) {
		this.totalFees = totalFees;
	}

	public long getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(long amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getInstallmentsMonths() {
		return installmentsMonths;
	}

	public void setInstallmentsMonths(int installmentsMonths) {
		this.installmentsMonths = installmentsMonths;
	}

	public double getInstallments() {
		return installments;
	}

	public void setInstallments(double installments) {
		this.installments = installments;
	}

	public String getDeuDate() {
		return deuDate;
	}

	public void setDeuDate(String deuDate) {
		this.deuDate = deuDate;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Course> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(List<Course> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

	public List<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(List<Receipt> receipts) {
		this.receipts = receipts;
	}
    
    
}
