package com.maxic.towers.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;

	@Column(name = "email")
	@NotBlank(message = "Email address cannot be blank.")
	@Size(min = 8, message = "Email address must be at least 8 characters long.")
	@Email(message = "Email address cannot be validated.")
	private String email;

	// @Column(name = "password")
	// @NotBlank(message = "Password cannot be blank.")
	// @Size(min = 6, max = 20, message =
	// "Password must be between 8 and 20 characters long.")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "role")
	private String role;

	@Column(name = "name")
	private String name;

	@Column(name = "towerCaptain")
	private int towerCaptain;

	public User() {

	}

	public User(Integer id, String email, String password, boolean enabled,
			String role, String name, int towerCaptain) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.name = name;
		this.towerCaptain = towerCaptain;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTowerCaptain() {
		return towerCaptain;
	}

	public void setTowerCaptain(int towerCaptain) {
		this.towerCaptain = towerCaptain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + towerCaptain;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (towerCaptain != other.towerCaptain)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password
				+ ", enabled=" + enabled + ", role=" + role + ", name=" + name
				+ ", towerCaptain=" + towerCaptain + "]";
	}

}
