package com.maxic.towers.web.dao;

import java.io.Serializable;

public class ContactPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2674906734495675317L;
	protected int towerId;
	protected int contactId;
	
	public ContactPK() {
	
	}

	public ContactPK(int towerId, int contactId) {
		this.towerId = towerId;
		this.contactId = contactId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contactId;
		result = prime * result + towerId;
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
		ContactPK other = (ContactPK) obj;
		if (contactId != other.contactId)
			return false;
		if (towerId != other.towerId)
			return false;
		return true;
	}
	
	
}
