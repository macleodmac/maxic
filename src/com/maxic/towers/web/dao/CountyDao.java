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

@Component("countyDao")
public class CountyDao {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	public List<County> getCounties() {

		return jdbc.query("SELECT * FROM counties", new RowMapper<County>() {

			public County mapRow(ResultSet rs, int rowNum) throws SQLException {
				County county = new County(rs.getString("countyId"), rs
						.getString("name"));
				return county;
			}
			
		});
	}
	public County getCounty(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("SELECT * FROM counties where countyId = :id",
				params, new RowMapper<County>() {

					public County mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						County county = new County(rs.getString("countyId"), rs
								.getString("name"));
						return county;
					}

				});
	}
	public boolean addCounty(County county) {
		System.out.println("Adding county " + county.getname());
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				county);
		boolean test = jdbc
				.update("INSERT INTO counties (`name`)",
						params) == 1;
		return test;

	}
	public boolean deleteCounty(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.update("DELETE FROM counties WHERE countyId = :id", params) == 1;
	}
	public boolean editCounty(County county) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				county);
		boolean test = jdbc.update("UPDATE counties SET name = :name, ",params) == 1;
		return test;
	}	
	public boolean addCounties(ArrayList<County> countylist) {
		for (County county : countylist) {
			this.addCounty(county);
		}
		return false;
	}
}
