package com.maxic.towers.web.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class EditUser implements Serializable {

	@NotNull
	@NotBlank(message = "Email address cannot be blank.")
	@Size(min = 8, message = "Email address must be at least 8 characters long.")
	@Email(message = "Email address cannot be validated.")
	private String oldEmail;
	@NotNull
	@NotBlank(message = "Email address cannot be blank.")
	@Size(min = 8, message = "Email address must be at least 8 characters long.")
	@Email(message = "Email address cannot be validated.")
	private String newEmail;
	@NotNull
	@NotBlank
	private String currentPassword;
	private String newPassword;
	private boolean enabled;

	private String role;

	public EditUser() {

	}

	public EditUser(String oldEmail, String newEmail, String currentPassword,
			String newPassword, boolean enabled, String role) {
		this.oldEmail = oldEmail;
		this.newEmail = newEmail;
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.enabled = enabled;
		this.role = role;
	}

	public String getOldEmail() {
		return oldEmail;
	}

	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currentPassword == null) ? 0 : currentPassword.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((newEmail == null) ? 0 : newEmail.hashCode());
		result = prime * result
				+ ((newPassword == null) ? 0 : newPassword.hashCode());
		result = prime * result
				+ ((oldEmail == null) ? 0 : oldEmail.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		if (currentPassword == null) {
			if (other.currentPassword != null)
				return false;
		} else if (!currentPassword.equals(other.currentPassword))
			return false;
		if (enabled != other.enabled)
			return false;
		if (newEmail == null) {
			if (other.newEmail != null)
				return false;
		} else if (!newEmail.equals(other.newEmail))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		if (oldEmail == null) {
			if (other.oldEmail != null)
				return false;
		} else if (!oldEmail.equals(other.oldEmail))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EditUser [oldEmail=" + oldEmail + ", newEmail=" + newEmail
				+ ", currentPassword=" + currentPassword + ", newPassword="
				+ newPassword + ", enabled=" + enabled + ", role=" + role + "]";
	}

}
