package com.example.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "recipient", namespace = "urn:xtk:queryDef")
public class Recipient {

	protected String email;
	protected String firstName;
	protected String lastName;
	
	@SerializedName("tonariwaId")
	protected String tonariwaid;
	
	@SerializedName("phone")
	protected String mobilePhone;
	protected String country;
	
	@SerializedName("folder")
	protected String folderName;
	
	protected String company;
	protected int count;

	@XmlAttribute(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlAttribute(name="firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlAttribute(name="lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlAttribute(name="tonariwaid")
	public String getTonariwaid() {
		return tonariwaid;
	}

	public void setTonariwaid(String tonariwaid) {
		this.tonariwaid = tonariwaid;
	}

	@XmlAttribute(name="count")
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
