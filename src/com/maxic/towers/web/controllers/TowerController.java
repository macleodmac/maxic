package com.maxic.towers.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maxic.towers.web.dao.Tower;
import com.maxic.towers.web.service.TowerService;

@Controller
public class TowerController {

	private TowerService towerService;
	
	
	@Autowired
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}
	
	@RequestMapping("/towers")
	public String showTowers(Model model) {
		
		List<Tower> towers = towerService.getTowers();
		System.out.println(towers.toString());
		model.addAttribute("towers", towers);
		
		return "towers";
	}
	
}
