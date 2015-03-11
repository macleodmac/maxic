package com.maxic.towers.web.dao;

import java.io.Serializable;

public class PracticePK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2674906734495675317L;
	protected int towerId;
	protected int practiceId;
	
	public PracticePK() {
	
	}

	public PracticePK(int towerId, int practiceId) {
		this.towerId = towerId;
		this.practiceId = practiceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + practiceId;
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
		PracticePK other = (PracticePK) obj;
		if (practiceId != other.practiceId)
			return false;
		if (towerId != other.towerId)
			return false;
		return true;
	}
	
	
}
