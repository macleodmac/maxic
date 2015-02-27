package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.County;
import com.maxic.towers.web.dao.CountyDao;



@Service("countyService")
public class CountyService {
	private CountyDao countyDao;

	@Autowired
	public void setCountyDao(CountyDao countyDao) {
		this.countyDao = countyDao;
	}

	public List<County> getCounties() {
		return countyDao.getCounties();
	}
	
	public boolean addCounty(County county) {
		return countyDao.addCounty(county);
	}

	public County getCounty(int id) {
		return countyDao.getCounty(id);
	}

	public boolean deleteCounty(int id) {
		return countyDao.deleteCounty(id);
	}

	public boolean editCounty(County county) {
		return countyDao.editCounty(county);
		
	}

	public boolean addCounties(ArrayList<County> countyList) {
		return countyDao.addCounties(countyList);
	}
}
