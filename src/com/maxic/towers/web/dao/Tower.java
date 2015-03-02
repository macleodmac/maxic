package com.maxic.towers.web.dao;

public class Tower {
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
			String placeName2, String placeNameCL, boolean ringable,
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
		this.ringable = ringable;
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
				+ ", ringable=" + ringable + ", gridReference=" + gridReference
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", postCode=" + postCode + ", satNavLatitude="
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessDetails == null) ? 0 : accessDetails.hashCode());
		result = prime * result
				+ ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + buildingId;
		result = prime * result + countryId;
		result = prime * result + countyId;
		result = prime * result
				+ ((dedication == null) ? 0 : dedication.hashCode());
		result = prime * result + ((doveId == null) ? 0 : doveId.hashCode());
		result = prime * result
				+ ((extraInfo == null) ? 0 : extraInfo.hashCode());
		result = prime * result
				+ ((gridReference == null) ? 0 : gridReference.hashCode());
		result = prime * result + (groundFloorRing ? 1231 : 1237);
		result = prime * result + guildId;
		result = prime * result + Float.floatToIntBits(latitude);
		result = prime * result
				+ ((listedGrade == null) ? 0 : listedGrade.hashCode());
		result = prime * result + Float.floatToIntBits(longitude);
		result = prime * result
				+ ((placeName == null) ? 0 : placeName.hashCode());
		result = prime * result
				+ ((placeName2 == null) ? 0 : placeName2.hashCode());
		result = prime * result
				+ ((placeNameCL == null) ? 0 : placeNameCL.hashCode());
		result = prime * result
				+ ((postCode == null) ? 0 : postCode.hashCode());
		result = prime * result + (ringable ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(satNavLatitude);
		result = prime * result + Float.floatToIntBits(satNavLongitude);
		result = prime * result + (simulator ? 1231 : 1237);
		result = prime * result + (toilet ? 1231 : 1237);
		result = prime * result
				+ ((towerCaptain == null) ? 0 : towerCaptain.hashCode());
		result = prime * result + towerId;
		result = prime * result + towerbaseId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tower other = (Tower) obj;
		if (accessDetails == null) {
			if (other.accessDetails != null)
				return false;
		} else if (!accessDetails.equals(other.accessDetails))
			return false;
		if (affiliation == null) {
			if (other.affiliation != null)
				return false;
		} else if (!affiliation.equals(other.affiliation))
			return false;
		if (buildingId != other.buildingId)
			return false;
		if (countryId != other.countryId)
			return false;
		if (countyId != other.countyId)
			return false;
		if (dedication == null) {
			if (other.dedication != null)
				return false;
		} else if (!dedication.equals(other.dedication))
			return false;
		if (doveId == null) {
			if (other.doveId != null)
				return false;
		} else if (!doveId.equals(other.doveId))
			return false;
		if (extraInfo == null) {
			if (other.extraInfo != null)
				return false;
		} else if (!extraInfo.equals(other.extraInfo))
			return false;
		if (gridReference == null) {
			if (other.gridReference != null)
				return false;
		} else if (!gridReference.equals(other.gridReference))
			return false;
		if (groundFloorRing != other.groundFloorRing)
			return false;
		if (guildId != other.guildId)
			return false;
		if (Float.floatToIntBits(latitude) != Float
				.floatToIntBits(other.latitude))
			return false;
		if (listedGrade == null) {
			if (other.listedGrade != null)
				return false;
		} else if (!listedGrade.equals(other.listedGrade))
			return false;
		if (Float.floatToIntBits(longitude) != Float
				.floatToIntBits(other.longitude))
			return false;
		if (placeName == null) {
			if (other.placeName != null)
				return false;
		} else if (!placeName.equals(other.placeName))
			return false;
		if (placeName2 == null) {
			if (other.placeName2 != null)
				return false;
		} else if (!placeName2.equals(other.placeName2))
			return false;
		if (placeNameCL == null) {
			if (other.placeNameCL != null)
				return false;
		} else if (!placeNameCL.equals(other.placeNameCL))
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		if (ringable != other.ringable)
			return false;
		if (Float.floatToIntBits(satNavLatitude) != Float
				.floatToIntBits(other.satNavLatitude))
			return false;
		if (Float.floatToIntBits(satNavLongitude) != Float
				.floatToIntBits(other.satNavLongitude))
			return false;
		if (simulator != other.simulator)
			return false;
		if (toilet != other.toilet)
			return false;
		if (towerCaptain == null) {
			if (other.towerCaptain != null)
				return false;
		} else if (!towerCaptain.equals(other.towerCaptain))
			return false;
		if (towerId != other.towerId)
			return false;
		if (towerbaseId != other.towerbaseId)
			return false;
		return true;
	}

	

}
