package com.maxic.towers.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.Tower;
import com.maxic.towers.web.dao.TowerDao;

@Service("towerService")
public class TowerService {

	private TowerDao towerDao;

	@Autowired
	public void setTowerDao(TowerDao towerDao) {
		this.towerDao = towerDao;
	}

	public List<Tower> getTowers() {
		return towerDao.getTowers();
	}
	
	public boolean addTower(Tower tower) {
		return towerDao.addTower(tower);
	}

	public Tower getTower(int id) {
		return towerDao.getTower(id);
	}

	public boolean deleteTower(int id) {
		return towerDao.deleteTower(id);
	}
}
