package com.maxic.towers.web.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.maxic.towers.web.model.Tower;
import com.maxic.towers.web.model.TowerDescriptor;
import com.maxic.towers.web.model.TowerShort;

@Transactional
@Component("towerDao")
public class TowerDao {

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
		
		return this.getDescriptors(towers);
	}
	
	public TowerDescriptor getTowerDescriptor(int towerId) {
		Tower tower = this.getTower(towerId);
		StringBuffer sb = new StringBuffer();
		sb.append(tower.getPlaceName());
		if (tower.getPlaceName2() != null
				&& !tower.getPlaceName2().equals("")) {
			sb.append(", " + tower.getPlaceName2());
		}
		if (tower.getDedication() != null
				&& !tower.getDedication().equals("")) {
			sb.append(", " + tower.getDedication());
		}
		TowerDescriptor towerDescriptor = new TowerDescriptor(
				tower.getTowerId(), sb.toString());
		
		return towerDescriptor;
	}
	
	public List<TowerDescriptor> getPaginatedTowerDescriptors(int pageLength, int displayStart) {

		List<Tower> towers = this.getPaginatedTowers(pageLength, displayStart);
		
		return this.getDescriptors(towers);
	}
	
	public List<TowerDescriptor> getPaginatedTowerDescriptorsBySearchTerm(int pageLength, int displayStart, String searchTerm) {

		List<Tower> towers = this.getPaginatedTowersByTerm(pageLength, displayStart, searchTerm);
		
		return this.getDescriptors(towers);
	}
	
	private List<TowerDescriptor> getDescriptors(List<Tower> towers) {
		List<TowerDescriptor> towerDescriptors = new ArrayList<TowerDescriptor>();
		for (Tower tower : towers) {
			StringBuffer sb = new StringBuffer();
			sb.append(tower.getPlaceName());
			if (tower.getPlaceName2() != null
					&& !tower.getPlaceName2().equals("")) {
				sb.append(", " + tower.getPlaceName2());
			}
			if (tower.getDedication() != null
					&& !tower.getDedication().equals("")) {
				sb.append(", " + tower.getDedication());
			}
			TowerDescriptor towerDescriptor = new TowerDescriptor(
					tower.getTowerId(), sb.toString());
			towerDescriptors.add(towerDescriptor);
		}

		return towerDescriptors;
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Tower> getPaginatedTowers(int pageLength, int displayStart) {
		
		Criteria crit = session().createCriteria(Tower.class);
		crit.addOrder(Order.asc("placeName"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);
		
		return crit.list();

	}

	@SuppressWarnings("unchecked")
	public List<Tower> getPaginatedTowersByTerm(int pageLength,
			int displayStart, String searchCriteria) {
		
		Criteria crit = session().createCriteria(Tower.class);
		
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("placeName", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("placeName2", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("placeNameCL", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("doveId", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		
		crit.add(or);
		crit.addOrder(Order.asc("placeName"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);
		
		return crit.list();

	}

	public int getNumberOfTowersBySearchTerm(String searchCriteria) {
		Criteria crit = session().createCriteria(Tower.class);
		
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("placeName", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("placeName2", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("placeNameCL", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("doveId", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		
		crit.add(or);

		return crit.list().size();
	}

	public int getNumberOfTowers() {
		int count = ((Long) session().createCriteria(Tower.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
		return count;
	}

	public Map<Integer, String> getTowerDescriptorMap() {
		List<TowerDescriptor> towers = this.getTowerDescriptors();
		LinkedHashMap<Integer, String> hm = new LinkedHashMap<Integer, String>();
		hm.put(0, "");
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

	public Tower getTowerByDoveId(String doveId) {
		Criteria crit = session().createCriteria(Tower.class);
		crit.add(Restrictions.eq("doveId", doveId));
		Tower tower = (Tower) crit.uniqueResult();
		return tower;
	}

	public void addTower(Tower tower) {
		session().saveOrUpdate(tower);
	}

	public void deleteTower(int id) {
		Criteria crit = session().createCriteria(Tower.class);
		crit.add(Restrictions.idEq(id));
		
		session().delete(crit.uniqueResult());
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

	@SuppressWarnings("unchecked")
	public List<Tower> getMapTowers(String diocese, int minimumBells,
			int maximumBells, boolean ringable, boolean groundFloorRing,
			int results) {

		Criteria crit = session().createCriteria(Tower.class);

		if (!diocese.isEmpty()) {
			crit.add(Restrictions.eq("dioceseId", diocese));
		}

		crit.add(Restrictions.between("bellNumber", minimumBells, maximumBells));

		crit.add(Restrictions.eq("ringable", ringable));
		crit.add(Restrictions.eq("groundFloorRing", groundFloorRing));
		crit.setMaxResults(results);
		System.out.println(crit);
		return (List<Tower>) crit.list();
	}

	public boolean existsByDoveId(String doveId) {
		Criteria crit = session().createCriteria(Tower.class);
		crit.add(Restrictions.eq("doveId", doveId));
		return crit.list().size() == 1;
	}

}
