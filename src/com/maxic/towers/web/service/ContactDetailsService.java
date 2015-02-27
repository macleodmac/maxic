package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.ContactDetails;
import com.maxic.towers.web.dao.ContactDetailsDao;


public class ContactDetailsService {

	private ContactDetailsDao contactDetailsDao;

	@Autowired
	public void setContactDetailsDao(ContactDetailsDao contactDetailsDao) {
		this.contactDetailsDao = contactDetailsDao;
	}

	public List<ContactDetails> getContactDetails() {
		return contactDetailsDao.getContactDetails();
	}
	
	public boolean addContactDetails(ContactDetails contactDetails) {
		return contactDetailsDao.addContactDetails(contactDetails);
	}

	public ContactDetails getContactDetails(int id) {
		return contactDetailsDao.getContactDetails(id);
	}

	public boolean deleteContactDetails(int id) {
		return contactDetailsDao.deleteContactDetails(id);
	}

	public boolean editContactDetails(ContactDetails contactDetails) {
		return contactDetailsDao.editContactDetails(contactDetails);
		
	}

	public boolean addContactDetails(ArrayList<ContactDetails> contactDetailsList) {
		return contactDetailsDao.addContactDetails(contactDetailsList);
	}
}
