package com.maxic.towers.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.maxic.towers.web.dao.Peal;
import com.maxic.towers.web.dao.Tower;
import com.maxic.towers.web.service.PealService;
import com.maxic.towers.web.service.TowerService;

@Controller
public class PealController {

	private TowerService towerService;
	private PealService pealService;

	@Autowired
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}

	@Autowired
	public void setPealService(PealService pealService) {
		this.pealService = pealService;
	}
	
//	@RequestMapping("/peals")
//	public String showPeals(Model model) {
//
//		List<Peal> peals = pealService.getPeals();
//		model.addAttribute("peals", peals);
//		return "/peals";
//	}
	
	@RequestMapping(value = "/peals/view", method = RequestMethod.GET)
	public String viewPeal(Model model, @RequestParam("p") int p) {
		Peal peal = pealService.getPeal(p);
		String towerDescription = towerService.getTowerDescriptor(peal.getTowerId());
		model.addAttribute("tower", towerDescription);
		model.addAttribute("peal", peal);
		return "/peals/view";
	}
	
	@RequestMapping(value = "/peals", method = RequestMethod.GET)
	public String viewTowerPeals(Model model, @RequestParam("t") String t) {
		List<Peal> peals;
		if (t.equals("all")) {
			peals = pealService.getPeals();
		} else {
			int towerId = Integer.parseInt(t);
			peals = pealService.getPealsForTower(towerId);
		}
		
		model.addAttribute("peals", peals);
		return "/peals";
	}

	
	
	
	
	
}