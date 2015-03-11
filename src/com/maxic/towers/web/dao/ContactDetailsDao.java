package com.maxic.towers.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Component("contactDetailsDao")
public class ContactDetailsDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<ContactDetails> getAllContactDetails() {

		return session().createQuery("from ContactDetails").list();
	}

	@SuppressWarnings("unchecked")
	public List<ContactDetails> getContactDetails(int id) {
		return (List<ContactDetails>) session()
				.createQuery("from ContactDetails where towerId = :id")
				.setInteger("id", id).list();
	}

	public void addContactDetails(ContactDetails contactDetails) {
		session().saveOrUpdate(contactDetails);
	}

	public boolean contactDetailsExist(int id) {
		return this.getContactDetails(id).size() != 0;
	}

	public boolean deleteContactDetails(int towerId, int contactId) {
		String hql = "delete from Practice where towerId = :towerId and contactId = :contactId";
		return session().createQuery(hql).setInteger("towerId", towerId)
				.setInteger("contactId", contactId).executeUpdate() == 1;
	}

	public void editContactDetails(ContactDetails contactDetails) {
		session().update(contactDetails);
	}


}
