package com.maxic.towers.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
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
import com.maxic.towers.web.model.Practice;
import com.maxic.towers.web.model.Tower;
import com.maxic.towers.web.model.TowerDescriptor;
import com.maxic.towers.web.model.TowerWrapper;
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
public class TowerCaptainController {

	/*
	 * Defining and autowiring services
	 */
	private TowerService towerService;
	private CountryService countryService;
	private DioceseService dioceseService;
	private PracticeService practiceService;
	private ContactDetailsService contactDetailsService;

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
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

	/*
	 * 
	 * TOWER CAPTAIN TOWERS REQUEST MAPPINGS
	 * 
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

		Set<String> contactTypes = new LinkedHashSet<String>();
		contactTypes.add("Phone");
		contactTypes.add("Email");
		contactTypes.add("Website");
		contactTypes.add("Twitter");
		contactTypes.add("Facebook");
		model.addAttribute("contactTypes", contactTypes);
		
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
			Pattern pattern;
			Matcher matcher;
			String contactType = contactDetails.getType();
			
			if (contactType.equalsIgnoreCase("phone")) {
				pattern = Pattern.compile("^[0-9]{10,15}$");
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes.addAttribute("t", contactDetails.getTowerId());
					redirectAttributes.addFlashAttribute("dangerMessage", "The phone number  " + contactDetails.getDetail() + " could not be verified, please make sure you follow the rules.");
					return "redirect:/admin/towers/edit";
				}
			} else if (contactType.equalsIgnoreCase("email")) {
				pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes.addAttribute("t", contactDetails.getTowerId());
					redirectAttributes.addFlashAttribute("dangerMessage", "The email address  " + contactDetails.getDetail() + " could not be verified, please make sure you follow the rules.");
					return "redirect:/admin/towers/edit";
				}
			} else if (contactType.equalsIgnoreCase("website")) {
				pattern = Pattern.compile("^(?:(?:https?|ftp):\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))(?::\\d{2,5})?(?:\\/\\S*)?$", Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes.addAttribute("t", contactDetails.getTowerId());
					redirectAttributes.addFlashAttribute("dangerMessage", "The website address  " + contactDetails.getDetail() + " could not be verified, please make sure you follow the rules.");
					return "redirect:/admin/towers/edit";
				}
			} else if (contactType.equalsIgnoreCase("twitter")) {
				pattern = Pattern.compile("^[A-z0-9]*$", Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes.addAttribute("t", contactDetails.getTowerId());
					redirectAttributes.addFlashAttribute("dangerMessage", "The Twitter username  " + contactDetails.getDetail() + " could not be verified, please make sure you follow the rules.");
					return "redirect:/admin/towers/edit";
				}
			} else if (contactType.equalsIgnoreCase("facebook")) {
				pattern = Pattern.compile("^[A-z0-9/]*$", Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes.addAttribute("t", contactDetails.getTowerId());
					redirectAttributes.addFlashAttribute("dangerMessage", "The Facebook group/page URL " + contactDetails.getDetail() + " could not be verified, please make sure you follow the rules.");
					return "redirect:/admin/towers/edit";
				}
			}
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

		Set<String> contactTypes = new LinkedHashSet<String>();
		contactTypes.add("Phone");
		contactTypes.add("Email");
		contactTypes.add("Website");
		contactTypes.add("Twitter");
		contactTypes.add("Facebook");
		model.addAttribute("contactTypes", contactTypes);
		
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
		Pattern pattern;
		Matcher matcher;
		String contactType = contactDetails.getType();
		
		if (contactType.equalsIgnoreCase("phone")) {
			pattern = Pattern.compile("^[0-9]{10,15}$");
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes.addAttribute("t", contactDetails.getTowerId());
				redirectAttributes.addFlashAttribute("dangerMessage", "The phone number  " + contactDetails.getDetail() + " could not be verified, please make sure you follow the rules below.");
				return "redirect:/admin/towers/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("email")) {
			pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes.addAttribute("t", contactDetails.getTowerId());
				redirectAttributes.addFlashAttribute("dangerMessage", "The email address  " + contactDetails.getDetail() + " could not be verified, please make sure you follow the rules below.");
				return "redirect:/admin/towers/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("website")) {
			pattern = Pattern.compile("^(?:(?:https?|ftp):\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))(?::\\d{2,5})?(?:\\/\\S*)?$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes.addAttribute("t", contactDetails.getTowerId());
				redirectAttributes.addFlashAttribute("dangerMessage", "The website address  " + contactDetails.getDetail() + " could not be verified, please make sure you follow the rules below.");
				return "redirect:/admin/towers/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("twitter")) {
			pattern = Pattern.compile("^[A-z0-9]*$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes.addAttribute("t", contactDetails.getTowerId());
				redirectAttributes.addFlashAttribute("dangerMessage", "The Twitter username  " + contactDetails.getDetail() + " could not be verified, please make sure you follow the rules below.");
				return "redirect:/admin/towers/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("facebook")) {
			pattern = Pattern.compile("^[A-z0-9/]*$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes.addAttribute("t", contactDetails.getTowerId());
				redirectAttributes.addFlashAttribute("dangerMessage", "The Facebook group/page URL  " + contactDetails.getDetail() + " could not be verified, please make sure you follow the rules below.");
				return "redirect:/admin/towers/addcontact";
			}
		}
		
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

}
