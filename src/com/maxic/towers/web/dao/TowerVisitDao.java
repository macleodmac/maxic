package com.maxic.towers.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.maxic.towers.web.model.TowerVisit;

@Transactional
@Component("towerVisitDao")
public class TowerVisitDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Fetch visits from database for a give userId
	 * 
	 * @param userId
	 *            ther userId to check
	 * @return list of visits
	 */
	@SuppressWarnings("unchecked")
	public List<TowerVisit> getVisitsByUserId(int userId) {
		Criteria crit = session().createCriteria(TowerVisit.class);
		crit.add(Restrictions.eq("userId", userId));
		List<TowerVisit> visits = (List<TowerVisit>) crit.list();
		return visits;
	}

	/**
	 * Fetch visit from database with a given id
	 * 
	 * @param id
	 *            visit id
	 * @return visit
	 */
	public TowerVisit getTowerVisit(int id) {

		Criteria crit = session().createCriteria(TowerVisit.class);
		crit.add(Restrictions.idEq(id));
		TowerVisit visit = (TowerVisit) crit.uniqueResult();

		return visit;
	}

	/**
	 * Persists visit to database
	 * 
	 * @param visit
	 *            to persist
	 */
	public void addTowerVisit(TowerVisit visit) {
		session().save(visit);
	}

	/**
	 * Delete visit details from the database for a given visitId
	 * 
	 * 
	 * @param visitId
	 *            to delete
	 * @return boolean success
	 */
	public void deleteTowerVisit(TowerVisit visit) {
		session().delete(visit);
	}

	/**
	 * Update given visit in the database
	 * 
	 * 
	 * @param visit
	 *            to update
	 */
	public void editTowerVisit(TowerVisit visit) {
		session().update(visit);
	}

	/**
	 * Returns total number of visits that match a given userId
	 * 
	 * @param userId
	 *            the userId being checked
	 * @return int number of visits
	 */
	public int getNumberOfVisits(int userId) {
		Criteria crit = session().createCriteria(TowerVisit.class);
		crit.add(Restrictions.eq("userId", userId));
		return crit.list().size();
	}

	/**
	 * Returns paginated visits with a limited number of results and first
	 * result for a given userId
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @param userId
	 *            to check
	 * @return list of tower visits
	 */
	@SuppressWarnings("unchecked")
	public List<TowerVisit> getPaginatedVisits(int userId, int pageLength,
			int displayStart) {
		Criteria crit = session().createCriteria(TowerVisit.class);
		crit.add(Restrictions.eq("userId", userId));
		crit.addOrder(Order.desc("date"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);
		return crit.list();
	}

}
