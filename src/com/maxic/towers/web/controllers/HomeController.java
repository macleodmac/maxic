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
import com.maxic.towers.web.service.PealService;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);
	private PealService pealService;

	@Autowired
	public void setPealService(PealService pealService) {
		this.pealService = pealService;
	}

	@RequestMapping("/")
	public String showHome() {
		logger.info("Showing homepage.");
		return "home";
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
