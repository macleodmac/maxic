package com.maxic.towers.web.dao;

public class Country {
	private int countryId;
	private String name;
	private String ISOcode;

	public Country(){
		
	}
	public Country(int countryId, String name, String ISOcode){
		this.countryId=countryId;
		this.name=name;
		this.ISOcode=ISOcode;	
	}
	public int getcountryId(){
		return countryId;
	}
	public void setcountryId(int countryId){
		this.countryId=countryId;
	}
	public String getname(){
		return name;
	}
	public void setname(String name){
		this.name=name;
	}
	public String getISOcode(){
		return ISOcode;
	}
	public void setISOcode(String ISOcode){
		this.ISOcode=ISOcode;
	}
	
	public String toString(){
		return "Country[countryId=" + countryId + ",name=" + name + ",ISOcode=" + ISOcode + "]";
	}
}
