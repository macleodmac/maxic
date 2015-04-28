package com.maxic.towers.web.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="peals")
public class Peal {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "towerId", nullable = false)
	private TowerDescriptor tower;
	@Id
	@GeneratedValue
	@Column(name="pealId")
	private int pealId;
	private String dedication;
	private Date dateRung;
	//private String dateRung;    //date
	private String time;		//time
	private String tenor;
	@NotBlank(message="Method cannot be blank.")
	private String method;
	private String methodDetails;
	private int changes;
	private String leader;
	private String composer;
	private String footnotes;
	private String composition;
	@NotBlank(message="At least one ringer must be entered.")
	private String ringer1;
	private String ringer2;
	private String ringer3;
	private String ringer4;
	private String ringer5;
	private String ringer6;
	private String ringer7;
	private String ringer8;
	private String ringer9;
	private String ringer10;
	private String ringer11;
	private String ringer12;
	private String ringer13;
	private String ringer14;
	private String ringer15;
	private String ringer16;
	
	public Peal(){
		
	}

	public Peal(TowerDescriptor tower, int pealId, String dedication, Date dateRung,
			String time, String tenor, String method, String methodDetails,
			int changes, String leader, String composer, String footnotes,
			String composition, String ringer1, String ringer2, String ringer3,
			String ringer4, String ringer5, String ringer6, String ringer7,
			String ringer8, String ringer9, String ringer10, String ringer11,
			String ringer12, String ringer13, String ringer14, String ringer15,
			String ringer16) {
		this.tower = tower;
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
		this.ringer1 = ringer1;
		this.ringer2 = ringer2;
		this.ringer3 = ringer3;
		this.ringer4 = ringer4;
		this.ringer5 = ringer5;
		this.ringer6 = ringer6;
		this.ringer7 = ringer7;
		this.ringer8 = ringer8;
		this.ringer9 = ringer9;
		this.ringer10 = ringer10;
		this.ringer11 = ringer11;
		this.ringer12 = ringer12;
		this.ringer13 = ringer13;
		this.ringer14 = ringer14;
		this.ringer15 = ringer15;
		this.ringer16 = ringer16;
	}

	public TowerDescriptor getTower() {
		return tower;
	}

	public void setTower(TowerDescriptor tower) {
		this.tower = tower;
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

	public Date getDateRung() {
		return dateRung;
	}

	public void setDateRung(Date dateRung) {
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

	public String getRinger1() {
		return ringer1;
	}

	public void setRinger1(String ringer1) {
		this.ringer1 = ringer1;
	}

	public String getRinger2() {
		return ringer2;
	}

	public void setRinger2(String ringer2) {
		this.ringer2 = ringer2;
	}

	public String getRinger3() {
		return ringer3;
	}

	public void setRinger3(String ringer3) {
		this.ringer3 = ringer3;
	}

	public String getRinger4() {
		return ringer4;
	}

	public void setRinger4(String ringer4) {
		this.ringer4 = ringer4;
	}

	public String getRinger5() {
		return ringer5;
	}

	public void setRinger5(String ringer5) {
		this.ringer5 = ringer5;
	}

	public String getRinger6() {
		return ringer6;
	}

	public void setRinger6(String ringer6) {
		this.ringer6 = ringer6;
	}

	public String getRinger7() {
		return ringer7;
	}

	public void setRinger7(String ringer7) {
		this.ringer7 = ringer7;
	}

	public String getRinger8() {
		return ringer8;
	}

	public void setRinger8(String ringer8) {
		this.ringer8 = ringer8;
	}

	public String getRinger9() {
		return ringer9;
	}

	public void setRinger9(String ringer9) {
		this.ringer9 = ringer9;
	}

	public String getRinger10() {
		return ringer10;
	}

	public void setRinger10(String ringer10) {
		this.ringer10 = ringer10;
	}

	public String getRinger11() {
		return ringer11;
	}

	public void setRinger11(String ringer11) {
		this.ringer11 = ringer11;
	}

	public String getRinger12() {
		return ringer12;
	}

	public void setRinger12(String ringer12) {
		this.ringer12 = ringer12;
	}

	public String getRinger13() {
		return ringer13;
	}

	public void setRinger13(String ringer13) {
		this.ringer13 = ringer13;
	}

	public String getRinger14() {
		return ringer14;
	}

	public void setRinger14(String ringer14) {
		this.ringer14 = ringer14;
	}

	public String getRinger15() {
		return ringer15;
	}

	public void setRinger15(String ringer15) {
		this.ringer15 = ringer15;
	}

	public String getRinger16() {
		return ringer16;
	}

	public void setRinger16(String ringer16) {
		this.ringer16 = ringer16;
	}

	@Override
	public String toString() {
		return "Peal [tower=" + tower + ", pealId=" + pealId + ", dedication="
				+ dedication + ", dateRung=" + dateRung + ", time=" + time
				+ ", tenor=" + tenor + ", method=" + method
				+ ", methodDetails=" + methodDetails + ", changes=" + changes
				+ ", leader=" + leader + ", composer=" + composer
				+ ", footnotes=" + footnotes + ", composition=" + composition
				+ ", ringer1=" + ringer1 + ", ringer2=" + ringer2
				+ ", ringer3=" + ringer3 + ", ringer4=" + ringer4
				+ ", ringer5=" + ringer5 + ", ringer6=" + ringer6
				+ ", ringer7=" + ringer7 + ", ringer8=" + ringer8
				+ ", ringer9=" + ringer9 + ", ringer10=" + ringer10
				+ ", ringer11=" + ringer11 + ", ringer12=" + ringer12
				+ ", ringer13=" + ringer13 + ", ringer14=" + ringer14
				+ ", ringer15=" + ringer15 + ", ringer16=" + ringer16 + "]";
	}



	
	
}
