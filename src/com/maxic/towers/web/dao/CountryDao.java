package com.maxic.towers.web.dao;

import com.maxic.towers.web.model.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("countryDao")
public class CountryDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Fetches all countries from the database
	 * 
	 * @return list of countries
	 */
	@SuppressWarnings("unchecked")
	public List<Country> getCountries() {

		return session().createQuery("from Country c order by c.name").list();
	}

	/**
	 * Fetches country from the database for a given countryId
	 * 
	 * @param countryId
	 * @return Country
	 */
	public Country getCountry(String id) {

		Criteria crit = session().createCriteria(Country.class);
		crit.add(Restrictions.idEq(id));
		Country country = (Country) crit.uniqueResult();

		return country;
	}

	/**
	 * Check that country exists for a given countryId
	 * 
	 * 
	 * @param countryId
	 * @return boolean
	 */
	public boolean countryExists(String id) {

		Criteria crit = session().createCriteria(Country.class);
		crit.add(Restrictions.idEq(id));
		Country country = (Country) crit.uniqueResult();

		return country != null;
	}

	/**
	 * Persists country to database
	 * 
	 * @param country
	 *            to persist
	 */
	public void addCountry(Country country) {
		session().saveOrUpdate(country);

	}

	/**
	 * Delete country details from the database for a given countryId
	 * 
	 * 
	 * @param countryId
	 *            to delete
	 * @return boolean success
	 */
	public boolean deleteCountry(String id) {
		String hql = "delete from Country where isoCode = :id";
		return session().createQuery(hql).setString("id", id).executeUpdate() == 1;

	}

	/**
	 * Update given country in the database
	 * 
	 * 
	 * @param country
	 *            to update
	 */
	public void editCountry(Country country) {
		session().update(country);
	}

	/**
	 * Persists country list to database
	 * 
	 * @param list
	 *            of countries to persist
	 */
	public void addCountries(ArrayList<Country> countrylist) {
		for (Country country : countrylist) {
			this.addCountry(country);
		}
	}

	/**
	 * Fetches map of countries from database
	 * 
	 * @return hashMap of country codes and names
	 */
	public Map<String, String> getCountryMap() {
		List<Country> countries = this.getCountries();
		LinkedHashMap<String, String> hm = new LinkedHashMap<String, String>();
		for (Country country : countries) {
			hm.put(country.getIsoCode(),
					country.getName() + " (" + country.getIsoCode() + ")");
		}
		return hm;
	}
}