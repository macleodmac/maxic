package com.maxic.towers.web.dao;

import com.maxic.towers.web.model.*;
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
@Component("dioceseDao")
public class DioceseDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Fetches all dioceses from the database
	 * 
	 * @return list of dioceses
	 */
	@SuppressWarnings("unchecked")
	public List<Diocese> getDioceses() {

		return session().createQuery("from Diocese d order by d.name").list();
	}

	/**
	 * Fetches diocese from the database for a given dioceseId
	 * 
	 * @param dioceseId
	 * @return Diocese
	 */
	public Diocese getDiocese(String id) {

		Criteria crit = session().createCriteria(Diocese.class);
		crit.add(Restrictions.idEq(id));
		Diocese diocese = (Diocese) crit.uniqueResult();
		return diocese;
	}

	/**
	 * Check that diocese exists for a given dioceseId
	 * 
	 * 
	 * @param dioceseId
	 * @return boolean
	 */
	public boolean dioceseExists(String id) {

		Criteria crit = session().createCriteria(Diocese.class);
		crit.add(Restrictions.idEq(id));
		Diocese diocese = (Diocese) crit.uniqueResult();

		return diocese != null;
	}

	/**
	 * Persists diocese to database
	 * 
	 * @param diocese
	 *            to persist
	 */
	public void addDiocese(Diocese diocese) {
		session().saveOrUpdate(diocese);

	}

	/**
	 * Delete diocese details from the database for a given dioceseId
	 * 
	 * 
	 * @param dioceseId
	 *            to delete
	 * @return boolean success
	 */
	public boolean deleteDiocese(String id) {

		String hql = "delete from Diocese where isoCode = :id";
		return session().createQuery(hql).setString("id", id).executeUpdate() == 1;

	}

	/**
	 * Update given diocese in the database
	 * 
	 * 
	 * @param diocese
	 *            to update
	 */
	public void editDiocese(Diocese diocese) {
		session().update(diocese);
	}

	/**
	 * Persists diocese list to database
	 * 
	 * @param list
	 *            of dioceses to persist
	 */
	public void addDioceses(ArrayList<Diocese> dioceseList) {
		for (Diocese diocese : dioceseList) {
			this.addDiocese(diocese);
		}
	}

	/**
	 * Fetches map of dioceses from database
	 * 
	 * @return hashMap of diocese id and names
	 */
	public Map<String, String> getDioceseMap() {
		List<Diocese> dioceses = this.getDioceses();
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		for (Diocese diocese : dioceses) {
			hm.put(diocese.getDioceseId(), diocese.getName());
		}
		return hm;
	}
}
