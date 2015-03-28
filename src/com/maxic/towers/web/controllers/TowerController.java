package com.maxic.towers.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.maxic.towers.web.dao.ContactDetails;
import com.maxic.towers.web.dao.Peal;
import com.maxic.towers.web.dao.Practice;
import com.maxic.towers.web.dao.Tower;
import com.maxic.towers.web.dao.TowerWrapper;
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

	@RequestMapping(value = "/towers/view", method = RequestMethod.GET)
	public String viewTower(Model model, @RequestParam("t") int t) {

		TowerWrapper towerWrapper = new TowerWrapper();

		towerWrapper.setTower(towerService.getTower(t));

		List<Practice> practices = practiceService.getPractices(t);
		List<ContactDetails> contactDetails = contactDetailsService
				.getContactDetails(t);

		if (!practices.isEmpty()) {
			towerWrapper.setPracticeList(practices);
		}

		if (!contactDetails.isEmpty()) {
			towerWrapper.setContactDetailsList(contactDetails);
		}

		model.addAttribute("towerWrapper", towerWrapper);
		return "/towers/view";
	}

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

}