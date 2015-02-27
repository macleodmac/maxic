package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;

import com.maxic.towers.web.dao.Practice;
import com.maxic.towers.web.dao.PracticeDao;


public class PracticeService {
	
	private PracticeDao practiceDao;
	
	public void setPracticeDao(PracticeDao practiceDao) {
		this.practiceDao = practiceDao;
	}

	public List<Practice> getPractice() {
		return practiceDao.getPractice();
	}
	
	public boolean addPractice(Practice practice) {
		return practiceDao.addPractice(practice);
	}

	public Practice getPractice(int id) {
		return practiceDao.getPractice(id);
	}

	public boolean deletePractice(int id) {
		return practiceDao.deletePractice(id);
	}

	public boolean editPractice(Practice practice) {
		return practiceDao.editPractice(practice);
		
	}

	public boolean addPractice(ArrayList<Practice> practiceList) {
		return practiceDao.addPractice(practiceList);
	}
}
