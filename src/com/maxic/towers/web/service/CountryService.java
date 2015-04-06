package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.model.*;
import com.maxic.towers.web.dao.CountryDao;


@Service("countryService")
public class CountryService {
	private CountryDao countryDao;

	@Autowired
	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	public List<Country> getCountries() {
		return countryDao.getCountries();
	}
	
	public void addCountry(Country country) {
		countryDao.addCountry(country);
	}

	public Country getCountry(String id) {
		return countryDao.getCountry(id);
	}

	public void deleteCountry(String id) {
		countryDao.deleteCountry(id);
	}

	public void editCountry(Country country) {
		countryDao.editCountry(country);
		
	}

	public void addCountries(ArrayList<Country> countryList) {
		countryDao.addCountries(countryList);
	}

	public boolean countryExists(String isoCode) {
		return countryDao.countryExists(isoCode);
	}

	public Map<String, String> getCountryMap() {
		return countryDao.getCountryMap();
	}
}
