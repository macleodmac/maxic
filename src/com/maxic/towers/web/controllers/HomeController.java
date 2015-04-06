package com.maxic.towers.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maxic.towers.web.model.Peal;
import com.maxic.towers.web.model.TowerShort;
import com.maxic.towers.web.service.ContactDetailsService;
import com.maxic.towers.web.service.PealService;
import com.maxic.towers.web.service.TowerService;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);
	private TowerService towerService;
	private PealService pealService;
	private ContactDetailsService contactDetailsService;

	@Autowired
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}

	@Autowired
	public void setPealService(PealService pealService) {
		this.pealService = pealService;
	}
	
	@Autowired
	public void setContactDetailsService(ContactDetailsService contactDetailsService) {
		this.contactDetailsService = contactDetailsService;
	}

	@RequestMapping("/")
	public String showHome() {
		logger.info("Showing homepage.");
		return "home";
	}

	@RequestMapping(value = "/towers/gettowers", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getTowers() {

		List<TowerShort> towers = null;

		towers = towerService.getTowersShort();

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("towers", towers);
		data.put("number", towers.size());

		return data;
	}

	@RequestMapping(value = "/peals/getpeals", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getPeals() {

		List<Peal> peals = null;

		peals = pealService.getPeals();

		Map<String, Object> pealMap = new HashMap<String, Object>();

		pealMap.put("peals", peals);
		pealMap.put("number", peals.size());

		return pealMap;
	}
	

	@RequestMapping(value="/about")
	 public String showAbout(){
	  return "/about";
	 }
}
