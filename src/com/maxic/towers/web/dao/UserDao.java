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

	/**
	 * Fetches all users from the database
	 * 
	 * @return list of all users
	 */
	@SuppressWarnings("unchecked")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> getUsers() {
		return session().createQuery("from User").list();
	}

	/**
	 * Persists user to database
	 * 
	 * @param user
	 *            to persist
	 */
	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	/**
	 * Check whether a user is already in the user database
	 * 
	 * @param user
	 *            the user to check
	 * @return boolean true/false
	 */
	public boolean exists(User user) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("email", user.getEmail()));
		return crit.list().size() == 1;
	}

	/**
	 * Get a user from the database based on their userId
	 * 
	 * @param userId
	 *            the userId to check
	 * @return User
	 */
	public User getUserById(int id) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(id));
		User user = (User) crit.uniqueResult();

		return user;
	}

	/**
	 * Get a user from the database based on their email
	 * 
	 * @param email
	 *            the email to check
	 * @return User
	 */
	public User getUserByEmail(String email) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();

		return user;
	}

	/**
	 * Set the given user to be 'disabled'
	 * 
	 * @param user
	 *            to disable
	 */
	public void disable(User user) {
		User tempUser = this.getUserByEmail(user.getEmail());
		tempUser.setEnabled(false);
		session().update(tempUser);
	}

	/**
	 * Set the given user to be 'enabled'
	 * 
	 * @param user
	 *            to enable
	 */

	public void enable(User user) {
		User tempUser = this.getUserByEmail(user.getEmail());
		tempUser.setEnabled(true);
		session().update(tempUser);
	}

	/**
	 * Update the password for the given user
	 * 
	 * @param user
	 *            to have password updated
	 */

	public void updatePassword(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().update(user);
	}

	/**
	 * Update the given user in the database
	 * 
	 * @param user
	 *            to be updated
	 */

	public void update(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().update(user);
	}

	/**
	 * Update the given user in the database without re-encoding their password
	 * 
	 * @param user
	 *            to be updated
	 */
	public void updateNoPassEncode(User user) {
		session().update(user);
	}

	/**
	 * Returns total number of users
	 * 
	 * @return int number of users
	 */
	public int getNumberofUsers() {
		int count = ((Long) session().createCriteria(User.class)
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
		return count;
	}

	/**
	 * Returns total number of users that match a given search term
	 * 
	 * @param searchCriteria
	 *            the term being searched
	 * @return int number of users
	 */
	public int getNumberOfUsersBySearchTerm(String searchCriteria) {
		Criteria crit = session().createCriteria(User.class);

		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("email", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("name", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));

		crit.add(or);

		return crit.list().size();
	}

	/**
	 * Returns paginated towers with a limited number of results and first
	 * result and search term
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @param searchCriteria
	 *            searchTerm to look for
	 * @return list of users
	 */
	@SuppressWarnings("unchecked")
	public List<User> getPaginatedUsersByTerm(int pageLength, int displayStart,
			String searchCriteria) {

		Criteria crit = session().createCriteria(User.class);

		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.ilike("email", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));
		or.add(Restrictions.ilike("name", '%' + searchCriteria + '%',
				MatchMode.ANYWHERE));

		crit.add(or);
		crit.addOrder(Order.asc("name"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);

		return crit.list();

	}

	/**
	 * Returns paginated users with a limited number of results and first
	 * result
	 * 
	 * @param pageLength
	 *            number of records to return
	 * @param displayStart
	 *            record to start from
	 * @return list of users
	 */
	@SuppressWarnings("unchecked")
	public List<User> getPaginatedUsers(int pageLength, int displayStart) {

		Criteria crit = session().createCriteria(User.class);
		crit.addOrder(Order.asc("name"));
		crit.setFirstResult(displayStart);
		crit.setMaxResults(pageLength);

		return crit.list();

	}

	/**
	 * Check whether a userId is already in the tower database
	 * 
	 * @param user
	 *            the user to check
	 * @return boolean true/false
	 */
	public boolean existsById(User user) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(user.getId()));
		return crit.list().size() == 1;
	}

}
