package com.maxic.towers.web.dao;

import com.maxic.towers.web.model.*;

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
@Component("practiceDao")
public class PracticeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Fetch all practices from database
	 * 
	 * @return list of all practices
	 */
	@SuppressWarnings("unchecked")
	public List<Practice> getAllPractices() {
		return session().createQuery("from Practice").list();
	}

	/**
	 * Fetch practices from database with a given towerId
	 * 
	 * @param towerId
	 *            towerId of the given tower
	 * @return list of practices
	 */
	@SuppressWarnings("unchecked")
	public List<Practice> getPractices(int towerId) {
		return (List<Practice>) session()
				.createQuery("from Practice where towerId = :id")
				.setInteger("id", towerId).list();
	}

	/**
	 * Fetch practice from database with a given id
	 * 
	 * @param id
	 *            practice id
	 * @return practice
	 */
	public Practice getPractice(int id) {

		Criteria crit = session().createCriteria(Practice.class);
		crit.add(Restrictions.idEq(id));
		Practice practice = (Practice) crit.uniqueResult();

		return practice;
	}

	/**
	 * Persists country to database
	 * 
	 * @param country
	 *            to persist
	 */
	public void addPractice(Practice practice) {
		session().saveOrUpdate(practice);
	}

	/**
	 * Check that practices exist for a given towerId
	 * 
	 * 
	 * @param towerId
	 * @return boolean
	 */
	public boolean practicesExist(int towerId) {
		return this.getPractices(towerId).size() != 0;
	}

	/**
	 * Delete practice details from the database for a given practiceId
	 * 
	 * 
	 * @param practiceId
	 *            to delete
	 * @return boolean success
	 */
	public boolean deletePractice(int practiceId) {
		String hql = "delete from Practice where practiceId = :practiceId";
		return session().createQuery(hql).setInteger("practiceId", practiceId)
				.executeUpdate() == 1;
	}

	/**
	 * Update given practice in the database
	 * 
	 * 
	 * @param practice
	 *            to update
	 */
	public void editPractice(Practice practice) {
		session().update(practice);
	}

	/**
	 * Persists practice list to database
	 * 
	 * @param list
	 *            of practices to persist
	 */
	public void addPractice(ArrayList<Practice> practiceList) {
		for (Practice practice : practiceList) {
			this.addPractice(practice);
		}
	}

	/**
	 * Check if a practice exists for a given tower and night
	 * 
	 * @param towerId
	 *            the id of the tower to be checked
	 * @param day
	 *            the night that is being checked
	 * @return boolean true/false
	 */
	public boolean existsByNight(int towerId, String day) {
		Criteria crit = session().createCriteria(Practice.class);
		crit.add(Restrictions.eq("towerId", towerId));
		crit.add(Restrictions.eq("day", day));
		return crit.list().size() == 1;
	}

}
