package com.maxic.towers.web.dao;
public class Tower {
	private int towerId;
	private String doveId;
	private int towerbaseId;
	private String placeName;
	private String placeName2;
	private String placeNameCL;
	private String associatedChurch;
	private String gridReference;
	private float latitude;
	private float longitude;
	private String postCode;
	private float satNavLatitude;
	private float satNavLongitude;
	private int countryId;
	private int countyId;
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

	
	public Tower() {
		
	}


	public Tower(int towerId, String doveId, int towerbaseId, String placeName,
			String placeName2, String placeNameCL, String associatedChurch,
			String gridReference, float latitude, float longitude,
			String postCode, float satNavLatitude, float satNavLongitude,
			int countryId, int countyId, int guildId, String dedication,
			String listedGrade, boolean groundFloorRing, boolean simulator,
			boolean toilet, String extraInfo, int buildingId,
			String affiliation, String accessDetails, String towerCaptain) {
		this.towerId = towerId;
		this.doveId = doveId;
		this.towerbaseId = towerbaseId;
		this.placeName = placeName;
		this.placeName2 = placeName2;
		this.placeNameCL = placeNameCL;
		this.associatedChurch = associatedChurch;
		this.gridReference = gridReference;
		this.latitude = latitude;
		this.longitude = longitude;
		this.postCode = postCode;
		this.satNavLatitude = satNavLatitude;
		this.satNavLongitude = satNavLongitude;
		this.countryId = countryId;
		this.countyId = countyId;
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


	public String getAssociatedChurch() {
		return associatedChurch;
	}


	public void setAssociatedChurch(String associatedChurch) {
		this.associatedChurch = associatedChurch;
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


	public int getCountryId() {
		return countryId;
	}


	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}


	public int getCountyId() {
		return countyId;
	}


	public void setCountyId(int countyId) {
		this.countyId = countyId;
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


	@Override
	public String toString() {
		return "Tower [towerId=" + towerId + ", doveId=" + doveId
				+ ", towerbaseId=" + towerbaseId + ", placeName=" + placeName
				+ ", placeName2=" + placeName2 + ", placeNameCL=" + placeNameCL
				+ ", associatedChurch=" + associatedChurch + ", gridReference="
				+ gridReference + ", latitude=" + latitude + ", longitude="
				+ longitude + ", postCode=" + postCode + ", satNavLatitude="
				+ satNavLatitude + ", satNavLongitude=" + satNavLongitude
				+ ", countryId=" + countryId + ", countyId=" + countyId
				+ ", guildId=" + guildId + ", dedication=" + dedication
				+ ", listedGrade=" + listedGrade + ", groundFloorRing="
				+ groundFloorRing + ", simulator=" + simulator + ", toilet="
				+ toilet + ", extraInfo=" + extraInfo + ", buildingId="
				+ buildingId + ", affiliation=" + affiliation
				+ ", accessDetails=" + accessDetails + ", towerCaptain="
				+ towerCaptain + "]";
	}
	


}
