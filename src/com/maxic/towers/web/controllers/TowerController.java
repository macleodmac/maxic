package com.maxic.towers.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.maxic.towers.web.model.ContactDetails;
import com.maxic.towers.web.model.Peal;
import com.maxic.towers.web.model.Practice;
import com.maxic.towers.web.model.TowerWrapper;
import com.maxic.towers.web.service.ContactDetailsService;
import com.maxic.towers.web.service.PealService;
import com.maxic.towers.web.service.PracticeService;
import com.maxic.towers.web.service.TowerService;

@Controller
public class TowerController {

	private TowerService towerService;
	private PracticeService practiceService;
	private ContactDetailsService contactDetailsService;
	private PealService pealService;
	
	@Autowired
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}

	@Autowired
	public void setPracticeService(PracticeService practiceService) {
		this.practiceService = practiceService;
	}

	@Autowired
	public void setContactDetailsService(
			ContactDetailsService contactDetailsService) {
		this.contactDetailsService = contactDetailsService;
	}
	
	@Autowired
	public void setPealService(PealService pealService) {
		this.pealService = pealService;
	}

	/**
	 * Fetches tower id from page, fetches tower using service, wraps it in a tower wrapper
	 * 
	 * @param t string identifying tower by towerId
	 * @return /towers/view view if successful
	 */
	@RequestMapping(value = "/towers/view", method = RequestMethod.GET)
	public String viewTower(Model model, @RequestParam("t") int t) {

		TowerWrapper towerWrapper = new TowerWrapper();

		towerWrapper.setTower(towerService.getTower(t));

		List<Practice> practices = practiceService.getPractices(t);
		List<ContactDetails> contactDetails = contactDetailsService
				.getContactDetails(t);
		List<Peal> pealList = pealService.getPealsForTower(t);
		
		if (!practices.isEmpty()) {
			towerWrapper.setPracticeList(practices);
		}

		if (!contactDetails.isEmpty()) {
			towerWrapper.setContactDetailsList(contactDetails);
		}

		model.addAttribute("peals", pealList);
		model.addAttribute("towerWrapper", towerWrapper);
		return "/towers/view";
	}

	/**
	 * Fetches tower id from page, fetches tower using service, wraps it in a tower wrapper
	 * 
	 * @param t string identifying tower by towerId
	 * @return /towers/modal view if successful
	 */
	@RequestMapping(value = "/towers/modal", method = RequestMethod.GET)
	public String viewTowerModal(Model model, @RequestParam("t") int t) {
		TowerWrapper towerWrapper = new TowerWrapper();

		towerWrapper.setTower(towerService.getTower(t));

		List<Practice> practices = practiceService.getPractices(t);
		List<ContactDetails> contactDetails = contactDetailsService.getContactDetails(t);
		List<Peal> pealList = pealService.getPealsForTower(t);
		
		if (!practices.isEmpty()) {
			towerWrapper.setPracticeList(practices);
		}

		if (!contactDetails.isEmpty()) {
			towerWrapper.setContactDetailsList(contactDetails);
		}
		
		model.addAttribute("peals", pealList);

		model.addAttribute("towerWrapper", towerWrapper);
		return "/towers/modal";
	}
	
	@RequestMapping(value = "/towers", method = RequestMethod.GET)
	public String showTowers(Model model) {
		return "/towers";
	}

}