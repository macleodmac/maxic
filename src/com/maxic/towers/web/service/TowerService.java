package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.TowerDao;
import com.maxic.towers.web.model.*;

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
	
	public void addTower(Tower tower) {
		towerDao.addTower(tower);
	}

	public Tower getTower(int id) {
		return towerDao.getTower(id);
	}

	public boolean deleteTower(int id) {
		return towerDao.deleteTower(id);
	}

	public void editTower(Tower tower) {
		towerDao.editTower(tower);
		
	}

	public void addTowers(ArrayList<Tower> towerList) {
		towerDao.addTowers(towerList);
	}

	public List<TowerShort> getTowersShort() {
		return towerDao.getTowersShort();
	}

	public List<TowerDescriptor> getTowerDescriptors() {
		return towerDao.getTowerDescriptors();
	}

	public Map<Integer, String> getTowerDescriptorMap() {
		return towerDao.getTowerDescriptorMap();
	}

	public String getTowerDescriptor(int towerId) {
		return towerDao.getTowerDescriptor(towerId);
	}
	
	public List<Tower> getPaginatedTowers(int pageLength, int displayStart) {
		return towerDao.getPaginatedTowers(pageLength, displayStart);
	}

	public int getNumberOfTowers() {
		return towerDao.getNumberOfTowers();
	}

	public List<Tower> getPaginatedTowersByTerm(int pageLength, int displayStart, String searchCriteria) {
		
		return towerDao.getPaginatedTowersByTerm(pageLength, displayStart, searchCriteria);
	}

	public int getNumberOfTowersBySearchTerm(String searchTerm) {
		return towerDao.getNumberOfTowersBySearchTerm(searchTerm);
	}
}
