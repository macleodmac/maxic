package com.maxic.towers.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String showTowers(Model model, @ModelAttribute("message") String message) {

		List<Tower> towers = towerService.getTowers();
		model.addAttribute("towers", towers);
		model.addAttribute("message", message);
		return "towers";
	}

	@RequestMapping(value = "/viewtower", method = RequestMethod.GET)
	public String showSingleTower(Model model, @RequestParam("t") String t) {
		int id = Integer.parseInt(t);
		Tower tower = towerService.getTower(id);
		model.addAttribute("tower", tower);
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
	public String doAdd(Model model, @Valid Tower tower, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "addtower";

		}
		towerService.addTower(tower);
		redirectAttributes.addFlashAttribute("message", "Tower successfully added!");
		return "redirect:/towers";
	}
	
	@RequestMapping(value = "/dodelete", method = RequestMethod.GET)
	public String deleteTower(Model model, @RequestParam("t") String t, RedirectAttributes redirectAttributes) {
		int id = Integer.parseInt(t);
		boolean success = towerService.deleteTower(id);
		
		if (success) {
			redirectAttributes.addFlashAttribute("message", "Tower successfully deleted.");
		return "redirect:/towers";
		}
		else {
			model.addAttribute("t", t);
			return ("redirect:/viewtower");
		}
	}
	

	@RequestMapping(value = "/edittower", method = RequestMethod.GET)
	public String editTower(Model model, @RequestParam("t") String t) {
		int id = Integer.parseInt(t);
		Tower tower = towerService.getTower(id);
		model.addAttribute("tower", tower);
		return "edittower";
	}
	
	@RequestMapping(value = "/doedit", method = RequestMethod.POST)
	public String doEdit(Model model, Tower tower, BindingResult result, @RequestParam("t") String t, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("t", t);
			return ("redirect:/edittower");

		}
		System.out.println(tower.getTowerId());
		System.out.println(t);
		redirectAttributes.addFlashAttribute("message", "Tower successfully edited!");
		return "redirect:/towers";
	}


}