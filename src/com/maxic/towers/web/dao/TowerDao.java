package com.maxic.towers.web.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("towerDao")
public class TowerDao {

//	private NamedParameterJdbcTemplate jdbc;
//
//	@Autowired
//	public void setDataSource(DataSource jdbc) {
//		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
//	}

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tower> getTowers() {
		return session().createQuery("from Tower").list();
	}

	@SuppressWarnings("unchecked")
	public List<TowerShort> getTowersShort() {
		return session().createQuery("from TowerShort").list();
	}

	public List<TowerDescriptor> getTowerDescriptors() {

		List<Tower> towers = this.getTowers();
		List<TowerDescriptor> towerDescriptors = new ArrayList<TowerDescriptor>();
		for (Tower tower : towers) {
			StringBuffer sb = new StringBuffer();
			sb.append(tower.getPlaceName());
			if (tower.getPlaceName2() != null && !tower.getPlaceName2().equals("")) {
				sb.append(", " + tower.getPlaceName2());
			}
			if (tower.getDedication() != null && !tower.getDedication().equals("")) {
				sb.append(", " + tower.getDedication());
			}
			TowerDescriptor towerDescriptor = new TowerDescriptor(
					tower.getTowerId(), sb.toString());
			towerDescriptors.add(towerDescriptor);
		}
		
		return towerDescriptors;
	}
	
	public String getTowerDescriptor(int id) {
		Tower tower = this.getTower(id);
		StringBuffer sb = new StringBuffer();
		sb.append(tower.getPlaceName());
		if (tower.getPlaceName2() != null && !tower.getPlaceName2().equals("")) {
			sb.append(", " + tower.getPlaceName2());
		}
		if (tower.getDedication() != null && !tower.getDedication().equals("")) {
			sb.append(", " + tower.getDedication());
		}

		return sb.toString();
	}

	public Map<Integer, String> getTowerDescriptorMap() {
		List<TowerDescriptor> towers = this.getTowerDescriptors();
		LinkedHashMap<Integer, String> hm = new LinkedHashMap<Integer, String>();
		for (TowerDescriptor tower : towers) {
			hm.put(tower.getId(), tower.getDe());
		}
		return hm;
	}
	
	public Tower getTower(int id) {

		Criteria crit = session().createCriteria(Tower.class);
		crit.add(Restrictions.idEq(id));
		Tower tower = (Tower) crit.uniqueResult();
		
		return tower;
	}

	public void addTower(Tower tower) {
		session().save(tower);
	}

	public boolean deleteTower(int id) {
		String hql = "delete from Tower where `towerId` = :id";
		return session().createQuery(hql).setInteger("id", id).executeUpdate() == 1;
	}

	public void editTower(Tower tower) {
		System.out.println("Got tower at Dao: " + tower.getTowerId());
		System.out.println(tower);
		session().update(tower);
	}

	@Transactional
	public void addTowers(ArrayList<Tower> towerList) {
		for (Tower tower : towerList) {
			System.out.println(tower);
			this.addTower(tower);
		}
	}

}
