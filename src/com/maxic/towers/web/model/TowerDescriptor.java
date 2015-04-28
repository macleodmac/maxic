package com.maxic.towers.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "towers")
public class TowerDescriptor {
	
	@Id
	@Column(name = "towerId", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "placeName")
	private String de;

	public TowerDescriptor() {

	}
	
	public TowerDescriptor(int id, String de) {
		this.id = id;
		this.de = de;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	@Override
	public String toString() {
		return "TowerDescriptor [id=" + id + ", de=" + de + "]";
	}

	
	
}
