package com.maxic.towers.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.maxic.towers.web.model.User;

@Component("userDao")
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getUsers() {
		return session().createQuery("from User").list();
	}

	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	public boolean exists(User user) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("email", user.getEmail()));
		return crit.list().size() == 1;
	}

	public User getUserById(int id) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(id));
		User user = (User) crit.uniqueResult();

		return user;
	}
	
	public User getUserByEmail(String email) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();

		return user;
	}

	public void disable(User user) {
		User tempUser = this.getUserByEmail(user.getEmail());
		tempUser.setEnabled(false);
		session().update(tempUser);
	}

	public void enable(User user) {
		User tempUser = this.getUserByEmail(user.getEmail());
		tempUser.setEnabled(true);
		session().update(tempUser);
	}

	public void updatePassword(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().update(user);
	}

	public boolean checkPass(String checkString) {
		return session().createQuery("from User where password = :password")
				.setString("password", checkString).list().size() == 1;
	}

	public void update(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().update(user);
	}
	
	public int getNumberofUsers() {
		int count = ((Long) session().createCriteria(User.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
		return count;
	}
	
	public int getNumberOfUsersBySearchTerm(String searchCriteria) {
		Criteria crit = session().createCriteria(User.class);
		
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("email", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("name", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		
		crit.add(or);
	
		return crit.list().size();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getPaginatedUsersByTerm(int pageLength,
			int displayStart, String searchCriteria) {
		
		Criteria crit = session().createCriteria(User.class);
		
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("email", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("name", '%'+searchCriteria+'%', MatchMode.ANYWHERE));
		
		crit.add(or);
		crit.addOrder(Order.asc("name"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);
		
		return crit.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getPaginatedUsers(int pageLength, int displayStart) {
		
		Criteria crit = session().createCriteria(User.class);
		crit.addOrder(Order.asc("name"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);
		
		
		return crit.list();

	}

	public boolean existsById(User user) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(user.getId()));
		return crit.list().size() == 1;
	}


}
