package com.maxic.towers.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
				Tower tower = new Tower(rs.getInt("towerId"), rs
						.getString("doveId"), rs.getInt("towerbaseId"), rs
						.getString("placeName"), rs.getString("placeName2"), rs
						.getString("placeNameCL"), rs.getBoolean("ringable"),
						rs.getString("gridReference"), rs.getFloat("latitude"),
						rs.getFloat("longitude"), rs.getString("postCode"), rs
								.getFloat("satNavLatitude"), rs
								.getFloat("satNavLongitude"), rs
								.getInt("countryId"), rs.getInt("countyId"), rs
								.getInt("guildId"), rs.getString("dedication"),
						rs.getString("listedGrade"), rs
								.getBoolean("groundFloorRing"), rs
								.getBoolean("simulator"), rs
								.getBoolean("toilet"), rs
								.getString("extraInfo"), rs
								.getInt("buildingId"), rs
								.getString("affiliation"), rs
								.getString("accessDetails"), rs
								.getString("towerCaptain"));
				return tower;
			}

		});
	}

	public List<TowerShort> getTowersShort() {

		return jdbc.query("SELECT * FROM towers", new RowMapper<TowerShort>() {

			public TowerShort mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				TowerShort towerShort = new TowerShort(rs.getInt("towerId"), rs
						.getFloat("latitude"), rs.getFloat("longitude"));
				return towerShort;
			}

		});
	}

	public Tower getTower(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("SELECT * FROM towers where towerId = :id",
				params, new RowMapper<Tower>() {

					public Tower mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Tower tower = new Tower(rs.getInt("towerId"), rs
								.getString("doveId"), rs.getInt("towerbaseId"),
								rs.getString("placeName"), rs
										.getString("placeName2"), rs
										.getString("placeNameCL"), rs
										.getBoolean("ringable"), rs
										.getString("gridReference"), rs
										.getFloat("latitude"), rs
										.getFloat("longitude"), rs
										.getString("postCode"), rs
										.getFloat("satNavLatitude"), rs
										.getFloat("satNavLongitude"), rs
										.getInt("countryId"), rs
										.getInt("countyId"), rs
										.getInt("guildId"), rs
										.getString("dedication"), rs
										.getString("listedGrade"), rs
										.getBoolean("groundFloorRing"), rs
										.getBoolean("simulator"), rs
										.getBoolean("toilet"), rs
										.getString("extraInfo"), rs
										.getInt("buildingId"), rs
										.getString("affiliation"), rs
										.getString("accessDetails"), rs
										.getString("towerCaptain"));
						return tower;
					}

				});
	}

	public boolean addTower(Tower tower) {
		System.out.println("Adding tower " + tower.getDoveId());
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				tower);
		boolean test = jdbc
				.update("INSERT INTO towers (`doveId`, `towerbaseId`, `placeName`,"
						+ " `placeName2`, `placeNameCL`, `ringable`,"
						+ " `gridReference`, `latitude`, `longitude`, `postCode`,"
						+ " `satNavLatitude`, `satNavLongitude`, `countryId`,"
						+ " `countyId`, `guildId`, `dedication`, `listedGrade`,"
						+ " `groundFloorRing`, `simulator`, `toilet`, `extraInfo`,"
						+ " `buildingId`, `affiliation`, `accessDetails`, `towerCaptain`)"
						+ " VALUES (:doveId, :towerbaseId, :placeName,"
						+ " :placeName2, :placeNameCL, :ringable,"
						+ " :gridReference, :latitude, :longitude, :postCode,"
						+ " :satNavLatitude, :satNavLongitude, :countryId,"
						+ " :countyId, :guildId, :dedication, :listedGrade,"
						+ " :groundFloorRing, :simulator, :toilet, :extraInfo,"
						+ " :buildingId, :affiliation, :accessDetails, :towerCaptain)",
						params) == 1;
		return test;

	}

	public boolean deleteTower(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.update("DELETE FROM towers WHERE towerId = :id", params) == 1;
	}

	public boolean editTower(Tower tower) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				tower);
		boolean test = jdbc.update("UPDATE towers SET doveId = :doveId, "
				+ "towerbaseId = :towerbaseId, " + "placeName = :placeName, "
				+ "placeName2 = :placeName2, " + "placeNameCL = :placeNameCL, "
				+ "ringable = :ringable, " + "gridReference = :gridReference, "
				+ "postCode = :postCode, " + "latitude = :latitude, "
				+ "longitude = :longitude, "
				+ "satNavLatitude = :satNavLatitude, "
				+ "satNavLongitude = :satNavLongitude, "
				+ "countryId = :countryId, " + "countyId = :countyId, "
				+ "guildId = :guildId, " + "dedication = :dedication, "
				+ "listedGrade = :listedGrade, "
				+ "groundFloorRing = :groundFloorRing, "
				+ "simulator = :simulator, " + "toilet = :toilet, "
				+ "extraInfo = :extraInfo, " + "buildingId = :buildingId, "
				+ "affiliation = :affiliation, "
				+ "accessDetails = :accessDetails, "
				+ "towerCaptain = :towerCaptain " + "WHERE towerId = :towerId",
				params) == 1;
		return test;
	}

	@Transactional
	public boolean addTowers(ArrayList<Tower> towerList) {
		for (Tower tower : towerList) {
			this.addTower(tower);
		}
		return false;
	}

}
