package com.maxic.towers.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxic.towers.web.dao.Country;
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
	
	public boolean addCountry(Country country) {
		return countryDao.addCountry(country);
	}

	public Country getCountry(int id) {
		return countryDao.getCountry(id);
	}

	public boolean deleteCountry(int id) {
		return countryDao.deleteCountry(id);
	}

	public boolean editCountry(Country country) {
		return countryDao.editCountry(country);
		
	}

	public boolean addCountries(ArrayList<Country> countryList) {
		return countryDao.addCountries(countryList);
	}
}
