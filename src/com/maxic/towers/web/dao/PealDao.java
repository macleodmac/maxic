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
	// private NamedParameterJdbcTemplate jdbc;
	//
	// @Autowired
	// public void setDataSource(DataSource jdbc) {
	// this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	// }

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Peal> getPeals() {
		return session().createQuery("from Peal").list();
	}

	@SuppressWarnings("unchecked")
	public List<Peal> getLatestPealsForTower(int towerId) {
		return session()
				.createQuery(
						"from Peal where towerId = :towerId order by dateRung desc")
				.setInteger("towerId", towerId).setMaxResults(5).list();
	}

	@SuppressWarnings("unchecked")
	public List<Peal> getPealsForTower(int towerId) {
		return session()
				.createQuery(
						"from Peal where towerId = :towerId order by dateRung desc")
				.setInteger("towerId", towerId).list();
	}

	public Peal getPeal(int id) {

		Criteria crit = session().createCriteria(Peal.class);
		crit.add(Restrictions.idEq(id));
		Peal peal = (Peal) crit.uniqueResult();

		return peal;

	}

	public void addPeal(Peal peal) {
		session().save(peal);
	}

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

	public void editPeal(Peal peal) {
		session().update(peal);
	}

	public boolean addPeals(ArrayList<Peal> pealList) {
		for (Peal peal : pealList) {
			this.addPeal(peal);
		}
		return false;
	}

	public int getNumberOfPeals() {

		int count = ((Long) session().createCriteria(Peal.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Peal> getPaginatedPeals(int pageLength, int displayStart) {
		Criteria crit = session().createCriteria(Peal.class);
		crit.addOrder(Order.desc("dateRung"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);

		return crit.list();
	}

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

	public int getNumberPealsForTower(int towerId) {
		Criteria crit = session().createCriteria(Peal.class);
		crit.add(Restrictions.eq("tower.id", towerId));

		return crit.list().size();
	}

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
				or.add(Restrictions.ilike("ringer" + i, '%' + ringer + '%', MatchMode.ANYWHERE));
			}
			crit.add(or);
		}

		crit.add(Restrictions.between("dateRung", dateFrom, dateTo));
		crit.addOrder(Order.desc("dateRung"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);
		return crit.list();
	}

	public int getNumberPealsForTower(int towerId, Date dateFrom, Date dateTo,
			String ringer) {
		Criteria crit = session().createCriteria(Peal.class);
		if (towerId != 0) {
			crit.add(Restrictions.eq("tower.id", towerId));
		}
		Disjunction or = Restrictions.disjunction();
		if (!ringer.isEmpty()) {
			for (int i = 1; i <= 16; i++) {
				or.add(Restrictions.ilike("ringer" + i, '%' + ringer + '%', MatchMode.ANYWHERE));
			}
			crit.add(or);
		}
		crit.add(Restrictions.between("dateRung", dateFrom, dateTo));

		return crit.list().size();
	}

}
