package com.maxic.towers.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.maxic.towers.web.model.Role;

@Component("roleDao")
@Transactional
public class RoleDao {


	@Autowired
	private SessionFactory sessionFactory;

	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		return session().createQuery("from Role").list();
	}
	@Transactional
	public void addRole(Role role) {
		session().save(role);
	}
}
