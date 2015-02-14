package com.maxic.towers.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@RequestMapping(value = "/addtower")
	public String addTower(Model model) {
		model.addAttribute(new Tower());
		return "addtower";
	}

//	@RequestMapping(value = "/doadd", method = RequestMethod.POST)
//	public String doAdd(Model model, @Valid Tower tower, BindingResult result) {
//		if (result.hasErrors()) {
//			return "addtower";
//		}
//		towerService.addTower(tower);
//		return "towercreated";
//	}

}