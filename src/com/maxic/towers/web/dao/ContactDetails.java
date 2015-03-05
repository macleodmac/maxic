package com.maxic.towers.web.dao;

public class ContactDetails {
	private int towerId;
	private int contactId;
	private String email;
	private String contactNumber;
	private String website;
	private String facebookPage;
	private String twitterPage;

	public ContactDetails() {

	}

	public ContactDetails(int towerId, int contactId, String email,
			String contactNumber, String website, String facebookPage,
			String twitterPage) {
		this.towerId = towerId;
		this.contactId = contactId;
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
				+ contactId + ", email=" + email + ", contactNumber="
				+ contactNumber + ", website=" + website + ", facebookPage="
				+ facebookPage + ", twitterPage=" + twitterPage + "]";
	}

}
