package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.model.*;
import com.maxic.towers.web.dao.RingerDao;

@Service("ringerService")
public class RingerService {
	private RingerDao ringerDao;

	@Autowired
	public void setRingerDao(RingerDao ringerDao) {
		this.ringerDao = ringerDao;
	}

	public List<Ringer> getRingers() {
		return ringerDao.getRingers();
	}
	
	public boolean addRinger(Ringer ringer) {
		return ringerDao.addRinger(ringer);
	}

	public Ringer getRinger(int id) {
		return ringerDao.getRinger(id);
	}

	public boolean deleteRinger(int id) {
		return ringerDao.deleteRinger(id);
	}

	public boolean editRinger(Ringer ringer) {
		return ringerDao.editRinger(ringer);
		
	}

	public boolean addRingers(ArrayList<Ringer> ringerList) {
		return ringerDao.addRingers(ringerList);
	}
}
