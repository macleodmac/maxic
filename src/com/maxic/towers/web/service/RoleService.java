package com.maxic.towers.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.RoleDao;
import com.maxic.towers.web.model.Role;

@Service("roleService")
public class RoleService {

	private RoleDao roleDao;

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	public void addRole(Role role) {
		roleDao.addRole(role);
	}
}
