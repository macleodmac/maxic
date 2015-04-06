package com.maxic.towers.web.dao;
import com.maxic.towers.web.model.*;
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

@Component("ringerDao")
public class RingerDao {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	public List<Ringer> getRingers() {

		return jdbc.query("SELECT * FROM ringers", new RowMapper<Ringer>() {

			public Ringer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ringer ringer = new Ringer(rs.getInt("paelId"), rs
						.getInt("bellnumber"), rs.getString("name"));
				return ringer;
			}
			
		});
	}
	public Ringer getRinger(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("SELECT * FROM ringers where pealId = :id",
				params, new RowMapper<Ringer>() {

					public Ringer mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Ringer ringer = new Ringer(rs.getInt("pealId"), rs
								.getInt("bellnumber"),rs.getString("name"));
						return ringer;
					}

				});
	}
	public boolean addRinger(Ringer ringer) {
		System.out.println("Adding ringer " + ringer.getname());
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				ringer);
		boolean test = jdbc
				.update("INSERT INTO ringers (`bellnumber`,`name`)",
						params) == 1;
		return test;

	}
	public boolean deleteRinger(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.update("DELETE FROM ringers WHERE pealId = :id", params) == 1;
	}
	public boolean editRinger(Ringer ringer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				ringer);
		boolean test = jdbc.update("UPDATE ringers SET name = :name, ",params) == 1;
		return test;
	}	
	public boolean addRingers(ArrayList<Ringer> ringerlist) {
		for (Ringer ringer : ringerlist) {
			this.addRinger(ringer);
		}
		return false;
	}	
}
