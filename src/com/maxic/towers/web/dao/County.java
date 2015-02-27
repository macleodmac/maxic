package com.maxic.towers.web.dao;

public class County {
	private String countyId;
	private String name;
	
	public County(){
		
	}
	public County(String countyId, String name){
		this.countyId=countyId;
		this.name=name;
	}
	public String getcountyId(){
		return countyId;
	}
	public void setcountyId(String countyId){
		this.countyId=countyId;
	}
	public String getname(){
		return name;
	}
	public void setname(String name){
		this.name=name;
	}

	public String toString(){
		return "County[countyId=" + countyId + ",name=" + name + "]";
	}

}
