package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.TowerVisit;
import com.maxic.towers.web.dao.TowerVisitDao;


public class TowerVisitService {

	private TowerVisitDao towerVisitDao;

	@Autowired
	public void setTowerVisitServiceDao(TowerVisitDao towerVisitDao) {
		this.towerVisitDao = towerVisitDao;
	}

	public List<TowerVisit> getTowerVisits() {
		return towerVisitDao.getTowerVisits();
	}
	
	public boolean addTowerVisit(TowerVisit towerVisit) {
		return towerVisitDao.addTowerVisit(towerVisit);
	}

	public TowerVisit getTowerVisit(int id) {
		return towerVisitDao.getTowerVisit(id);
	}

	public boolean deleteTower(int id) {
		return towerVisitDao.deleteTowerVisit(id);
	}

	public boolean editTower(TowerVisit towerVisit) {
		return towerVisitDao.editTowerVisit(towerVisit);
		
	}

	public boolean addTowers(ArrayList<TowerVisit> towerVisitList) {
		return towerVisitDao.addTowerVisit(towerVisitList);
	}
}
