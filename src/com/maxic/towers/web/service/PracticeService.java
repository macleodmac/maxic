package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.Practice;
import com.maxic.towers.web.dao.PracticeDao;

@Service("practiceService")
public class PracticeService {
	
	private PracticeDao practiceDao;
	
	@Autowired
	public void setPracticeDao(PracticeDao practiceDao) {
		this.practiceDao = practiceDao;
	}

	public List<Practice> getAllPractices() {
		return practiceDao.getAllPractices();
	}
	
	public void addPractice(Practice practice) {
		practiceDao.addPractice(practice);
	}

	public List<Practice> getPractices(int id) {
		return practiceDao.getPractices(id);
	}

	public boolean deletePractice(int id) {
		return practiceDao.deletePractice(id);
	}

	public void editPractice(Practice practice) {
		practiceDao.editPractice(practice);
		
	}
	
	public boolean practicesExist(int id) {
		return practiceDao.practicesExist(id);
	}
	

	public void addPractice(ArrayList<Practice> practiceList) {
		practiceDao.addPractice(practiceList);
	}
}
