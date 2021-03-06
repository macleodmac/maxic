package com.maxic.towers.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.model.*;
import com.maxic.towers.web.dao.ContactDetailsDao;

@Service("contactDetailsService")
public class ContactDetailsService {

	private ContactDetailsDao contactDetailsDao;

	@Autowired
	public void setContactDetailsDao(ContactDetailsDao contactDetailsDao) {
		this.contactDetailsDao = contactDetailsDao;
	}

	public List<ContactDetails> getAllContactDetails() {
		return contactDetailsDao.getAllContactDetails();
	}

	public List<ContactDetails> getContactDetails(int id) {
		return contactDetailsDao.getContactDetails(id);
	}

	public void addContactDetails(ContactDetails contactDetails) {
		contactDetailsDao.addContactDetails(contactDetails);
	}

	public boolean deleteContactDetail(int contactId) {
		return contactDetailsDao.deleteContactDetail(contactId);
	}

	public void editContactDetails(ContactDetails contactDetails) {
		contactDetailsDao.editContactDetails(contactDetails);

	}

	public ContactDetails getContactDetail(int c) {
		return contactDetailsDao.getContactDetail(c);
	}
	
	public boolean existsByDetail(int towerId, String detail) {
		return contactDetailsDao.existsByDetail(towerId, detail);
	}
}
