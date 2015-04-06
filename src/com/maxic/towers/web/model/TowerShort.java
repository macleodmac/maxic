package com.maxic.towers.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="towers")
public class TowerShort {
	@Id
	@GeneratedValue
	@Column(name="towerId")
	private int t;
	@Column(name="latitude")
	private float la;
	@Column(name="longitude")
	private float lo;

	public TowerShort() {

	}

	public TowerShort(int t, float la, float lo) {
		this.t = t;
		this.la = la;
		this.lo = lo;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public float getLa() {
		return la;
	}

	public void setLa(float la) {
		this.la = la;
	}

	public float getLo() {
		return lo;
	}

	public void setLo(float lo) {
		this.lo = lo;
	}

	@Override
	public String toString() {
		return t + "," + la + "," + lo;
	}
	
	

}
