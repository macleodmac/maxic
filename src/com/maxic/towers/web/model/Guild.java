package com.maxic.towers.web.model;

public class Guild {
	private String guildId;
	private String name;
	private String website;
	
	public Guild(){
		
	}
	public Guild(String guildId, String name, String website){
		this.guildId=guildId;
		this.name=name;
		this.website=website;	
	}
	public String getguildId(){
		return guildId;
	}
	public void setguildId(String guildId){
		this.guildId=guildId;
	}
	public String getname(){
		return name;
	}
	public void setname(String name){
		this.name=name;
	}
	public String getwebsite(){
		return website;
	}
	public void setwebsite(String website){
		this.website=website;
	}
	
	public String toString(){
		return "Guild[guildId=" + guildId + ",name=" + name + ",website=" + website + "]";
	}
}


