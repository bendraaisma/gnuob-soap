package com.netbrasoft.gnuob.generic.customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.netbrasoft.gnuob.generic.security.Access;

@Entity(name = Customer.ENTITY)
@Table(name = Customer.TABLE)
@XmlRootElement(name = Customer.ENTITY)
public class Customer extends Access {

	private static final long serialVersionUID = -4021500012055256731L;
	protected static final String ENTITY = "Customer";
	protected static final String TABLE = "GNUOB_CUSTOMERS";

	@Column(name = "SALUTATION")
	private String salutation;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "PREFIX")
	private String suffix;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "PAYER_BUSINESS")
	private String payerBusiness;

	@Column(name = "PAYER_ID")
	private String payerId;

	@Column(name = "BUYER_EMAIL", nullable = false)
	private String buyerEmail;

	@Column(name = "BUYER_MARKETING_EMAIL")
	private String buyerMarketingEmail;

	@Column(name = "PAYER")
	private String payer;

	@Column(name = "PAYER_STATUS")
	private String payerStatus;

	@Column(name = "CONTACT_PHONE")
	private String contactPhone;

	@Column(name = "TAX_ID_TYPE")
	private String taxIdType;

	@Column(name = "TAX_ID")
	private String taxId;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, orphanRemoval = true, optional = false)
	private Address address;

	public Customer() {
	}

	@XmlElement(name = "adress", required = true)
	public Address getAddress() {
		return address;
	}

	@XmlElement(name = "buyerEmail", required = true)
	public String getBuyerEmail() {
		return buyerEmail;
	}

	@XmlElement(name = "buyerMarketingEmail")
	public String getBuyerMarketingEmail() {
		return buyerMarketingEmail;
	}

	@XmlElement(name = "contactPhone")
	public String getContactPhone() {
		return contactPhone;
	}

	@XmlElement(name = "firstName")
	public String getFirstName() {
		return firstName;
	}

	@XmlElement(name = "lastName")
	public String getLastName() {
		return lastName;
	}

	@XmlElement(name = "middleName")
	public String getMiddleName() {
		return middleName;
	}

	@XmlElement(name = "payer")
	public String getPayer() {
		return payer;
	}

	@XmlElement(name = "payerBusiness")
	public String getPayerBusiness() {
		return payerBusiness;
	}

	@XmlElement(name = "payerId")
	public String getPayerId() {
		return payerId;
	}

	@XmlElement(name = "payerStatus")
	public String getPayerStatus() {
		return payerStatus;
	}

	@XmlElement(name = "salutation")
	public String getSalutation() {
		return salutation;
	}

	@XmlElement(name = "suffix")
	public String getSuffix() {
		return suffix;
	}

	@XmlElement(name = "taxId")
	public String getTaxId() {
		return taxId;
	}

	@XmlElement(name = "taxIdType")
	public String getTaxIdType() {
		return taxIdType;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public void setBuyerMarketingEmail(String buyerMarketingEmail) {
		this.buyerMarketingEmail = buyerMarketingEmail;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public void setPayerBusiness(String payerBusiness) {
		this.payerBusiness = payerBusiness;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public void setPayerStatus(String payerStatus) {
		this.payerStatus = payerStatus;
	}

	public void setPrefix(String suffix) {
		this.suffix = suffix;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public void setTaxIdType(String taxIdType) {
		this.taxIdType = taxIdType;
	}

}