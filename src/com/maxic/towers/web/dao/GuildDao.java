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

@Component("guildDao")
public class GuildDao {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	public List<Guild> getGuilds() {

		return jdbc.query("SELECT * FROM guilds", new RowMapper<Guild>() {

			public Guild mapRow(ResultSet rs, int rowNum) throws SQLException {
				Guild guild = new Guild(rs.getString("guildId"), rs
						.getString("name"), rs.getString("website"));
				return guild;
			}
			
		});
	}
	public Guild getGuild(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("SELECT * FROM guilds where guildId = :id",
				params, new RowMapper<Guild>() {

					public Guild mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Guild guild = new Guild(rs.getString("guildId"), rs
								.getString("name"),rs.getString("website"));
						return guild;
					}

				});
	}
	public boolean addGuild(Guild guild) {
		System.out.println("Adding guild " + guild.getname());
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				guild);
		boolean test = jdbc
				.update("INSERT INTO guilds (`name`, `website`)",
						params) == 1;
		return test;

	}
	public boolean deleteGuild(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.update("DELETE FROM guilds WHERE guildId = :id", params) == 1;
	}
	public boolean editGuild(Guild guild) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				guild);
		boolean test = jdbc.update("UPDATE guilds SET name = :name, "
				+ "website = :website, ",params) == 1;
		return test;
	}	
	public boolean addGuilds(ArrayList<Guild> guildlist) {
		for (Guild guild : guildlist) {
			this.addGuild(guild);
		}
		return false;
	}
}
