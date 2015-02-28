package com.maxic.towers.web.controllers;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maxic.towers.web.dao.ContactDetails;
import com.maxic.towers.web.dao.Peal;
import com.maxic.towers.web.dao.Tower;
import com.maxic.towers.web.processing.Parser;
import com.maxic.towers.web.service.ContactDetailsService;
import com.maxic.towers.web.service.PealService;
import com.maxic.towers.web.service.TowerService;

@Controller
public class AdminController {

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


	@RequestMapping("/admin/towers")
	public String showTowers(Model model,
			@ModelAttribute("message") String message) {

		List<Tower> towers = towerService.getTowers();
		model.addAttribute("towers", towers);
		model.addAttribute("message", message);
		return "/admin/towers";
	}

	// @RequestMapping(value = "/viewtower", method = RequestMethod.GET)
	// public String showSingleTower(Model model, @RequestParam("t") String t) {
	// int id = Integer.parseInt(t);
	// Tower tower = towerService.getTower(id);
	// model.addAttribute("tower", tower);
	// return "viewtower";
	// }

	@RequestMapping(value = "/admin/towers/add")
	public String addTower(Model model) {
		model.addAttribute("tower", new Tower());
		return "/admin/towers/addtower";
	}

	@RequestMapping(value = "/admin/towers/doadd", method = RequestMethod.POST)
	public String doAdd(Model model, @Valid Tower tower, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/admin/towers/addtower";

		}
		towerService.addTower(tower);
		redirectAttributes.addFlashAttribute("message",
				"Tower successfully added!");
		return "redirect:/admin/towers";
	}

	@RequestMapping(value = "/admin/towers/dodelete", method = RequestMethod.GET)
	public String deleteTower(Model model, @RequestParam("t") String t,
			RedirectAttributes redirectAttributes) {
		int id = Integer.parseInt(t);
		boolean success = towerService.deleteTower(id);

		if (success) {
			redirectAttributes.addFlashAttribute("message",
					"Tower successfully deleted.");
			return "redirect:/admin/towers";
		} else {
			model.addAttribute("t", t);
			return ("redirect:/admin/towers/edit");
		}
	}

	@RequestMapping(value = "/admin/towers/edit", method = RequestMethod.GET)
	public String editTower(Model model, @RequestParam("t") String t, @RequestParam("c") String c) {
		int id = Integer.parseInt(t);
		int contact = Integer.parseInt(c);
		if (contact == 1) {
			model.addAttribute("contactMessage", "true");
			model.addAttribute("contactDetails", new ContactDetails());
		}
		Tower tower = towerService.getTower(id);
		model.addAttribute("tower", tower);
		return "/admin/towers/edittower";
	}

	@RequestMapping(value = "/admin/towers/doedit", method = RequestMethod.POST)
	public String doEdit(Model model, Tower tower, BindingResult result,
			@RequestParam("t") String t, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("t", t);
			return ("redirect:/admin/towers/edit");

		}
		towerService.editTower(tower);
		redirectAttributes.addFlashAttribute("message",
				"Tower successfully edited!");
		return "redirect:/admin/towers";
	}

	@RequestMapping(value = "/admin/towers/parsedove")
	public String parseDove(Model model) {
		Parser parser = new Parser();
		ArrayList<Tower> towerList = parser.parseDoveFile();
		towerService.addTowers(towerList);
		return "home";
	}
	
	@RequestMapping("/admin/peals")
	public String showPeals(Model model,
			@ModelAttribute("message") String message) {

		List<Peal> peals = pealService.getPeals();
		model.addAttribute("peals", peals);
		model.addAttribute("message", message);
		return "/admin/peals";
	}

}
