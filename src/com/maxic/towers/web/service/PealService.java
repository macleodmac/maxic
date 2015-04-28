package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.model.*;
import com.maxic.towers.web.dao.PealDao;

@Service("pealService")
public class PealService {
	private PealDao pealDao;

	@Autowired
	public void setPealDao(PealDao pealDao) {
		this.pealDao = pealDao;
	}

	public List<Peal> getPeals() {
		return pealDao.getPeals();
	}
	
	public List<Peal> getLatestPealsForTower(int towerId) {
		return pealDao.getLatestPealsForTower(towerId);
	}
	
	public List<Peal> getPealsForTower(int towerId) {
		return pealDao.getPealsForTower(towerId);
	}
	
	public void addPeal(Peal peal) {
		pealDao.addPeal(peal);
	}

	public Peal getPeal(int id) {
		return pealDao.getPeal(id);
	}

	public boolean deletePeal(int id) {
		return pealDao.deletePeal(id);
	}

	public void editPeal(Peal peal) {
		pealDao.editPeal(peal);
		
	}

	public boolean addPeals(ArrayList<Peal> pealList) {
		return pealDao.addPeals(pealList);
	}

	public int getNumberOfPeals() {
		return pealDao.getNumberOfPeals();
	}

	public List<Peal> getPaginatedPeals(int pageLength, int displayStart) {
		return pealDao.getPaginatedPeals(pageLength, displayStart);
	}

	public List<Peal> getPaginatedPealsForTower(int towerId, int pageLength,
			int displayStart) {
		return pealDao.getPaginatedPealsForTower(towerId, pageLength, displayStart);
	}

	public int getNumberPealsForTower(int towerId) {
		return pealDao.getNumberPealsForTower(towerId);
	}

	public List<Peal> getPaginatedPealsForTower(int towerId, Date dateFrom,
			Date dateTo, String ringer, int pageLength, int displayStart) {
		return pealDao.getPaginatedPealsForTower(towerId, dateFrom, dateTo, ringer, pageLength, displayStart);
	}

	public int getNumberPealsForTower(int towerId, Date dateFrom, Date dateTo, String ringer) {
		return pealDao.getNumberPealsForTower(towerId, dateFrom, dateTo, ringer);
	}
}
