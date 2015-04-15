package com.maxic.towers.web.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.UserDao;
import com.maxic.towers.web.model.User;

@Service("userService")
public class UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}
	
	public boolean exists(User user) {
		return userDao.exists(user);
	}

	public User getUser(String email) {
		return userDao.getUserByEmail(email);
	}
	
	public User getUser(int id) {
		return userDao.getUserById(id);
	}

	public void disable(User user) {
		userDao.disable(user);
	}

	public void enable(User user) {
		userDao.enable(user);
	}

	public void updatePassword(User user) {
		userDao.updatePassword(user);
		
	}
	public void update(User user) {
		userDao.update(user);
	}
	
	public void updateNoPassEncode(User user) {
		userDao.updateNoPassEncode(user);
	}
	
	public int getNumberofUsers() {
		return userDao.getNumberofUsers();
	}
	
	public int getNumberOfUsersBySearchTerm(String searchTerm) {
		return userDao.getNumberOfUsersBySearchTerm(searchTerm);
	}
	
	public List<User> getPaginatedUsersByTerm(int pageLength,
			int displayStart, String searchCriteria) {
		
		return userDao.getPaginatedUsersByTerm(pageLength, displayStart, searchCriteria);
		
	}
	
	public List<User> getPaginatedUsers(int pageLength, int displayStart) {
		
		return userDao.getPaginatedUsers(pageLength, displayStart);

	}

	public boolean existsById(User user) {
		return userDao.existsById(user);
	}
}
