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

import com.maxic.towers.web.dao.Country;
import com.maxic.towers.web.dao.Diocese;
import com.maxic.towers.web.dao.Peal;
import com.maxic.towers.web.dao.Practice;
import com.maxic.towers.web.dao.Tower;
import com.maxic.towers.web.dao.TowerDescriptor;
import com.maxic.towers.web.processing.Parser;
import com.maxic.towers.web.service.CountryService;
import com.maxic.towers.web.service.DioceseService;
import com.maxic.towers.web.service.PealService;
import com.maxic.towers.web.service.PracticeService;
import com.maxic.towers.web.service.TowerService;

@Controller
public class AdminController {

	/*
	 * Defining and autowiring services
	 */
	private TowerService towerService;
	private PealService pealService;
	private CountryService countryService;
	private DioceseService dioceseService;
	private PracticeService practiceService;

	@Autowired
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}

	@Autowired
	public void setPealService(PealService pealService) {
		this.pealService = pealService;
	}

	@Autowired
	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	@Autowired
	public void setDioceseService(DioceseService dioceseService) {
		this.dioceseService = dioceseService;
	}
	
	@Autowired
	public void setPracticeService(PracticeService practiceService) {
		this.practiceService = practiceService;
	}

	/*
	 * 
	 * NON-CRUD RELATED ADMIN MAPPINGS
	 */

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

	/*
	 * 
	 * ADMIN TOWERS REQUEST MAPPINGS
	 * 
	 */

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

	/**
	 * Creates a new instance of tower and returns to the page
	 * 
	 * @return /admin/towers/add view
	 */
	@RequestMapping(value = "/admin/towers/add")
	public String addTower(Model model) {
		model.addAttribute("tower", new Tower());
		Map<String, String> countryMap = countryService.getCountryMap();
		model.addAttribute("countries", countryMap);

		Map<String, String> dioceseMap = dioceseService.getDioceseMap();
		model.addAttribute("dioceses", dioceseMap);
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
		for (Tower tower : towerList) {
			if (!countryService.countryExists(tower.getCountry().getIsoCode())) {
				Country country = new Country(tower.getCountry().getIsoCode(),
						tower.getCountry().getName());
				countryService.addCountry(country);
			}
			if (!dioceseService
					.dioceseExists(tower.getDiocese().getDioceseId())) {
				Diocese diocese = new Diocese(
						tower.getDiocese().getDioceseId(), tower.getDiocese()
								.getName());
				dioceseService.addDiocese(diocese);
			}	
			towerService.addTower(tower);
		}

		List<Tower> insertedTowerList = towerService.getTowers();
		
		for (Tower tower : insertedTowerList) {
			if (!practiceService.practicesExist(tower.getTowerId())) {
				Practice practice = new Practice(tower.getTowerId(), 1, null, null, null, false);
				System.out.println("Adding practice for tower: " + tower.getTowerId());
				practiceService.addPractice(practice);
			}
			
		}
		
		
		return "redirect:/admin/towers";
	}

	/*
	 * 
	 * ADMIN PEALS REQUEST MAPPINGS
	 * 
	 */

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
		Map<Integer, String> hm = towerService.getTowerDescriptorMap();
		model.addAttribute("towers", hm);
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
		pealService.editPeal(peal);
		return ("redirect:/admin/peals");
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

	/*
	 * 
	 * ADMIN COUNTRIES REQUEST MAPPINGS
	 * 
	 */

	@RequestMapping("/admin/countries")
	public String showCountries(Model model,
			@ModelAttribute("message") String message) {

		List<Country> countries = countryService.getCountries();
		model.addAttribute("countries", countries);
		model.addAttribute("message", message);
		return "/admin/countries";
	}

	@RequestMapping("/admin/countries/add")
	public String showAddCountry(Model model) {
		Country country = new Country();

		model.addAttribute("country", country);
		return "/admin/countries/addcountry";
	}

	@RequestMapping(value = "/admin/countries/doadd", method = RequestMethod.POST)
	public String doAddCountry(Model model, @Valid Country country,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/admin/countries/addcountry";
		}
		countryService.addCountry(country);
		redirectAttributes.addFlashAttribute("message",
				"Country successfully added!");
		return ("redirect:/admin/countries");
	}

	@RequestMapping(value = "/admin/countries/edit", method = RequestMethod.GET)
	public String showEditCountry(Model model, @RequestParam("c") String c) {
		Country country = countryService.getCountry(c);
		model.addAttribute("country", country);

		return "/admin/countries/editcountry";
	}

	@RequestMapping(value = "/admin/countries/doedit", method = RequestMethod.POST)
	public String doEditCountry(Model model, Country country,
			BindingResult result, @RequestParam("c") String c,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("c", c);
		if (result.hasErrors()) {
			for (Object error : result.getAllErrors()) {
				System.out.println(error);
			}
			redirectAttributes.addAttribute("c", c);
			return ("redirect:/admin/countries/edit");
		}
		System.out.println("Trying to edit country");
		countryService.editCountry(country);
		return ("redirect:/admin/countries");
	}

	@RequestMapping(value = "/admin/countries/dodelete", method = RequestMethod.GET)
	public String deleteCountry(Model model, @RequestParam("c") String c,
			RedirectAttributes redirectAttributes) {

		countryService.deleteCountry(c);

		redirectAttributes.addFlashAttribute("message",
				"Country successfully deleted.");
		return "redirect:/admin/countries";

	}
	
	/*
	 * 
	 * ADMIN DIOCESE REQUEST MAPPINGS
	 * 
	 */

	@RequestMapping("/admin/dioceses")
	public String showDioceses(Model model,
			@ModelAttribute("message") String message) {

		List<Diocese> dioceses = dioceseService.getDioceses();
		model.addAttribute("dioceses", dioceses);
		model.addAttribute("message", message);
		return "/admin/dioceses";
	}

	@RequestMapping("/admin/dioceses/add")
	public String showAddDiocese(Model model) {
		Diocese diocese = new Diocese();

		model.addAttribute("diocese", diocese);
		return "/admin/dioceses/adddiocese";
	}

	@RequestMapping(value = "/admin/dioceses/doadd", method = RequestMethod.POST)
	public String doAddDiocese(Model model, @Valid Diocese diocese,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/admin/dioceses/adddiocese";
		}
		dioceseService.addDiocese(diocese);
		redirectAttributes.addFlashAttribute("message",
				"Diocese successfully added!");
		return ("redirect:/admin/dioceses");
	}

	@RequestMapping(value = "/admin/dioceses/edit", method = RequestMethod.GET)
	public String showEditDiocese(Model model, @RequestParam("d") String d) {
		Diocese diocese = dioceseService.getDiocese(d);
		model.addAttribute("diocese", diocese);

		return "/admin/dioceses/editdiocese";
	}

	@RequestMapping(value = "/admin/dioceses/doedit", method = RequestMethod.POST)
	public String doEditDiocese(Model model, Diocese diocese,
			BindingResult result, @RequestParam("d") String d,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("d", d);
		if (result.hasErrors()) {
			for (Object error : result.getAllErrors()) {
				System.out.println(error);
			}
			redirectAttributes.addAttribute("d", d);
			return ("redirect:/admin/dioceses/edit");
		}
		System.out.println("Trying to edit diocese");
		dioceseService.editDiocese(diocese);
		return ("redirect:/admin/dioceses");
	}

	@RequestMapping(value = "/admin/dioceses/dodelete", method = RequestMethod.GET)
	public String deleteDiocese(Model model, @RequestParam("d") String d,
			RedirectAttributes redirectAttributes) {

		dioceseService.deleteDiocese(d);

		redirectAttributes.addFlashAttribute("message",
				"Diocese successfully deleted.");
		return "redirect:/admin/dioceses";

	}

}
