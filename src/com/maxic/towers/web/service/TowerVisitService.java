package com.maxic.towers.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.TowerVisitDao;
import com.maxic.towers.web.model.TowerVisit;

@Service("towerVisitService")
public class TowerVisitService {

	private TowerVisitDao towerVisitDao;

	@Autowired
	public void setTowerVisitServiceDao(TowerVisitDao towerVisitDao) {
		this.towerVisitDao = towerVisitDao;
	}

	public List<TowerVisit> getVisitsByUserId(int userId) {
		return towerVisitDao.getVisitsByUserId(userId);
	}
	
	public TowerVisit getTowerVisit(int id) {
		return towerVisitDao.getTowerVisit(id);
	}
	
	public void addTowerVisit(TowerVisit visit) {
		towerVisitDao.addTowerVisit(visit);
	}

	public void deleteTowerVisit(TowerVisit visit) {
		towerVisitDao.deleteTowerVisit(visit);
	}

	public void editTowerVisit(TowerVisit visit) {
		towerVisitDao.editTowerVisit(visit);
	}

	public int getNumberOfVisits(int userId) {
		return towerVisitDao.getNumberOfVisits(userId);
	}

	public List<TowerVisit> getPaginatedVisits(int userId, int pageLength, int displayLength) {
		return towerVisitDao.getPaginatedVisits(userId, pageLength, displayLength);
	}
}
