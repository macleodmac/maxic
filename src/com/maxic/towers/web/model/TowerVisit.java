package com.maxic.towers.web.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "visits")
public class TowerVisit {
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "towerId", nullable = false)
	private TowerDescriptor tower;
	
	@Id
	@Column(name = "visitId", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int visitId;
	
	@Column(name = "userId", unique = false, nullable = false)
	private int userId;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "rung")
	private boolean rung;
	
	@Column(name = "pealRung")
	private boolean pealRung;
	
	@Column(name = "quarterPealRung")
	private boolean quarterPealRung;
	
	@Column(name = "notes")
	private String notes;
	
	public TowerVisit() {
		
	}

	public TowerVisit(TowerDescriptor tower, int visitId, int userId, Date date,
			boolean rung, boolean pealRung, boolean quarterPealRung,
			String notes) {
		this.tower = tower;
		this.visitId = visitId;
		this.userId = userId;
		this.date = date;
		this.rung = rung;
		this.pealRung = pealRung;
		this.quarterPealRung = quarterPealRung;
		this.notes = notes;
	}

	

	public TowerDescriptor getTower() {
		return tower;
	}

	public void setTower(TowerDescriptor tower) {
		this.tower = tower;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getRung() {
		return rung;
	}

	public void setRung(boolean rung) {
		this.rung = rung;
	}

	public boolean getPealRung() {
		return pealRung;
	}

	public void setPealRung(boolean pealRung) {
		this.pealRung = pealRung;
	}

	public boolean getQuarterPealRung() {
		return quarterPealRung;
	}

	public void setQuarterPealRung(boolean quarterPealRung) {
		this.quarterPealRung = quarterPealRung;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "TowerVisit [tower=" + tower + ", visitId=" + visitId
				+ ", userId=" + userId + ", date=" + date + ", rung=" + rung
				+ ", pealRung=" + pealRung + ", quarterPealRung="
				+ quarterPealRung + ", notes=" + notes + "]";
	}

	


}
