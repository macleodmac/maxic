package com.maxic.towers.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.maxic.towers.web.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maxic.towers.web.dao.TowerJsonObject;
import com.maxic.towers.web.model.ContactDetails;
import com.maxic.towers.web.model.Country;
import com.maxic.towers.web.model.Diocese;
import com.maxic.towers.web.model.Peal;
import com.maxic.towers.web.model.Practice;
import com.maxic.towers.web.model.Tower;
import com.maxic.towers.web.model.TowerDescriptor;
import com.maxic.towers.web.model.TowerWrapper;
import com.maxic.towers.web.model.User;
import com.maxic.towers.web.model.VerificationToken;
import com.maxic.towers.web.processing.Parser;
import com.maxic.towers.web.service.ContactDetailsService;
import com.maxic.towers.web.service.CountryService;
import com.maxic.towers.web.service.DioceseService;
import com.maxic.towers.web.service.PealService;
import com.maxic.towers.web.service.PracticeService;
import com.maxic.towers.web.service.TowerService;
import com.maxic.towers.web.service.UserService;
import com.maxic.towers.web.service.VerificationService;

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
	private ContactDetailsService contactDetailsService;
	private UserService userService;
	private VerificationService verificationService;

	@Autowired
	private MailSender mailSender;
	
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

	@Autowired
	public void setContactDetailsService(
			ContactDetailsService contactDetailsService) {
		this.contactDetailsService = contactDetailsService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setVerificationService(VerificationService verificationService) {
		this.verificationService = verificationService;
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
	 */

	/**
	 * Fetches a list of all towers from the tower service and returns to the
	 * page
	 * 
	 * @param message
	 *            arbitrary information message to display on the page
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

		TowerWrapper towerWrapper = new TowerWrapper();
		towerWrapper.setTower(towerService.getTower(id));

		for (Practice practice : practiceService.getPractices(id)) {
			towerWrapper.addPractice(practice);
		}

		for (ContactDetails contactDetails : contactDetailsService
				.getContactDetails(id)) {
			towerWrapper.addContactDetails(contactDetails);
		}

		Set<String> days = new LinkedHashSet<String>();
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");
		days.add("Thursday");
		days.add("Friday");
		days.add("Saturday");
		days.add("Sunday");
		model.addAttribute("days", days);

		Map<Boolean, String> booleanMap = new LinkedHashMap<Boolean, String>();
		booleanMap.put(true, "Yes");
		booleanMap.put(false, "No");
		model.addAttribute("yesno", booleanMap);

		model.addAttribute("towerWrapper", towerWrapper);

		Map<String, String> countryMap = countryService.getCountryMap();
		model.addAttribute("countries", countryMap);

		Map<String, String> dioceseMap = dioceseService.getDioceseMap();
		model.addAttribute("dioceses", dioceseMap);
		return "/admin/towers/edittower";
	}

	@RequestMapping(value = "/admin/towers/doedit", method = RequestMethod.POST)
	public String doEdit(Model model, TowerWrapper towerWrapper,
			BindingResult result, @RequestParam("t") String t,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("t", t);
			redirectAttributes.addAttribute("t", t);
			return ("redirect:/admin/towers/edit");
		}

		towerService.editTower(towerWrapper.getTower());

		for (ContactDetails contactDetails : towerWrapper
				.getContactDetailsList()) {
			System.out.println("Here at " + contactDetails);
			contactDetailsService.editContactDetails(contactDetails);
		}

		for (Practice practice : towerWrapper.getPracticeList()) {
			System.out.println("Here at " + practice);
			practiceService.editPractice(practice);
		}

		redirectAttributes.addFlashAttribute("message",
				"Tower successfully edited!");
		redirectAttributes.addAttribute("t", t);
		return ("redirect:/admin/towers/edit");
	}

	/**
	 * Creates a new instance of a practice and returns to the tower edit page
	 * 
	 * @return /admin/towers/add view
	 */
	@RequestMapping(value = "/admin/towers/addpractice")
	public String addPractice(Model model, @RequestParam("t") int t) {

		Set<String> days = new LinkedHashSet<String>();
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");
		days.add("Thursday");
		days.add("Friday");
		days.add("Saturday");
		days.add("Sunday");
		model.addAttribute("days", days);

		Practice practice = new Practice(t, 0, null, null, "00:00:00", null,
				true);
		model.addAttribute("practice", practice);

		return "/admin/towers/addpractice";
	}

	/**
	 * Fetches tower from page using POST, checks for errors, adds tower to
	 * database using service
	 * 
	 * @param tower
	 *            tower object fetched from form submission
	 * @return redirect:/admin/towers/towers view if successful
	 */
	@RequestMapping(value = "/admin/towers/doaddpractice", method = RequestMethod.POST)
	public String doAddPractice(Model model, @Valid Practice practice,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/admin/towers/addpractice";

		}
		practiceService.addPractice(practice);

		redirectAttributes.addFlashAttribute("message",
				"Practice successfully added!");
		redirectAttributes.addAttribute("t", practice.getTowerId());
		return "redirect:/admin/towers/edit";
	}

	@RequestMapping(value = "/admin/towers/dodeletepractice", method = RequestMethod.GET)
	public String doDeletePractice(Model model, @RequestParam("pr") int pr,
			@RequestParam("t") int t, RedirectAttributes redirectAttributes) {

		practiceService.deletePractice(pr);

		redirectAttributes.addFlashAttribute("message",
				"Practice successfully deleted!");
		redirectAttributes.addAttribute("t", t);
		return "redirect:/admin/towers/edit";
	}

	/**
	 * Creates a new instance of a practice and returns to the tower edit page
	 * 
	 * @return /admin/towers/add view
	 */
	@RequestMapping(value = "/admin/towers/addcontact")
	public String addContact(Model model, @RequestParam("t") int t) {

		ContactDetails contact = new ContactDetails(t, 0, null, null);
		model.addAttribute("contact", contact);

		return "/admin/towers/addcontact";
	}

	/**
	 * Fetches tower from page using POST, checks for errors, adds tower to
	 * database using service
	 * 
	 * @param tower
	 *            tower object fetched from form submission
	 * @return redirect:/admin/towers/towers view if successful
	 */
	@RequestMapping(value = "/admin/towers/doaddcontact", method = RequestMethod.POST)
	public String doAddContact(Model model,
			@Valid ContactDetails contactDetails, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/admin/towers/addcontact";

		}
		contactDetailsService.addContactDetails(contactDetails);

		redirectAttributes.addFlashAttribute("message",
				"Contact Details successfully added!");
		redirectAttributes.addAttribute("t", contactDetails.getTowerId());
		return "redirect:/admin/towers/edit";
	}

	@RequestMapping(value = "/admin/towers/dodeletecontact", method = RequestMethod.GET)
	public String doDeleteContact(Model model, @RequestParam("c") int c,
			@RequestParam("t") int t, RedirectAttributes redirectAttributes) {

		contactDetailsService.deleteContactDetails(c);

		redirectAttributes.addFlashAttribute("message",
				"Contact Details successfully deleted!");
		redirectAttributes.addAttribute("t", t);
		return "redirect:/admin/towers/edit";
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
			System.out.println(tower);
		}

		towerService.addTowers(towerList);

		List<Tower> insertedTowerList = towerService.getTowers();

		for (Tower tower : insertedTowerList) {
			if (!practiceService.practicesExist(tower.getTowerId())) {
				Practice practice = new Practice(tower.getTowerId(), 1, null,
						null, null, null, false);
				System.out.println("Adding practice for tower: "
						+ tower.getTowerId());
				practiceService.addPractice(practice);
			}

		}

		return "redirect:/admin/towers";
	}

	@RequestMapping(value = "/admin/towers/towerpagination", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String springPaginationDataTables(
			HttpServletRequest request) throws IOException {

		int pageNo = 0;

		if (request.getParameter("iDisplayStart") != null) {
			pageNo = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
		}

		String searchTerm = request.getParameter("sSearch");
		int pageLength = Integer
				.valueOf(request.getParameter("iDisplayLength"));
		List<Tower> towerList;
		int towerCount = towerService.getNumberOfTowers();
		int towerListCount;

		if (searchTerm != null && !searchTerm.equals("")) {
			towerList = towerService.getPaginatedTowersByTerm(pageLength,
					(pageNo - 1) * 10, searchTerm);
			towerListCount = towerService
					.getNumberOfTowersBySearchTerm(searchTerm);
		} else {
			towerList = towerService.getPaginatedTowers(pageLength,
					(pageNo - 1) * 10);
			towerListCount = towerCount;
		}

		TowerJsonObject towerJson = new TowerJsonObject();
		// Set Total display record

		towerJson.setiTotalDisplayRecords(towerListCount);
		// Set Total record
		towerJson.setiTotalRecords(towerCount);
		towerJson.setAaData(towerList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonForReturn = gson.toJson(towerJson);

		return jsonForReturn;

	}

	/*
	 * 
	 * ADMIN PEALS REQUEST MAPPINGS
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
			model.addAttribute("message",
					"Peal not added. An error occured in the input");
			Map<Integer, String> hm = towerService.getTowerDescriptorMap();
			model.addAttribute("towers", hm);
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
		Map<Integer, String> hm = towerService.getTowerDescriptorMap();
		model.addAttribute("towers", hm);
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

	@RequestMapping(value = "/admin/users")
	public String showUsers(Model model) {

		List<User> users = userService.getUsers();

		model.addAttribute("users", users);

		return "/admin/users";

	}
	
	@RequestMapping(value = "/admin/users/add")
	public String addUser(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "/admin/users/add";

	}

	@RequestMapping(value = "/admin/users/reset", method = RequestMethod.GET)
	public String resetUser(Model model,
			@RequestParam("u") String email,
			RedirectAttributes redirectAttributes, HttpServletRequest httpRequestServlet) {

		User tempUser = new User();
		tempUser.setEmail(email);
		VerificationToken verificationToken = null;
		User user = null;
		
		if (userService.exists(tempUser)) {
			user = userService.getUser(email);
			String token = UUID.randomUUID().toString();
			verificationToken = new VerificationToken(token, user);
			verificationService.addToken(verificationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			String contextPath = httpRequestServlet.getContextPath();
			StringBuffer sb = new StringBuffer();
			sb.append("An administrator has submitted a request to reset your password. \n\n");
			sb.append("Please visit the below URL to reset your password:\n\n");
			sb.append("http://localhost:8080" + contextPath + "/reset?token=" + verificationToken.getToken() + "\n\n");
			sb.append("Please note: this link will expire in two days, and can only be used once.\n\n");
			sb.append("Many Thanks,\n\n");
			sb.append("The TowerFinder team");
			
			mailMessage.setTo(user.getEmail());
			mailMessage.setFrom("noreply@towerfinder.com");
			mailMessage.setSubject("TowerFinder - Reset Password");
			mailMessage.setText(sb.toString());
			mailSender.send(mailMessage);
			redirectAttributes.addFlashAttribute("message", "User " + user.getEmail() + " has been emailed a request to reset their password.");
			return "redirect:/admin/users";
		} else {
			redirectAttributes.addFlashAttribute("message", "An unforseen error occured, that user was not found.");
			return "redirect:/admin/users";
		}
	}

}
