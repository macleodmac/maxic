package com.maxic.towers.web.model;

import java.util.ArrayList;
import java.util.List;

public class TowerWrapper {

	private Tower tower;

	private List<Practice> practiceList;

	private List<ContactDetails> contactDetailsList;

	public TowerWrapper() {
		this.practiceList = new ArrayList<Practice>();
		this.contactDetailsList = new ArrayList<ContactDetails>();
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public List<Practice> getPracticeList() {
		return practiceList;
	}

	public void setPracticeList(List<Practice> practiceList) {
		this.practiceList = practiceList;
	}

	public void addPractice(Practice practice) {
		this.practiceList.add(practice);
	}

	public int sizePractice() {
		return practiceList.size();
	}

	public List<ContactDetails> getContactDetailsList() {
		return contactDetailsList;
	}

	public void setContactDetailsList(List<ContactDetails> contactDetailsList) {
		this.contactDetailsList = contactDetailsList;
	}

	public void addContactDetails(ContactDetails contactDetails) {
		this.contactDetailsList.add(contactDetails);
	}

	public int sizeContactDetails() {
		return contactDetailsList.size();
	}

	@Override
	public String toString() {
		return "TowerWrapper [tower=" + tower + ", practiceList="
				+ practiceList + ", contactDetailsList=" + contactDetailsList
				+ "]";
	}
	
	

}
