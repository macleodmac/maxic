package com.maxic.towers.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maxic.towers.web.dao.Peal;
import com.maxic.towers.web.dao.Tower;
import com.maxic.towers.web.dao.TowerDescriptor;
import com.maxic.towers.web.processing.Parser;
import com.maxic.towers.web.service.ContactDetailsService;
import com.maxic.towers.web.service.PealService;
import com.maxic.towers.web.service.TowerService;

@Controller
public class AdminController {

	/*
	 * Defining and autowiring services
	 */
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
	public void setContactDetailsService(
			ContactDetailsService contactDetailsService) {
		this.contactDetailsService = contactDetailsService;
	}

	/**
	 * Fetches a list of all towers from the tower service and returns to the
	 * page
	 * 
	 * @param message
	 *            arbitrary message to display on the page
	 * @return /admin/towers view
	 */
	@RequestMapping("/admin/towers")
	public String showTowers(Model model,
			@ModelAttribute("message") String message) {

		List<TowerDescriptor> towers = towerService.getTowerDescriptors();
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

	/**
	 * Creates a new instance of tower and returns to the page
	 * 
	 * @return /admin/towers/add view
	 */
	@RequestMapping(value = "/admin/towers/add")
	public String addTower(Model model) {
		model.addAttribute("tower", new Tower());
		return "/admin/towers/addtower";
	}

	/**
	 * Fetches tower from page using POST, checks for errors, adds tower to
	 * database using service
	 * 
	 * @param tower
	 *            tower object fetched from form submission
	 * @return redirect:/admin/towers/towers view if successful
	 */
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

	/**
	 * Fetches tower id from page, deletes tower using service, adds
	 * success/failure flashAttribute
	 * 
	 * @param t
	 *            string identifying tower by towerId
	 * @return redirect:/admin/towers view if successful
	 */
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
	public String editTower(Model model, @RequestParam("t") String t) {
		int id = Integer.parseInt(t);
		Tower tower = towerService.getTower(id);
		model.addAttribute("tower", tower);
		return "/admin/towers/edittower";
	}

	@RequestMapping(value = "/admin/towers/doedit", method = RequestMethod.POST)
	public String doEdit(Model model, Tower tower, BindingResult result,
			@RequestParam("t") String t, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("t", t);
			redirectAttributes.addAttribute("t", t);
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

	@RequestMapping("/admin/peals/add")
	public String showAddPeal(Model model) {

		Peal peal = new Peal();
		model.addAttribute("peal", peal);
		return "/admin/peals/addpeal";
	}

	@RequestMapping(value = "/admin/peals/doadd", method = RequestMethod.POST)
	public String doAddPeal(Model model, @Valid Peal peal,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/admin/peals/addpeal";
		}
		pealService.addPeal(peal);
		redirectAttributes.addFlashAttribute("message",
				"Peal successfully added!");
		return ("redirect:/admin/peals");
	}

	@RequestMapping(value = "/admin/peals/edit", method = RequestMethod.GET)
	public String showEditPeal(Model model, @RequestParam("p") String p) {
		int pealId = Integer.parseInt(p);
		Peal peal = pealService.getPeal(pealId);
		model.addAttribute("peal", peal);

		return "/admin/peals/editpeal";
	}

	@RequestMapping(value = "/admin/peals/doedit", method = RequestMethod.POST)
	public String doEditPeal(Model model, Peal peal, BindingResult result,
			@RequestParam("p") String p, RedirectAttributes redirectAttributes) {
		model.addAttribute("p", p);
		if (result.hasErrors()) {
			for (Object error : result.getAllErrors()) {
				System.out.println(error);
			}
			redirectAttributes.addAttribute("p", p);
			return ("redirect:/admin/peals/edit");
		}
		System.out.println("Trying to edit peal");
		boolean editResult = pealService.editPeal(peal);
		if (editResult) {
			redirectAttributes.addFlashAttribute("message",
					"Peal successfully edited!");
			return ("redirect:/admin/peals");
		} else {
			redirectAttributes.addFlashAttribute("message",
					"Peal not successfully edited. Please try again.");
			redirectAttributes.addAttribute("p", p);
			return ("redirect:/admin/peals/edit");
		}
	}

	@RequestMapping(value = "/admin/peals/dodelete", method = RequestMethod.GET)
	public String deletePeal(Model model, @RequestParam("p") String p,
			RedirectAttributes redirectAttributes) {
		int pealId = Integer.parseInt(p);
		boolean success = pealService.deletePeal(pealId);

		if (success) {
			redirectAttributes.addFlashAttribute("message",
					"Peal successfully deleted.");
			return "redirect:/admin/peals";
		} else {
			model.addAttribute("p", p);
			return ("redirect:/admin/peals/edit");
		}
	}

	@RequestMapping("/admin/dashboard")
	public String showDashboard(Model model) {

		return "/admin/dashboard";
	}

	@RequestMapping("/admin/manual")
	public String showManual(Model model) {

		return "/admin/manual";
	}

	@RequestMapping("/admin/documentation")
	public String showDocumentation(Model model) {

		return "/admin/documentation";
	}

	@RequestMapping(value = "/towerlist", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getTowerList() {

		List<TowerDescriptor> towers = null;
		towers = towerService.getTowerDescriptors();
		
		Map<String, Object> towerMap = new HashMap<String, Object>();

		towerMap.put("towers", towers);
		towerMap.put("number", towers.size());

		return towerMap;
	}

}
