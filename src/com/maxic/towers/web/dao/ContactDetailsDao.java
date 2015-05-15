package com.maxic.towers.web.dao;

import java.util.List;

import com.maxic.towers.web.model.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	/**
	 * Fetches all contact details from the database
	 * 
	 * @return list of contact details
	 */
	@SuppressWarnings("unchecked")
	public List<ContactDetails> getAllContactDetails() {
		Criteria crit = session().createCriteria(ContactDetails.class);

		return crit.list();
	}

	/**
	 * Fetches all contact details from the database for a given towerId
	 * 
	 * @param towerId
	 *            for which to get the contact details
	 * @return list of contact details
	 */
	@SuppressWarnings("unchecked")
	public List<ContactDetails> getContactDetails(int id) {

		return (List<ContactDetails>) session()
				.createQuery("from ContactDetails where towerId = :id")
				.setInteger("id", id).list();
	}

	/**
	 * Persists contact detail to database
	 * 
	 * @param contactDetails
	 *            to persist
	 */
	public void addContactDetails(ContactDetails contactDetails) {
		session().save(contactDetails);
	}

	/**
	 * Check that contact details exist for a given towerId
	 * 
	 * 
	 * @param towerId
	 *            for which to get the contact details
	 * @return boolean
	 */
	public boolean contactDetailsExist(int id) {
		return this.getContactDetails(id).size() != 0;
	}

	/**
	 * Delete contact details from the database for a given contactId
	 * 
	 * 
	 * @param contactId
	 *            to delete
	 * @return boolean
	 */
	public boolean deleteContactDetail(int contactId) {
		String hql = "delete from ContactDetails where contactId = :contactId";
		return session().createQuery(hql).setInteger("contactId", contactId)
				.executeUpdate() == 1;
	}

	/**
	 * Update given contactDetails in the database
	 * 
	 * 
	 * @param contactDetails
	 *            to update
	 */
	public void editContactDetails(ContactDetails contactDetails) {
		session().update(contactDetails);
	}

	/**
	 * Fetch the contact details for a given contactId
	 * 
	 * 
	 * @param contactId
	 *            for which to get the contact details
	 * @return ContactDetails
	 */
	public ContactDetails getContactDetail(int id) {

		Criteria crit = session().createCriteria(ContactDetails.class);
		crit.add(Restrictions.idEq(id));
		ContactDetails contactDetails = (ContactDetails) crit.uniqueResult();

		return contactDetails;
	}

	/**
	 * Deeper check to see if the contact detail exists
	 * 
	 * 
	 * @param towerId
	 *            for which to get the contact details
	 * @param detail
	 *            the contact detail to check
	 * @return boolean
	 */
	public boolean existsByDetail(int towerId, String detail) {
		Criteria crit = session().createCriteria(ContactDetails.class);
		crit.add(Restrictions.eq("detail", detail));
		crit.add(Restrictions.eq("towerId", towerId));
		return crit.list().size() == 1;
	}

}
