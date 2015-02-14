package com.maxic.towers.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("towerDao")
public class TowerDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Tower> getTowers() {

		return jdbc.query("SELECT * FROM towers", new RowMapper<Tower>() {

			public Tower mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tower tower = new Tower();
				tower.setTowerId(rs.getInt("towerId"));
				tower.setPlaceName((rs.getString("placeName")));
				tower.setLatitude((rs.getFloat("lat")));
				tower.setLongitude((rs.getFloat("long")));
				return tower;
			}

		});
	}

//	public boolean addTower(Tower tower) {
//
//		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
//				tower);
//		boolean test = jdbc
//				.update("INSERT INTO towers (`doveId`, `towerBaseId, `lat`, `long`, "
//						+ "`placeName`, `placeName2`, `placeNameCL`, `associatedChurch`, "
//						+ "`gridReference`, `postCode`, `satNavLatitude`, `satNavLongitude`, "
//						+ "`countryID`, `countyID`, `dedication`, `listedGrade`, "
//						+ "`groundFloorRing`, `simulator`, `toilet`, `extraInfo`, `buildingId`, "
//						+ "`affiliation`, `towerCaptain`) "
//						+ "VALUES (:doveId, :towerBaseId, :latitude, :longitude, :placeName, "
//						+ ":placeName2, :placeNameCL, :associatedChurch, :gridReference, :postCode, "
//						+ ":satNavLatitude, :satNavLongitude, :countryID, "
//						+ ":countyID, :dedication, :listedGrade, :groundFloorRing, :simulator, :toilet, "
//						+ ":extraInfo, :buildingId, :affiliation, :towerCaptain)",
//						params) == 1;
//		return test;
//
//	}

}
