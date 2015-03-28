package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.Peal;
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
}
