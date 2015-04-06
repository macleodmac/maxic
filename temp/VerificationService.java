package com.maxic.towers.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.verification.VerificationToken;

@Service("verificationService")
public class VerificationService {

	private VerificationDao verificationDao;

	@Autowired
	public void setVerificationDao(VerificationDao verificationDao) {
		this.verificationDao = verificationDao;
	}


	public void addUser(VerificationToken verificationToken) {
		verificationDao.addVerificationToken(verificationToken);
	}
	
}
