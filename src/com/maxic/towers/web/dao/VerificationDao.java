package com.maxic.towers.web.dao;

import java.sql.Date;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	public void addVerificationToken(VerificationToken verificationToken) {
		session().save(verificationToken);
	}

	public boolean validToken(VerificationToken verificationToken) {
		Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());
		return session()
				.createQuery(
						"from VerificationToken where token = :token and verified = :verified and expiryDate >= :date")
				.setString("token", verificationToken.getToken())
				.setBoolean("verified", false).setDate("date", timeNow).list()
				.size() == 1;
	}

	public VerificationToken getVerificationToken(String token) {
		return (VerificationToken) session()
				.createQuery("from VerificationToken where token = :token")
				.setString("token", token).uniqueResult();
	}

	public void verify(VerificationToken verificationToken) {
		VerificationToken tempToken = this
				.getVerificationToken(verificationToken.getToken());
		tempToken.setVerified(true);
		session().update(tempToken);

	}

}
