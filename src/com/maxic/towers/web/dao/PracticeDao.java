package com.maxic.towers.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	@SuppressWarnings("unchecked")
	public List<Practice> getAllPractices() {
		return session().createQuery("from Practice").list();
	}

	public List<Practice> getPractices(int id) {
		return (List<Practice>) session()
				.createQuery("from Practice where towerId = :id")
				.setInteger("id", id).list();
	}

	public void addPractice(Practice practice) {
		session().saveOrUpdate(practice);
	}

	public boolean practicesExist(int id) {
		return this.getPractices(id).size() != 0;
	}

	public boolean deletePractice(int practiceId) {
		String hql = "delete from Practice where practiceId = :practiceId";
		return session().createQuery(hql).setInteger("practiceId", practiceId)
				.executeUpdate() == 1;
	}

	public void editPractice(Practice practice) {
		String hql = "update from Practice where practiceId = :practiceId and towerId = :towerId";
		session().createQuery(hql).setInteger("practiceId", practice.getPracticeId()).setInteger("towerId", practice.getTowerId()).executeUpdate();
	}

	public void addPractice(ArrayList<Practice> practiceList) {
		for (Practice practice : practiceList) {
			this.addPractice(practice);
		}
	}

}
