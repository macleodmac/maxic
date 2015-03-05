package com.maxic.towers.web.dao;

import java.util.ArrayList;
import java.util.List;

public class TowerExec {

	private Tower tower;
	private Country country;
	private County county;
	private List<ContactDetails> contactDetails;
	
	public TowerExec() {
		this.tower = new Tower();
		this.contactDetails = new ArrayList<ContactDetails>();
		this.contactDetails.add(new ContactDetails());
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public List<ContactDetails> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<ContactDetails> list) {
		this.contactDetails = list;
	}

	public TowerExec(Tower tower, Country country, County county,
			ArrayList<ContactDetails> contactDetails) {
		super();
		this.tower = tower;
		this.country = country;
		this.county = county;
		this.contactDetails = contactDetails;
	}
	
	
	
}
