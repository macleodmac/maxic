package com.maxic.towers.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.VerificationDao;
import com.maxic.towers.web.model.VerificationToken;

@Service("verificationService")
public class VerificationService {

	private VerificationDao verificationDao;

	@Autowired
	public void setVerificationDao(VerificationDao verificationDao) {
		this.verificationDao = verificationDao;
	}

	public void addToken(VerificationToken verificationToken) {
		verificationDao.addVerificationToken(verificationToken);
	}

	public boolean validToken(VerificationToken verificationToken) {
		return verificationDao.validToken(verificationToken);
	}

	public VerificationToken getVerificationToken(String token) {
		return verificationDao.getVerificationToken(token);
	}

	public void verify(VerificationToken verificationToken) {
		verificationDao.verify(verificationToken);
	}

}
