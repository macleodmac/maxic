package com.maxic.towers.web.dao;

public class Practice {

	private int towerId;
	private String day;
	private String time;
	private String regularity;
	private String visitorsWelcome;
	
	public Practice() {
		
	}	
	public Practice(int towerId, String day,String time, String regularity, String visitorsWelcome) {
		this.towerId=towerId;
		this.day=day;
		this.time=time;
		this.regularity=regularity;
		this.visitorsWelcome=visitorsWelcome;
	}
	
	public int getTowerId() {
		return towerId;
	}
	public void setTowerId(int towerId) {
		this.towerId = towerId;
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
	public String getVisitorsWelcome() {
		return visitorsWelcome;
	}
	public void setVisitorsWelcome(String visitorsWelcome) {
		this.visitorsWelcome = visitorsWelcome;
	}
	public String toString(){
		return "Practice [towerId="+towerId+", day="+day+", time="+time+", regularity="+regularity+", visitorsWelcome="+visitorsWelcome+"]";
	}
	
}
