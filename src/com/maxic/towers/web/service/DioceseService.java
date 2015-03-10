package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.Diocese;
import com.maxic.towers.web.dao.DioceseDao;



@Service("dioceseService")
public class DioceseService {
	private DioceseDao dioceseDao;

	@Autowired
	public void setDioceseDao(DioceseDao dioceseDao) {
		this.dioceseDao = dioceseDao;
	}

	public List<Diocese> getDioceses() {
		return dioceseDao.getDioceses();
	}
	
	public void addDiocese(Diocese diocese) {
		dioceseDao.addDiocese(diocese);
	}

	public Diocese getDiocese(String id) {
		return dioceseDao.getDiocese(id);
	}

	public boolean deleteDiocese(String id) {
		return dioceseDao.deleteDiocese(id);
	}

	public void editDiocese(Diocese diocese) {
		dioceseDao.editDiocese(diocese);
		
	}

	public void addDioceses(ArrayList<Diocese> dioceseList) {
		dioceseDao.addDioceses(dioceseList);
	}

	public boolean dioceseExists(String id) {
		return dioceseDao.dioceseExists(id);
	}

	public Map<String, String> getDioceseMap() {
		return dioceseDao.getDioceseMap();
	}
}
