package com.maxic.towers.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dioceses")
public class Diocese {
	@Id
	@Column(name="dioceseId")
	private String dioceseId;
	private String name;
	
	public Diocese(){
		
	}

	public Diocese(String dioceseId, String name) {
		this.dioceseId = dioceseId;
		this.name = name;
	}

	public String getDioceseId() {
		return dioceseId;
	}

	public void setDioceseId(String dioceseId) {
		this.dioceseId = dioceseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
