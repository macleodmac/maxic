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

	/**
	 * Fetches all towers from the database
	 * 
	 * @return list of towers
	 */
	@SuppressWarnings("unchecked")
	public List<Tower> getTowers() {
		return session().createQuery("from Tower").list();
	}

	@SuppressWarnings("unchecked")
	public List<TowerShort> getTowersShort() {
		return session().createQuery("from TowerShort").list();
	}

	/**
	 * Fetches all towers descriptors from the database
	 * 
	 * @return list of towers descriptors
	 */
	public List<TowerDescriptor> getTowerDescriptors() {

		List<Tower> towers = this.getTowers();

		return this.getDescriptors(towers);
	}

	/**
	 * Fetches tower descriptor from the database for a given towerId
	 * 
	 * @param towerId
	 * @return Tower
	 */
	public TowerDescriptor getTowerDescriptor(int towerId) {
		Tower tower = this.getTower(towerId);
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

		return towerDescriptor;
	}

	/**
	 * Returns paginated towers descriptors with a limited number of results and
	 * first result
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @return list of towers descriptors
	 */
	public List<TowerDescriptor> getPaginatedTowerDescriptors(int pageLength,
			int displayStart) {

		List<Tower> towers = this.getPaginatedTowers(pageLength, displayStart);

		return this.getDescriptors(towers);
	}

	/**
	 * Returns paginated towers descriptors with a limited number of results and
	 * first result with search term
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @param searchTerm
	 *            term to match
	 * @return list of towers descriptors
	 */
	public List<TowerDescriptor> getPaginatedTowerDescriptorsBySearchTerm(
			int pageLength, int displayStart, String searchTerm) {

		List<Tower> towers = this.getPaginatedTowersByTerm(pageLength,
				displayStart, searchTerm);

		return this.getDescriptors(towers);
	}

	/**
	 * Returns a list of tower descriptors from a lsit of towers
	 * 
	 * @param towers
	 *            a list of towers
	 * @return list of tower descriptors
	 */
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

	private List<TowerShort> getShorts(List<Tower> towers) {
		List<TowerShort> towerShorts = new ArrayList<TowerShort>();
		for (Tower tower : towers) {

			TowerShort towerShort = new TowerShort(tower.getTowerId(),
					tower.getLatitude(), tower.getLongitude());
			towerShorts.add(towerShort);
		}

		return towerShorts;
	}

	/**
	 * Returns paginated towers with a limited number of results and first
	 * result
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @return list of towers
	 */
	@SuppressWarnings("unchecked")
	public List<Tower> getPaginatedTowers(int pageLength, int displayStart) {

		Criteria crit = session().createCriteria(Tower.class);
		crit.addOrder(Order.asc("placeName"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);

		return crit.list();

	}

	/**
	 * Returns paginated towers with a limited number of results and first
	 * result and search term
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @param searchCriteria
	 *            searchTerm to look for
	 * @return list of towers
	 */
	@SuppressWarnings("unchecked")
	public List<Tower> getPaginatedTowersByTerm(int pageLength,
			int displayStart, String searchCriteria) {

		Criteria crit = session().createCriteria(Tower.class);

		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("placeName", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("placeName2", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("placeNameCL", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("doveId", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));

		crit.add(or);
		crit.addOrder(Order.asc("placeName"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);

		return crit.list();

	}

	/**
	 * Returns total number of towers that match a given search term
	 * 
	 * @param searchCriteria
	 *            the term being searched
	 * @return int number of towers
	 */
	public int getNumberOfTowersBySearchTerm(String searchCriteria) {
		Criteria crit = session().createCriteria(Tower.class);

		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("placeName", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("placeName2", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("placeNameCL", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("doveId", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));

		crit.add(or);

		return crit.list().size();
	}

	/**
	 * Returns total number of towers
	 * 
	 * @return int number of towers
	 */
	public int getNumberOfTowers() {
		int count = ((Long) session().createCriteria(Tower.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
		return count;
	}

	/**
	 * Fetches map of towers from database
	 * 
	 * @return hashMap of tower ids and descriptors
	 */
	public Map<Integer, String> getTowerDescriptorMap() {
		List<TowerDescriptor> towers = this.getTowerDescriptors();
		LinkedHashMap<Integer, String> hm = new LinkedHashMap<Integer, String>();
		hm.put(0, "");
		for (TowerDescriptor tower : towers) {
			hm.put(tower.getId(), tower.getDe());
		}
		return hm;
	}

	/**
	 * Fetches tower from the database for a given towerId
	 * 
	 * @param towerId
	 * @return Tower
	 */
	public Tower getTower(int id) {

		Criteria crit = session().createCriteria(Tower.class);
		crit.add(Restrictions.idEq(id));
		Tower tower = (Tower) crit.uniqueResult();

		return tower;
	}

	/**
	 * Fetches tower from the database for a given doveId
	 * 
	 * @param doveId
	 * @return Tower
	 */
	public Tower getTowerByDoveId(String doveId) {
		Criteria crit = session().createCriteria(Tower.class);
		crit.add(Restrictions.eq("doveId", doveId));
		Tower tower = (Tower) crit.uniqueResult();
		return tower;
	}

	/**
	 * Persists tower to database
	 * 
	 * @param tower
	 *            to persist
	 */
	public void addTower(Tower tower) {
		session().saveOrUpdate(tower);
	}

	/**
	 * Delete tower details from the database for a given towerId
	 * 
	 * 
	 * @param towerId
	 *            to delete
	 * @return boolean success
	 */
	public void deleteTower(int id) {
		Criteria crit = session().createCriteria(Tower.class);
		crit.add(Restrictions.idEq(id));

		session().delete(crit.uniqueResult());
	}

	/**
	 * Update given tower in the database
	 * 
	 * 
	 * @param tower
	 *            to update
	 */
	public void editTower(Tower tower) {
		System.out.println("Got tower at Dao: " + tower.getTowerId());
		System.out.println(tower);
		session().update(tower);
	}

	/**
	 * Persists tower list to database
	 * 
	 * @param list
	 *            of towers to persist
	 */
	@Transactional
	public void addTowers(ArrayList<Tower> towerList) {
		for (Tower tower : towerList) {
			System.out.println(tower);
			this.addTower(tower);
		}
	}

	/**
	 * Gets a list of towers for the map
	 * 
	 * @param diocese
	 *            the diocese to include
	 * @param minimumBells
	 *            the minimum number of bells
	 * @param maximumBells
	 *            the maximum number of bells
	 * @param ringable
	 *            whether to include towers that are not ringable
	 * @param groundFloorRing
	 *            whether to include towers that are not groundFloorRingable
	 * @return list of towers
	 */
	@SuppressWarnings("unchecked")
	public List<TowerShort> getMapTowers(String diocese, int minimumBells,
			int maximumBells, boolean ringable, boolean groundFloorRing) {

		Criteria crit = session().createCriteria(Tower.class);

		if (!diocese.isEmpty()) {
			crit.add(Restrictions.eq("dioceseId", diocese));
		}
		
		if (ringable) {
			crit.add(Restrictions.eq("ringable", true));
		}

		if (groundFloorRing) {
			crit.add(Restrictions.eq("groundFloorRing", true));
		}
		
		crit.add(Restrictions.between("bellNumber", minimumBells, maximumBells));
		
		
		List<TowerShort> towerShorts = this
				.getShorts((List<Tower>) crit.list());

		return towerShorts;
	}

	/**
	 * Check whether a doveId is already in the tower database
	 * 
	 * @param doveId
	 *            the id to check
	 * @return boolean true/false
	 */
	public boolean existsByDoveId(String doveId) {
		Criteria crit = session().createCriteria(Tower.class);
		crit.add(Restrictions.eq("doveId", doveId));
		return crit.list().size() == 1;
	}

}
