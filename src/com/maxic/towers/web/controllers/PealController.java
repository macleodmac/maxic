package com.maxic.towers.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.maxic.towers.web.dao.Peal;
import com.maxic.towers.web.service.PealService;

@Controller
public class PealController {

	private PealService pealService;

	@Autowired
	public void setPealService(PealService pealService) {
		this.pealService = pealService;
	}
	
	@RequestMapping("/peals")
	public String showPeals(Model model) {

		List<Peal> peals = pealService.getPeals();
		model.addAttribute("peals", peals);
		return "/peals";
	}
	
	@RequestMapping(value = "/peals/view", method = RequestMethod.GET)
	public String viewPeal(Model model, @RequestParam("p") String p) {
		int id = Integer.parseInt(p);
		Peal peal = pealService.getPeal(id);
		model.addAttribute("peal", peal);
		return "/peals/view";
	}

	
	
	
	
	
}