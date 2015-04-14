package com.maxic.towers.web.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EditUser implements Serializable {

	private int id;

	private User user;

	private String newName;

	private String newEmail;

	private String newPassword;

	public EditUser() {

	}

	public EditUser(int id, User user, String newName, String newEmail,
			String newPassword) {
		this.id = id;
		this.user = user;
		this.newName = newName;
		this.newEmail = newEmail;
		this.newPassword = newPassword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((newEmail == null) ? 0 : newEmail.hashCode());
		result = prime * result + ((newName == null) ? 0 : newName.hashCode());
		result = prime * result
				+ ((newPassword == null) ? 0 : newPassword.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		EditUser other = (EditUser) obj;
		if (id != other.id)
			return false;
		if (newEmail == null) {
			if (other.newEmail != null)
				return false;
		} else if (!newEmail.equals(other.newEmail))
			return false;
		if (newName == null) {
			if (other.newName != null)
				return false;
		} else if (!newName.equals(other.newName))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EditUser [id=" + id + ", user=" + user + ", newName=" + newName
				+ ", newEmail=" + newEmail + ", newPassword=" + newPassword
				+ "]";
	}

}
