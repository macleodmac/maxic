package com.maxic.towers.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="peals")
public class Peal {
	private int towerId;
	@Id
	@GeneratedValue
	@Column(name="pealId")
	private int pealId;
	private String dedication;
	private String dateRung;    //date
	private String time;		//time
	private String tenor;
	private String method;
	private String methodDetails;
	private int changes;
	private String leader;
	private String composer;
	private String footnotes;
	private String composition;
	
	public Peal(){
		
	}

	public Peal(int towerId,int pealId,String dedication,String dateRung,
			String time,String tenor,String method,String methodDetails,int changes,
			String leader,String composer,String footnotes,String composition){
		this.towerId = towerId;
		this.pealId = pealId;
		this.dedication = dedication;
		this.dateRung = dateRung;
		this.time = time;
		this.tenor = tenor;
		this.method = method;
		this.methodDetails = methodDetails;
		this.changes = changes;
		this.leader = leader;
		this.composer = composer;
		this.footnotes = footnotes;
		this.composition = composition;
	}
	
	public int getTowerId() {
		return towerId;
	}
	public void setTowerId(int towerId) {
		this.towerId = towerId;
	}
	public int getPealId() {
		return pealId;
	}
	public void setPealId(int pealId) {
		this.pealId = pealId;
	}
	public String getDedication() {
		return dedication;
	}
	public void setDedication(String dedication) {
		this.dedication = dedication;
	}
	public String getDateRung() {
		return dateRung;
	}
	public void setDateRung(String dateRung) {
		this.dateRung = dateRung;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTenor() {
		return tenor;
	}
	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMethodDetails() {
		return methodDetails;
	}
	public void setMethodDetails(String methodDetails) {
		this.methodDetails = methodDetails;
	}
	public int getChanges() {
		return changes;
	}
	public void setChanges(int changes) {
		this.changes = changes;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public String getFootnotes() {
		return footnotes;
	}
	public void setFootnotes(String footnotes) {
		this.footnotes = footnotes;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String toString() {
		return "Peal [towerId ="+ towerId+", pealId ="+ pealId+
				", dedication ="+ dedication+", dateRung ="+dateRung+
				", time ="+ time+", tenor ="+ tenor+", method ="+ method+
				", methodDetails ="+ methodDetails+", changes ="+ changes+
		        ", leader ="+ leader+", composer ="+ composer+", footnotes= "
		        + footnotes+", composition ="+ composition+" ]";
	}
}
