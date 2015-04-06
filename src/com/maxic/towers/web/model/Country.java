package com.maxic.towers.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="countries")
public class Country {
	@Id
	@Column(name="isoCode")
	private String isoCode;
	private String name;

	public Country(){
		
	}

	public Country(String isoCode, String name) {
		this.isoCode = isoCode;
		this.name = name;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [isoCode=" + isoCode + ", name=" + name + "]";
	}
	
	
}
