package com.maxic.towers.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.maxic.towers.web.dao.Tower;
import com.maxic.towers.web.service.TowerService;

@Controller
public class TowerController {

	private TowerService towerService;

	@Autowired
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}

	@RequestMapping(value = "/towers/view", method = RequestMethod.GET)
	public String viewTower(Model model, @RequestParam("t") String t) {
		int id = Integer.parseInt(t);
		Tower tower = towerService.getTower(id);
		model.addAttribute("tower", tower);
		return "/towers/view";
	}
	
	@RequestMapping(value = "/towers/modal", method = RequestMethod.GET)
	public String viewTowerModal(Model model, @RequestParam("t") String t) {
		int id = Integer.parseInt(t);
		Tower tower = towerService.getTower(id);
		model.addAttribute("tower", tower);
		return "/towers/modal";
	}
	
	
	
	
}