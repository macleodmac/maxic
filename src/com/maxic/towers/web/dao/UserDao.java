package com.maxic.towers.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<User> getUsers() {
		return session().createQuery("from User").list();
	}

	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	public boolean exists(User user) {
		return session().createQuery("from User where email = :email")
				.setString("email", user.getEmail()).list().size() == 1;
	}

	public User getUser(String email) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(email));
		User user = (User) crit.uniqueResult();

		return user;
	}

	public void disable(User user) {
		User tempUser = this.getUser(user.getEmail());
		tempUser.setEnabled(false);
		session().update(tempUser);
	}

	public void enable(User user) {
		User tempUser = this.getUser(user.getEmail());
		tempUser.setEnabled(true);
		session().update(tempUser);
	}

	public void updatePassword(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().update(user);
	}
}
