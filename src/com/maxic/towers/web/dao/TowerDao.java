package com.maxic.towers.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
				Tower tower = new Tower(rs.getInt("towerId"), rs
						.getString("doveId"), rs.getInt("towerbaseId"), rs
						.getString("placeName"), rs.getString("placeName2"), rs
						.getString("placeNameCL"), rs
						.getString("associatedChurch"), rs
						.getString("gridReference"), rs.getFloat("latitude"),
						rs.getFloat("longitude"), rs.getString("postCode"), rs
								.getFloat("satNavLatitude"), rs
								.getFloat("satNavLongitude"), rs
								.getInt("countryId"), rs.getInt("countyId"), rs
								.getInt("guildId"), rs.getString("dedication"),
						rs.getString("listedGrade"), rs
								.getBoolean("groundFloorRing"), rs
								.getBoolean("simulator"), rs
								.getBoolean("toilet"), rs
								.getString("extraInfo"), rowNum, rs
								.getString("affiliation"), rs
								.getString("accessDetails"), rs
								.getString("towerCaptain"));
				return tower;
			}

		});
	}

	public Tower getTower(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("SELECT * FROM towers where towerId = :id",
				params, new RowMapper<Tower>() {

			public Tower mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tower tower = new Tower(rs.getInt("towerId"), rs
						.getString("doveId"), rs.getInt("towerbaseId"), rs
						.getString("placeName"), rs.getString("placeName2"), rs
						.getString("placeNameCL"), rs
						.getString("associatedChurch"), rs
						.getString("gridReference"), rs.getFloat("latitude"),
						rs.getFloat("longitude"), rs.getString("postCode"), rs
								.getFloat("satNavLatitude"), rs
								.getFloat("satNavLongitude"), rs
								.getInt("countryId"), rs.getInt("countyId"), rs
								.getInt("guildId"), rs.getString("dedication"),
						rs.getString("listedGrade"), rs
								.getBoolean("groundFloorRing"), rs
								.getBoolean("simulator"), rs
								.getBoolean("toilet"), rs
								.getString("extraInfo"), rowNum, rs
								.getString("affiliation"), rs
								.getString("accessDetails"), rs
								.getString("towerCaptain"));
				return tower;
					}

				});
	}

	public boolean addTower(Tower tower) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				tower);
		boolean test = jdbc
				.update("INSERT INTO towers (`doveId`, `latitude`, `longitude`) VALUES (:doveId, :latitude, :longitude)",
						params) == 1;
		return test;

	}
	
	

}
