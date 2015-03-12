package com.maxic.towers.web.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "towers")
public class Tower implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "towerId", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int towerId;
	private String doveId;
	private int towerbaseId;
	private String placeName;
	private String placeName2;
	private String placeNameCL;
	private boolean ringable;
	private String gridReference;
	private float latitude;
	private float longitude;
	private String postCode;
	private float satNavLatitude;
	private float satNavLongitude;

	@ManyToOne
	@JoinColumn(name = "isoCode")
	private Country country;

	@ManyToOne
	@JoinColumn(name = "dioceseId")
	private Diocese diocese;

	private int guildId;
	private String dedication;
	private String listedGrade;
	private boolean groundFloorRing;
	private boolean simulator;
	private boolean toilet;
	private String extraInfo;
	private int buildingId;
	private String affiliation;
	private String accessDetails;
	private String towerCaptain;

	@OneToMany
	@JoinColumn(name="towerId")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Practice> practices = new ArrayList<Practice>();

	// @OneToMany
	// @LazyCollection(LazyCollectionOption.FALSE)
	// @JoinColumn(name="towerId")
	// private List<ContactDetails> contactDetails;

	public Tower() {

	}

	public Tower(int towerId, String doveId, int towerbaseId, String placeName,
			String placeName2, String placeNameCL, boolean ringable,
			String gridReference, float latitude, float longitude,
			String postCode, float satNavLatitude, float satNavLongitude,
			Country country, Diocese diocese, int guildId, String dedication,
			String listedGrade, boolean groundFloorRing, boolean simulator,
			boolean toilet, String extraInfo, int buildingId,
			String affiliation, String accessDetails, String towerCaptain,
			List<Practice> practices) {
		this.towerId = towerId;
		this.doveId = doveId;
		this.towerbaseId = towerbaseId;
		this.placeName = placeName;
		this.placeName2 = placeName2;
		this.placeNameCL = placeNameCL;
		this.ringable = ringable;
		this.gridReference = gridReference;
		this.latitude = latitude;
		this.longitude = longitude;
		this.postCode = postCode;
		this.satNavLatitude = satNavLatitude;
		this.satNavLongitude = satNavLongitude;
		this.country = country;
		this.diocese = diocese;
		this.guildId = guildId;
		this.dedication = dedication;
		this.listedGrade = listedGrade;
		this.groundFloorRing = groundFloorRing;
		this.simulator = simulator;
		this.toilet = toilet;
		this.extraInfo = extraInfo;
		this.buildingId = buildingId;
		this.affiliation = affiliation;
		this.accessDetails = accessDetails;
		this.towerCaptain = towerCaptain;
		this.practices = practices;
	}

	public int getTowerId() {
		return towerId;
	}

	public void setTowerId(int towerId) {
		this.towerId = towerId;
	}

	public String getDoveId() {
		return doveId;
	}

	public void setDoveId(String doveId) {
		this.doveId = doveId;
	}

	public int getTowerbaseId() {
		return towerbaseId;
	}

	public void setTowerbaseId(int towerbaseId) {
		this.towerbaseId = towerbaseId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceName2() {
		return placeName2;
	}

	public void setPlaceName2(String placeName2) {
		this.placeName2 = placeName2;
	}

	public String getPlaceNameCL() {
		return placeNameCL;
	}

	public void setPlaceNameCL(String placeNameCL) {
		this.placeNameCL = placeNameCL;
	}

	public boolean isRingable() {
		return ringable;
	}

	public void setRingable(boolean ringable) {
		this.ringable = ringable;
	}

	public String getGridReference() {
		return gridReference;
	}

	public void setGridReference(String gridReference) {
		this.gridReference = gridReference;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public float getSatNavLatitude() {
		return satNavLatitude;
	}

	public void setSatNavLatitude(float satNavLatitude) {
		this.satNavLatitude = satNavLatitude;
	}

	public float getSatNavLongitude() {
		return satNavLongitude;
	}

	public void setSatNavLongitude(float satNavLongitude) {
		this.satNavLongitude = satNavLongitude;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Diocese getDiocese() {
		return diocese;
	}

	public void setDiocese(Diocese diocese) {
		this.diocese = diocese;
	}

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}

	public String getDedication() {
		return dedication;
	}

	public void setDedication(String dedication) {
		this.dedication = dedication;
	}

	public String getListedGrade() {
		return listedGrade;
	}

	public void setListedGrade(String listedGrade) {
		this.listedGrade = listedGrade;
	}

	public boolean isGroundFloorRing() {
		return groundFloorRing;
	}

	public void setGroundFloorRing(boolean groundFloorRing) {
		this.groundFloorRing = groundFloorRing;
	}

	public boolean isSimulator() {
		return simulator;
	}

	public void setSimulator(boolean simulator) {
		this.simulator = simulator;
	}

	public boolean isToilet() {
		return toilet;
	}

	public void setToilet(boolean toilet) {
		this.toilet = toilet;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getAccessDetails() {
		return accessDetails;
	}

	public void setAccessDetails(String accessDetails) {
		this.accessDetails = accessDetails;
	}

	public String getTowerCaptain() {
		return towerCaptain;
	}

	public void setTowerCaptain(String towerCaptain) {
		this.towerCaptain = towerCaptain;
	}

	public List<Practice> getPractices() {
		return practices;
	}

	public void setPractices(List<Practice> practices) {
		this.practices = practices;
	}

	@Override
	public String toString() {
		return "Tower [towerId=" + towerId + ", doveId=" + doveId
				+ ", towerbaseId=" + towerbaseId + ", placeName=" + placeName
				+ ", placeName2=" + placeName2 + ", placeNameCL=" + placeNameCL
				+ ", ringable=" + ringable + ", gridReference=" + gridReference
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", postCode=" + postCode + ", satNavLatitude="
				+ satNavLatitude + ", satNavLongitude=" + satNavLongitude
				+ ", country=" + country + ", diocese=" + diocese
				+ ", guildId=" + guildId + ", dedication=" + dedication
				+ ", listedGrade=" + listedGrade + ", groundFloorRing="
				+ groundFloorRing + ", simulator=" + simulator + ", toilet="
				+ toilet + ", extraInfo=" + extraInfo + ", buildingId="
				+ buildingId + ", affiliation=" + affiliation
				+ ", accessDetails=" + accessDetails + ", towerCaptain="
				+ towerCaptain + ", practices=" + practices + "]";
	}

}
