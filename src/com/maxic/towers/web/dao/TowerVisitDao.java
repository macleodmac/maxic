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
//import org.springframework.stereotype.Component;


public class TowerVisitDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<TowerVisit> getTowerVisits() {

		return jdbc.query("SELECT * FROM towerVisits", new RowMapper<TowerVisit>() {

			public TowerVisit mapRow(ResultSet rs, int rowNum) throws SQLException {
				TowerVisit towerVisit = new TowerVisit(rs.getInt("towerId"), rs
						.getInt("visitId"), rs.getString("userName"), rs
						.getString("date"), rs.getString("time"), rs
								.getBoolean("rung"), rs
								.getBoolean("pealRung"), rs
								.getBoolean("quarterPealRung"), rs
								.getString("notes"));
				return towerVisit;
			}

		});
	}

	public TowerVisit getTowerVisit(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("SELECT * FROM towerVisit where towerId = :id",
				params, new RowMapper<TowerVisit>() {

					public TowerVisit mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						TowerVisit towerVisit = new TowerVisit(rs.getInt("towerId"), rs
								.getInt("visitId"), rs.getString("userName"),
								rs.getString("date"), rs
										.getString("time"), rs
										.getBoolean("rung"), rs
										.getBoolean("pealRung"), rs
										.getBoolean("quarterPealRung"), rs
										.getString("notes"));
						return towerVisit;
					}

				});
	}

	public boolean addTowerVisit(TowerVisit towerVisit) {
		System.out.println("Adding towerVisit " + towerVisit.getVisitId());
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				towerVisit);
		boolean test = jdbc
				.update("INSERT INTO towerVisit (`visitId`, `userName`, `date`,"
						+ " `time`, `rung`, `pealRung`,"
						+ " `quarterPealRung`, `notes`)"
						+ " VALUES (:visitId, :userName, :date,"
						+ " :time, :rung, :pealRung,"
						+ " :quarterPealRung, :notes)",
						params) == 1;
		return test;

	}

	public boolean deleteTowerVisit(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.update("DELETE FROM towerVisits WHERE towerId = :id", params) == 1;
	}

	public boolean editTowerVisit(TowerVisit towerVisit) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				towerVisit);
		boolean test = jdbc.update("UPDATE towerVisit SET visitId = :visitId, "
				+ "userName = :userName, " + "date = :date, "
				+ "time = :time, " + "rung = :rung, "
				+ "pealRung = :pealRung, "
				+ "quarterPealRung = :quarterPealRung, " + "notes = :notes, ",
				params) == 1;
		return test;
	}

	public boolean addTowerVisit(ArrayList<TowerVisit> towerVisitList) {
		for (TowerVisit towerVisit : towerVisitList) {
			this.addTowerVisit(towerVisit);
		}
		return false;
	}

}
