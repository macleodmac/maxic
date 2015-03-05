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

@Component("contactDetailsDao")
public class ContactDetailsDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<ContactDetails> getContactDetails() {

		return jdbc.query("SELECT * FROM ContactDetails",
				new RowMapper<ContactDetails>() { // not sure

					public ContactDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ContactDetails ContactDetails = new ContactDetails(rs
								.getInt("towerId"), rs.getInt("contactId"), rs
								.getString("email"), rs
								.getString("contactNumber"), rs
								.getString("website"), rs
								.getString("facebookPage"), rs
								.getString("twitterPage"));
						return ContactDetails;
					}

				});
	}

	public List<ContactDetails> getContactDetails(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.query("SELECT * FROM ContactDetails where towerId = :id",
				params, new RowMapper<ContactDetails>() {

					public ContactDetails mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ContactDetails ContactDetails = new ContactDetails(rs
								.getInt("towerId"), rs.getInt("contactId"), rs
								.getString("email"), rs
								.getString("contactNumber"), rs
								.getString("website"), rs
								.getString("facebookPage"), rs
								.getString("twitterPage"));
						return ContactDetails;
					}

				});
	}

	public boolean addContactDetails(ContactDetails ContactDetails) {
		System.out.println("Adding ContactDetails "
				+ ContactDetails.getTowerId());
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				ContactDetails);
		boolean test = jdbc.update(
				"INSERT INTO ContactDetails (`email`, `contactNumber`, `website`,"
						+ " `facebookPage`, `twitterPage`)"
						+ " VALUES (:email, :contactNumber, :website,"
						+ " :facebookPage, :twitterPage)", params) == 1;
		return test;

	}

	public boolean deleteContactDetails(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.update("DELETE FROM towers WHERE towerId = :id", params) == 1;
	}

	public boolean editContactDetails(ContactDetails ContactDetails) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				ContactDetails);
		boolean test = jdbc.update("UPDATE ContactDetails SET email = :email, "
				+ "contactNumber = :contactNumber, " + "website = :website, "
				+ "facebookPage = :facebookPage, "
				+ "twitterPage = :twitterPage, ", params) == 1;
		return test;
	}

	public boolean addContactDetails(
			ArrayList<ContactDetails> ContactDetailsList) {
		for (ContactDetails ContactDetails : ContactDetailsList) {
			this.addContactDetails(ContactDetails);
		}
		return false;
	}

}
