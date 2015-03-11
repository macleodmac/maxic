package com.maxic.towers.web.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="contactdetails")
@IdClass(ContactPK.class)
public class ContactDetails {
	
	@Id
	private int towerId;
	@Id
	private int contactId;
	private String address;
	private String email;
	private String contactNumber;
	private String website;
	private String facebookPage;
	private String twitterPage;

	public ContactDetails() {

	}

	public ContactDetails(int towerId, int contactId, String address,
			String email, String contactNumber, String website,
			String facebookPage, String twitterPage) {
		super();
		this.towerId = towerId;
		this.contactId = contactId;
		this.address = address;
		this.email = email;
		this.contactNumber = contactNumber;
		this.website = website;
		this.facebookPage = facebookPage;
		this.twitterPage = twitterPage;
	}

	public int getTowerId() {
		return towerId;
	}

	public void setTowerId(int towerId) {
		this.towerId = towerId;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebookPage() {
		return facebookPage;
	}

	public void setFacebookPage(String facebookPage) {
		this.facebookPage = facebookPage;
	}

	public String getTwitterPage() {
		return twitterPage;
	}

	public void setTwitterPage(String twitterPage) {
		this.twitterPage = twitterPage;
	}

	@Override
	public String toString() {
		return "ContactDetails [towerId=" + towerId + ", contactId="
				+ contactId + ", address=" + address + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", website=" + website
				+ ", facebookPage=" + facebookPage + ", twitterPage="
				+ twitterPage + "]";
	}

	
}
