package com.maxic.towers.web.dao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	@SuppressWarnings("unchecked")
	public List<TowerVisit> getVisitsByUserId(int userId) {
		Criteria crit = session().createCriteria(TowerVisit.class);
		crit.add(Restrictions.eq("userId", userId));
		List<TowerVisit> visits = (List<TowerVisit>) crit.list();
		//TODO
		return visits;
	}
	
	public TowerVisit getTowerVisit(int id) {

		Criteria crit = session().createCriteria(TowerVisit.class);
		crit.add(Restrictions.idEq(id));
		TowerVisit visit = (TowerVisit) crit.uniqueResult();

		return visit;
	}

	public void addTowerVisit(TowerVisit visit) {
		session().save(visit);
	}

	public void deleteTowerVisit(TowerVisit visit) {
		session().delete(visit);
	}

	public void editTowerVisit(TowerVisit visit) {
		session().update(visit);
	}
	
	
}
