package com.maxic.towers.web.dao;

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

	@SuppressWarnings("unchecked")
	public List<Diocese> getDioceses() {

		return session().createQuery("from Diocese d order by d.name").list();
	}

	public Diocese getDiocese(String id) {

		Criteria crit = session().createCriteria(Diocese.class);
		crit.add(Restrictions.idEq(id));
		Diocese diocese = (Diocese) crit.uniqueResult();

		return diocese;
	}

	public boolean dioceseExists(String id) {

		Criteria crit = session().createCriteria(Diocese.class);
		crit.add(Restrictions.idEq(id));
		Diocese diocese = (Diocese) crit.uniqueResult();

		return diocese != null;
	}

	public void addDiocese(Diocese diocese) {
		session().save(diocese);

	}

	public boolean deleteDiocese(String id) {

		String hql = "delete from Diocese where isoCode = :id";
		return session().createQuery(hql).setString("id", id).executeUpdate() == 1;

	}

	public void editDiocese(Diocese diocese) {
		session().update(diocese);
	}

	public void addDioceses(ArrayList<Diocese> dioceseList) {
		for (Diocese diocese : dioceseList) {
			this.addDiocese(diocese);
		}
	}

	public Map<String, String> getDioceseMap() {
		List<Diocese> dioceses = this.getDioceses();
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		for (Diocese diocese : dioceses) {
			hm.put(diocese.getDioceseId(), diocese.getName());
		}
		return hm;
	}
}
