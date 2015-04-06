package com.maxic.towers.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contactdetails")
public class ContactDetails {
	
	private int towerId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int contactId;
	private String type;
	private String detail;
	
	public ContactDetails() {

	}

	public ContactDetails(int towerId, int contactId, String type, String detail) {
		this.towerId = towerId;
		this.contactId = contactId;
		this.type = type;
		this.detail = detail;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "ContactDetails [towerId=" + towerId + ", contactId="
				+ contactId + ", type=" + type + ", detail=" + detail + "]";
	}
	
	

	
}
