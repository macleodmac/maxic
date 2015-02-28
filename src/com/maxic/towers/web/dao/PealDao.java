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

@Component("pealDao")
public class PealDao {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Peal> getPeals() {

		return jdbc.query("SELECT * FROM peals", new RowMapper<Peal>() {

			public Peal mapRow(ResultSet rs, int rowNum) throws SQLException {
				Peal peal = new Peal(rs.getInt("towerId"), rs.getInt("pealId"),
						rs.getString("dedication"), rs.getString("dateRung"),
						rs.getString("time"), rs.getString("tenor"), rs
								.getString("method"), rs
								.getString("methodDetails"), rs
								.getInt("changes"), rs.getString("leader"), rs
								.getString("composer"), rs
								.getString("footnotes"), rs
								.getString("composition"));

				return peal;
			}

		});
	}

	public Peal getPeal(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("SELECT * FROM peals where towerId = :id",
				params, new RowMapper<Peal>() {
					public Peal mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Peal peal = new Peal(rs.getInt("towerId"), rs
								.getInt("pealId"),
								rs.getString("dedication"), rs
										.getString("dateRung"), rs
										.getString("time"), rs
										.getString("tenor"), rs
										.getString("method"), rs
										.getString("methodDetails"), rs
										.getInt("changes"), rs
										.getString("leader"), rs
										.getString("composer"), rs
										.getString("footnotes"), rs
										.getString("composition"));

						return peal;
					}
				});
	}

	public boolean addPeal(Peal peal) {
		System.out.println("Adding peal " + peal.getPealId());
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				peal);
		boolean test = jdbc
				.update("INSERT INTO peals (`pealId`,`dedication`,"
						+ "`dateRung`,`time`,`tenor`,`method`,`methodDetails`,`changes`,"
						+ "`leader`,`composer`,`footnotes`,`composition`)"
						+ "VALUES (:pealId, :dedication, "
						+ ":dateRung, :time, :tenor, :method, :methodDetails, :changes, "
						+ ":leader, :composer, :footnotes, :composition)",
						params) == 1;
		return test;
	}

	public boolean deletePeal(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.update("DELETE FROM peals WHERE pealId = :id", params) == 1;

	}

	public boolean editPeal(Peal peal) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				peal);
		boolean test = jdbc.update("UPDATE peals SET pealId = :pealId, "
				+ "dedication = :dedication, " + "dateRung = :dateRung,"
				+ "time = :time," + "tenor = :tenor," + "method = :method,"
				+ "methodDetails = :methodDetails," + "changes = :changes, "
				+ "leader = :leader," + "composer = :composer,"
				+ "footnotes = :footnotes," + "composition = :composition"
				+ "WHERE pealId = :pealId", params) == 1;
		return test;
	}

	public boolean addPeals(ArrayList<Peal> pealList) {
		for (Peal peal : pealList) {
			this.addPeal(peal);
		}
		return false;
	}

}
