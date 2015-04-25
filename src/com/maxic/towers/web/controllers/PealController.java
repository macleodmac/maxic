package com.maxic.towers.web.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maxic.towers.web.model.Peal;
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

	@RequestMapping("/peals/add")
	public String showAddPeal(Model model) {
		Peal peal = new Peal();
		Map<Integer, String> hm = towerService.getTowerDescriptorMap();
		model.addAttribute("towers", hm);
		model.addAttribute("peal", peal);
		return "/peals/add";
	}

	@RequestMapping(value = "/peals/doadd", method = RequestMethod.POST)
	public String doAddPeal(Model model, @Valid Peal peal,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("message",
					"Peal not added. An error occured in the input");
			Map<Integer, String> hm = towerService.getTowerDescriptorMap();
			model.addAttribute("towers", hm);
			return "/peals/add";
		}
		pealService.addPeal(peal);

		redirectAttributes.addFlashAttribute("message",
				"Peal successfully added!");
		redirectAttributes.addAttribute("t", "all");
		return ("redirect:/peals");
	}

	@RequestMapping(value = "/peals/view", method = RequestMethod.GET)
	public String viewPeal(Model model, @RequestParam("p") int p) {
		Peal peal = pealService.getPeal(p);
		String towerDescription = towerService.getTowerDescriptor(peal
				.getTowerId());
		model.addAttribute("tower", towerDescription);
		model.addAttribute("peal", peal);
		return "/peals/view";
	}

	@RequestMapping(value = "/peals")
	public String viewPeals(Model model) {
		Map<Integer, String> hm = towerService.getTowerDescriptorMap();
		model.addAttribute("towers", hm);
		return "/peals";
	}

}