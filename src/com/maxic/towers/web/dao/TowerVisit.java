package com.maxic.towers.web.dao;
public class TowerVisit {
	private int towerId;
	private int visitId;
	private String userName;
	private String date;
	private String time;
	private boolean rung;
	private boolean pealRung;
	private boolean quarterPealRung;
	private String notes;
	
	public TowerVisit() {
		
	}


	public TowerVisit(int towerId, int visitId, String userName, String date, String time, boolean rung, boolean pealRung, boolean quarterPealRung, String notes) {
		this.towerId = towerId;
		this.visitId = visitId;
		this.userName = userName;
		this.date = date;
		this.time = time;
		this.rung = rung;
		this.pealRung = pealRung;
		this.quarterPealRung = quarterPealRung;
		this.notes = notes;
		
	}


	

	public int getTowerId() {
		return towerId;
	}


	public void setTowerId(int towerId) {
		this.towerId = towerId;
	}


	public int getVisitId() {
		return visitId;
	}


	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public boolean isRung() {
		return rung;
	}


	public void setRung(boolean rung) {
		this.rung = rung;
	}


	public boolean isPealRung() {
		return pealRung;
	}


	public void setPealRung(boolean pealRung) {
		this.pealRung = pealRung;
	}


	public boolean isQuarterPealRung() {
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
		return "Tower [towerId=" + towerId + ", visitId=" + visitId
				+ ", userName=" + userName + ", date=" + date
				+ ", time=" + time + ", rung=" + rung
				+ ", pealRung=" + pealRung + ", quarterPealRung="
				+ quarterPealRung + ", notes=" + notes + "]";
	}
	


}
