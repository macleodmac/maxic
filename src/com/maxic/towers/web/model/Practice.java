package com.maxic.towers.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "practice")
public class Practice {

	private int towerId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int practiceId;
	
	private String practiceName;
	
	private String day;
	
	private String time;
	private String regularity;
	private boolean visitorsWelcome;

	public Practice() {

	}

	public Practice(int towerId, int practiceId, String practiceName,
			String day, String time, String regularity, boolean visitorsWelcome) {
		this.towerId = towerId;
		this.practiceId = practiceId;
		this.practiceName = practiceName;
		this.day = day;
		this.time = time;
		this.regularity = regularity;
		this.visitorsWelcome = visitorsWelcome;
	}

	public int getTowerId() {
		return towerId;
	}

	public void setTowerId(int towerId) {
		this.towerId = towerId;
	}

	public int getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(int practiceId) {
		this.practiceId = practiceId;
	}

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRegularity() {
		return regularity;
	}

	public void setRegularity(String regularity) {
		this.regularity = regularity;
	}

	public boolean isVisitorsWelcome() {
		return visitorsWelcome;
	}

	public void setVisitorsWelcome(boolean visitorsWelcome) {
		this.visitorsWelcome = visitorsWelcome;
	}

	@Override
	public String toString() {
		return "Practice [towerId=" + towerId + ", practiceId=" + practiceId
				+ ", practiceName=" + practiceName + ", day=" + day + ", time="
				+ time + ", regularity=" + regularity + ", visitorsWelcome="
				+ visitorsWelcome + "]";
	}

}
