package com.maxic.towers.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class PracticeDao {

	private NamedParameterJdbcTemplate jdbc;
	
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public List<Practice> getPractice() {
		return jdbc.query("SELECT * FROM practice", new RowMapper<Practice>() {
			
			public Practice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Practice practice = new Practice(rs.getInt("towerId"), rs.getString("day"),
						rs.getString("time"),rs.getString("regularity"),rs.getString("visitorsWelcome"));
				return practice;
				}
			
		});
	}
	
	public Practice getPractice(int id){

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.queryForObject("SELECT * FROM practice where towerId = :id",params, new RowMapper<Practice>(){
			public Practice mapRow(ResultSet rs, int rowNum)throws SQLException{
				Practice practice=new Practice(rs.getInt("towerId"),rs.getString("day"),rs.getString("time"),
						rs.getString("regularity"),rs.getString("visitorsWelcome"));
			return practice;
			}
		});
	}
	
	public boolean addPractice (Practice practice){
		System.out.println("Adding practice " + practice.getTowerId());
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				practice);
		boolean test = jdbc.update("INSERT INTO practice (`day`,`time`,`regularity`,`visitorsWelcome`)"
				+ "VALUES (:day, :time, :regularity, :visitorsWelcome) ", params)==1;
		
		return test;
	
	}
	
	public boolean deletePractice(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.update("DELETE FROM practice WHERE towerId = :id", params) == 1;
	}
	
	public boolean editPractice(Practice practice) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				practice);
		boolean test = jdbc.update("UPDATE practice SET day=:day, "
				+ "time = :time, "+ "regularity = :regularity, "
				+ "visitorsWelcome = :visitorsWelcome" + "WHERE towerId = :towerId",
				params) ==1;
		return test;
	}
	public boolean addPractice(ArrayList<Practice> practiceList) {
		for (Practice practice : practiceList) {
			this.addPractice(practice);
		}
		return false;
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	