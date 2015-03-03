package com.maxic.towers.web.dao;

public class TowerShort {
	private int t;
	private float la;
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
