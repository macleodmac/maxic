package com.maxic.towers.web.model;


public class TowerDescriptor {
	private int id;
	private String de;

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
