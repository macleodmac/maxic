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
				tower.setDoveId(rs.getString("doveId"));
				tower.setPlaceName((rs.getString("placeName")));
				tower.setLatitude((rs.getFloat("lat")));
				tower.setLongitude((rs.getFloat("long")));
				return tower;
			}

		});
	}


	public boolean addTower(Tower tower) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				tower);
		System.out.println(tower.getDoveId());
		boolean test = jdbc
				.update("INSERT INTO towers (`doveId`, `lat`, `long`) VALUES (:doveId, :latitude, :longitude)",
						params) == 1;
		return test;

	}

}
