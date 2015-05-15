package com.maxic.towers.web.dao;

import com.maxic.towers.web.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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

@Transactional
@Component("pealDao")
public class PealDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Fetches all peals from the database
	 * 
	 * @return list of peals
	 */
	@SuppressWarnings("unchecked")
	public List<Peal> getPeals() {
		return session().createQuery("from Peal").list();
	}

	/**
	 * Fetches all peals from the database for a given tower
	 * 
	 * @param towerId
	 * @return list of peals
	 */
	@SuppressWarnings("unchecked")
	public List<Peal> getLatestPealsForTower(int towerId) {
		return session()
				.createQuery(
						"from Peal where towerId = :towerId order by dateRung desc")
				.setInteger("towerId", towerId).setMaxResults(5).list();
	}

	/**
	 * Fetches 5 latest peals from the database for a given tower
	 * 
	 * @param towerId
	 * @return list of peals
	 */
	@SuppressWarnings("unchecked")
	public List<Peal> getPealsForTower(int towerId) {
		return session()
				.createQuery(
						"from Peal where towerId = :towerId order by dateRung desc")
				.setInteger("towerId", towerId).list();
	}

	/**
	 * Fetches peal from the database for a given pealId
	 * 
	 * @param pealId
	 * @return Peal
	 */
	public Peal getPeal(int id) {

		Criteria crit = session().createCriteria(Peal.class);
		crit.add(Restrictions.idEq(id));
		Peal peal = (Peal) crit.uniqueResult();

		return peal;

	}

	/**
	 * Persists peal to database
	 * 
	 * @param peal
	 *            to persist
	 */
	public void addPeal(Peal peal) {
		session().saveOrUpdate(peal);
	}

	/**
	 * Delete peal details from the database for a given pealId
	 * 
	 * 
	 * @param pealId
	 *            to delete
	 * @return boolean success
	 */
	public boolean deletePeal(int id) {
		Criteria crit = session().createCriteria(Peal.class);
		crit.add(Restrictions.idEq(id));
		try {
			session().delete(crit.uniqueResult());
			return true;
		} catch (HibernateException e) {
			return false;
		}

	}

	/**
	 * Update given peal in the database
	 * 
	 * 
	 * @param peal
	 *            to update
	 */
	public void editPeal(Peal peal) {
		session().update(peal);
	}

	/**
	 * Persists peal list to database
	 * 
	 * @param list
	 *            of peals to persist
	 */
	public boolean addPeals(ArrayList<Peal> pealList) {
		for (Peal peal : pealList) {
			this.addPeal(peal);
		}
		return false;
	}

	/**
	 * Checks how many peal records there are in the database
	 * 
	 * @return int total number of peals
	 */
	public int getNumberOfPeals() {

		int count = ((Long) session().createCriteria(Peal.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
		return count;
	}

	/**
	 * Returns paginated peals with a limited number of results and first result
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @return list of peals
	 */
	@SuppressWarnings("unchecked")
	public List<Peal> getPaginatedPeals(int pageLength, int displayStart) {
		Criteria crit = session().createCriteria(Peal.class);
		crit.addOrder(Order.desc("dateRung"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);

		return crit.list();
	}

	/**
	 * Returns paginated peals with a limited number of results and first result
	 * for a given tower
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @param towerId
	 *            to fetch
	 * @return list of peals
	 */
	@SuppressWarnings("unchecked")
	public List<Peal> getPaginatedPealsForTower(int towerId, int pageLength,
			int displayStart) {
		Criteria crit = session().createCriteria(Peal.class);
		crit.add(Restrictions.eq("tower.id", towerId));
		crit.addOrder(Order.desc("dateRung"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);

		return crit.list();
	}

	/**
	 * Checks how many peal records there are in the database for a given tower
	 * id
	 * 
	 * @param towerId
	 *            to check
	 * @return int total number of peals
	 */
	public int getNumberPealsForTower(int towerId) {
		Criteria crit = session().createCriteria(Peal.class);
		crit.add(Restrictions.eq("tower.id", towerId));

		return crit.list().size();
	}

	/**
	 * Returns paginated peals with a limited number of results and first result
	 * for a given tower
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @param towerId
	 *            to fetch
	 * @param dateFrom
	 *            start date of search
	 * @param dateTo
	 *            end date of search
	 * @param ringer
	 *            ringer name
	 * @return list of peals
	 */
	@SuppressWarnings("unchecked")
	public List<Peal> getPaginatedPealsForTower(int towerId, Date dateFrom,
			Date dateTo, String ringer, int pageLength, int displayStart) {
		Criteria crit = session().createCriteria(Peal.class);
		if (towerId != 0) {
			crit.add(Restrictions.eq("tower.id", towerId));
		}
		Disjunction or = Restrictions.disjunction();
		if (!ringer.isEmpty()) {
			for (int i = 1; i <= 16; i++) {
				or.add(Restrictions.ilike("ringer" + i, '%' + ringer + '%',
						MatchMode.ANYWHERE));
			}
			crit.add(or);
		}

		crit.add(Restrictions.between("dateRung", dateFrom, dateTo));
		crit.addOrder(Order.desc("dateRung"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);
		return crit.list();
	}

	/**
	 * Returns number of peals for a given search
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @param towerId
	 *            to fetch
	 * @param dateFrom
	 *            start date of search
	 * @param dateTo
	 *            end date of search
	 * @param ringer
	 *            ringer name
	 * @return int total number of peals
	 */
	public int getNumberPealsForTower(int towerId, Date dateFrom, Date dateTo,
			String ringer) {
		Criteria crit = session().createCriteria(Peal.class);
		if (towerId != 0) {
			crit.add(Restrictions.eq("tower.id", towerId));
		}
		Disjunction or = Restrictions.disjunction();
		if (!ringer.isEmpty()) {
			for (int i = 1; i <= 16; i++) {
				or.add(Restrictions.ilike("ringer" + i, '%' + ringer + '%',
						MatchMode.ANYWHERE));
			}
			crit.add(or);
		}
		crit.add(Restrictions.ge("dateRung", dateFrom));
		crit.add(Restrictions.le("dateRung", dateTo));

		return crit.list().size();
	}

	/**
	 * Check whether a bellboard id is already in the peal database
	 * 
	 * @param pealId
	 *            the id to check
	 * @return boolean true/false
	 */
	public boolean bellboardPealExists(int pealId) {
		Criteria crit = session().createCriteria(Peal.class);
		crit.add(Restrictions.eq("ringingWorldId", pealId));

		return crit.list().size() == 1;
	}

}
