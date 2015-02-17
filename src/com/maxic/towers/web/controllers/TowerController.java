package com.maxic.towers.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping("/towers")
	public String showTowers(Model model) {

		List<Tower> towers = towerService.getTowers();
		System.out.println(towers.toString());
		model.addAttribute("towers", towers);

		return "towers";
	}

	@RequestMapping(value = "/viewtower", method = RequestMethod.GET)
	public String showSingleTower(Model model, @RequestParam("t") String t) {
		int id = Integer.parseInt(t);
		Tower tower = towerService.getTower(id);
		model.addAttribute("tower", tower);
		System.out.println(tower.getTowerId());
		return "viewtower";
	}

	@RequestMapping(value = "/addtower")
	public String addTower(Model model) {
		model.addAttribute("tower", new Tower());
		return "addtower";
	}

	@RequestMapping(value = "/createtower")
	public String createTower(Model model) {
		model.addAttribute("tower", new Tower());
		return "createtower";
	}

	@RequestMapping(value = "/doadd", method = RequestMethod.POST)
	public String doAdd(Model model, @Valid Tower tower, BindingResult result) {
		if (result.hasErrors()) {
			return "addtower";

		}
		towerService.addTower(tower);
		return "towercreated";
	}

}