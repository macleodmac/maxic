package com.maxic.towers.web.dao;

public class Ringer {
	private int pealId;
	private int bellnumber;
	private String name;
	
	public Ringer(){
		
	}
	public Ringer(int pealId, int bellnumber, String name){
		this.pealId=pealId;
		this.bellnumber=bellnumber;
		this.name=name;	
	}
	public int getpealId(){
		return pealId;
	}
	public void setpealId(int pealId){
		this.pealId=pealId;
	}
	public int getbellnumber(){
		return bellnumber;
	}
	public void setbellnumber(int bellnumber){
		this.bellnumber=bellnumber;
	}
	public String getname(){
		return name;
	}
	public void setname(String name){
		this.name=name;
	}

	
	public String toString(){
		return "Ringer[pealId=" + pealId + ",bellnumber=" + bellnumber + ",name=" + name + "]";
	}
}
