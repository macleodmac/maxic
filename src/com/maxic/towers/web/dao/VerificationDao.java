package com.maxic.towers.web.dao;

import java.sql.Date;
import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.maxic.towers.web.model.VerificationToken;

@Component("verificationDao")
@Transactional
public class VerificationDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Persists token to database
	 * 
	 * @param verificationToken
	 *            to persist
	 */
	public void addVerificationToken(VerificationToken verificationToken) {
		session().save(verificationToken);
	}

	/**
	 * Check that the verification token is valid, in date, not used
	 * 
	 * 
	 * @param VerificationToken
	 * @return boolean
	 */
	public boolean validToken(VerificationToken verificationToken) {
		Criteria crit = session().createCriteria(VerificationToken.class);
		crit.add(Restrictions.eq("token", verificationToken.getToken()));
		crit.add(Restrictions.eq("verified", false));
		Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		crit.add(Restrictions.ge("expiryDate", timeNow));

		return crit.list().size() > 0;
	}

	/**
	 * Fetch VerificationToken from database with a given token string
	 * 
	 * @param token
	 *            verificationToken token string
	 * @return VerificationToken
	 */
	public VerificationToken getVerificationToken(String token) {
		return (VerificationToken) session()
				.createQuery("from VerificationToken where token = :token")
				.setString("token", token).uniqueResult();
	}

	/**
	 * Set verified to true for the given token
	 * 
	 * @param verificationToken
	 *            verificationToken to be updated
	 */
	public void verify(VerificationToken verificationToken) {
		VerificationToken tempToken = this
				.getVerificationToken(verificationToken.getToken());
		tempToken.setVerified(true);
		session().update(tempToken);

	}

}
