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

	@SuppressWarnings("unchecked")
	public List<Practice> getAllPractices() {
		return session().createQuery("from Practice").list();
	}

	@SuppressWarnings("unchecked")
	public List<Practice> getPractices(int towerId) {
		return (List<Practice>) session()
				.createQuery("from Practice where towerId = :id")
				.setInteger("id", towerId).list();
	}

	public Practice getPractice(int id) {

		Criteria crit = session().createCriteria(Practice.class);
		crit.add(Restrictions.idEq(id));
		Practice practice = (Practice) crit.uniqueResult();

		return practice;
	}
	
	public void addPractice(Practice practice) {
		session().saveOrUpdate(practice);
	}

	
	public boolean practicesExist(int towerId) {
		return this.getPractices(towerId).size() != 0;
	}

	public boolean deletePractice(int practiceId) {
		String hql = "delete from Practice where practiceId = :practiceId";
		return session().createQuery(hql).setInteger("practiceId", practiceId)
				.executeUpdate() == 1;
	}

	public void editPractice(Practice practice) {
		session().update(practice);
	}

	public void addPractice(ArrayList<Practice> practiceList) {
		for (Practice practice : practiceList) {
			this.addPractice(practice);
		}
	}

	public boolean existsByNight(int towerId, String day) {
		Criteria crit = session().createCriteria(Practice.class);
		crit.add(Restrictions.eq("towerId", towerId));
		crit.add(Restrictions.eq("day", day));
		return crit.list().size() == 1;
	}

}
