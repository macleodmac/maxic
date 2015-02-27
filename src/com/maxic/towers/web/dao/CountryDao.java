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

@Component("countryDao")
public class CountryDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	public List<Country> getCountries() {

		return jdbc.query("SELECT * FROM countries", new RowMapper<Country>() {

			public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
				Country country = new Country(rs.getInt("countryId"), rs
						.getString("name"), rs.getString("ISOcode"));
				return country;
			}
			
		});
	}
	
	public Country getCountry(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("SELECT * FROM countries where countryId = :id",
				params, new RowMapper<Country>() {

					public Country mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Country country = new Country(rs.getInt("countryId"), rs
								.getString("name"), rs.getString("ISOcode"));
						return country;
					}

				});
	}
	
	public boolean addCountry(Country country) {
		System.out.println("Adding country " + country.getname());
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				country);
		boolean test = jdbc
				.update("INSERT INTO countries (`name`, `ISOcode`)",
						params) == 1;
		return test;

	}
	
	public boolean deleteCountry(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.update("DELETE FROM countries WHERE countryId = :id", params) == 1;
	}
	
	public boolean editCountry(Country country) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				country);
		boolean test = jdbc.update("UPDATE countries SET name = :name, "
				+ "ISOcode = :ISOcode, ",params) == 1;
		return test;
	}
	public boolean addCountries(ArrayList<Country> countrylist) {
		for (Country country : countrylist) {
			this.addCountry(country);
		}
		return false;
	}
}