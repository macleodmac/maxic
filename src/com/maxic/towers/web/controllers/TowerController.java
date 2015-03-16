package com.maxic.towers.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.maxic.towers.web.dao.Practice;
import com.maxic.towers.web.dao.Tower;
import com.maxic.towers.web.dao.TowerWrapper;
import com.maxic.towers.web.service.PracticeService;
import com.maxic.towers.web.service.TowerService;

@Controller
public class TowerController {

	private TowerService towerService;
	private PracticeService practiceService;

	@Autowired
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}
	
	@Autowired
	public void setPracticeService(PracticeService practiceService) {
		this.practiceService = practiceService;
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
		TowerWrapper towerWrapper = new TowerWrapper();
		
		towerWrapper.setTower(towerService.getTower(id));
		
		List<Practice> practices = practiceService.getPractices(id);
		
		if (!practices.isEmpty()) {
			towerWrapper.setPracticeList(practices);
		}
		
		
		model.addAttribute("towerWrapper", towerWrapper);
		return "/towers/modal";
	}
	
	
	
	
}