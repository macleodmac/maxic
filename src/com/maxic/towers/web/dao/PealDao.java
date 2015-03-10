package com.maxic.towers.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("pealDao")
public class PealDao {
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
	public List<Peal> getPeals() {
		return session().createQuery("from Peal").list();
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
		String hql = "delete from Peal where pealId = :id";
		return session().createQuery(hql).setInteger("id", id).executeUpdate() == 1;

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

}
