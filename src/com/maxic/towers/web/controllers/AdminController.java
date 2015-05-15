package com.maxic.towers.web.controllers;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@Value("${mailSender.username}")
	private String fromEmail;

	@Value("${mailSender.webUrl}")
	private String websiteUrl;

	@Value("${general.siteName}")
	private String siteName;

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

	
	/*
	 * 
	 * ADMIN TOWERS REQUEST MAPPINGS
	 * 
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
		Map<Boolean, String> booleanMap = new LinkedHashMap<Boolean, String>();
		booleanMap.put(true, "Yes");
		booleanMap.put(false, "No");
		model.addAttribute("yesno", booleanMap);
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
	 * @param t string identifying tower by towerId
	 * @return redirect:/admin/towers view if successful
	 */
	@RequestMapping(value = "/admin/towers/dodelete", method = RequestMethod.GET)
	public String deleteTower(Model model, @RequestParam("t") String t,
			RedirectAttributes redirectAttributes) {
		int id = Integer.parseInt(t);
		boolean success = true;

		try {
			towerService.deleteTower(id);
		} catch (Exception e) {
			success = false;
		}

		if (success) {
			redirectAttributes.addFlashAttribute("message",
					"Tower successfully deleted.");
			return "redirect:/admin/towers";
		} else {
			model.addAttribute("t", t);
			return ("redirect:/admin/towers/edit");
		}
	}

	/**
	 * Fetches tower id from page, fetches tower using service, returns view
	 * for editing tower
	 * 
	 * @param t string identifying tower by towerId
	 * @return /admin/towers/edittower view if successful
	 */
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

	/**
	 * Fetches tower id from page, saves edited tower using service, returns view
	 * for editing tower
	 * 
	 * @param t string identifying tower by towerId
	 * @return redirect:/admin/towers/edit view if successful
	 */
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
					redirectAttributes.addAttribute("t",
							contactDetails.getTowerId());
					redirectAttributes
							.addFlashAttribute(
									"dangerMessage",
									"The phone number  "
											+ contactDetails.getDetail()
											+ " could not be verified, please make sure you follow the rules.");
					return "redirect:/admin/towers/edit";
				}
			} else if (contactType.equalsIgnoreCase("email")) {
				pattern = Pattern.compile(
						"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
						Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes.addAttribute("t",
							contactDetails.getTowerId());
					redirectAttributes
							.addFlashAttribute(
									"dangerMessage",
									"The email address  "
											+ contactDetails.getDetail()
											+ " could not be verified, please make sure you follow the rules.");
					return "redirect:/admin/towers/edit";
				}
			} else if (contactType.equalsIgnoreCase("website")) {
				pattern = Pattern
						.compile(
								"^(?:(?:https?|ftp):\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))(?::\\d{2,5})?(?:\\/\\S*)?$",
								Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes.addAttribute("t",
							contactDetails.getTowerId());
					redirectAttributes
							.addFlashAttribute(
									"dangerMessage",
									"The website address  "
											+ contactDetails.getDetail()
											+ " could not be verified, please make sure you follow the rules.");
					return "redirect:/admin/towers/edit";
				}
			} else if (contactType.equalsIgnoreCase("twitter")) {
				pattern = Pattern.compile("^[A-z0-9]*$",
						Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes.addAttribute("t",
							contactDetails.getTowerId());
					redirectAttributes
							.addFlashAttribute(
									"dangerMessage",
									"The Twitter username  "
											+ contactDetails.getDetail()
											+ " could not be verified, please make sure you follow the rules.");
					return "redirect:/admin/towers/edit";
				}
			} else if (contactType.equalsIgnoreCase("facebook")) {
				pattern = Pattern.compile("^[A-z0-9/]*$",
						Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes.addAttribute("t",
							contactDetails.getTowerId());
					redirectAttributes
							.addFlashAttribute(
									"dangerMessage",
									"The Facebook group/page URL "
											+ contactDetails.getDetail()
											+ " could not be verified, please make sure you follow the rules.");
					return "redirect:/admin/towers/edit";
				}
			}
		}

		for (Practice practice : towerWrapper.getPracticeList()) {
			practiceService.editPractice(practice);
		}

		redirectAttributes.addFlashAttribute("message",
				"Tower successfully edited!");
		redirectAttributes.addAttribute("t", t);
		return ("redirect:/admin/towers/edit");
	}

	/**
	 * Creates a new instance of a practice and returns to the add practice view
	 * 
	 * @return /admin/towers/addpractice view
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

		Map<Boolean, String> booleanMap = new LinkedHashMap<Boolean, String>();
		booleanMap.put(true, "Yes");
		booleanMap.put(false, "No");
		model.addAttribute("yesno", booleanMap);

		Practice practice = new Practice(t, 0, null, null, "00:00:00", null,
				true);
		model.addAttribute("practice", practice);

		return "/admin/towers/addpractice";
	}

	/**
	 * Fetches practice details from page using POST, checks for errors, adds practice to
	 * database using service
	 * 
	 * @param practice
	 *            practice object fetched from form submission
	 * @return redirect:/admin/towers/edit view if successful
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
	
	/**
	 * Fetches tower, practice id from page, deletes practice using service, returns view
	 * for editing tower
	 * 
	 * @param t string identifying tower by towerId
	 * @param pr string identifying practice by practiceId
	 * @return redirect:/admin/towers/edit view if successful
	 */
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
	 * Creates a new instance of a contact detail and returns to the add practice view
	 * 
	 * @return /admin/towers/addcontact view
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
	 * Fetches contact details from page using POST, checks for errors, adds contact details to
	 * database using service
	 * 
	 * @param contactDetails
	 *            practice object fetched from form submission
	 * @return redirect:/admin/towers/edit view if successful
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
				redirectAttributes.addAttribute("t",
						contactDetails.getTowerId());
				redirectAttributes
						.addFlashAttribute(
								"dangerMessage",
								"The phone number  "
										+ contactDetails.getDetail()
										+ " could not be verified, please make sure you follow the rules below.");
				return "redirect:/admin/towers/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("email")) {
			pattern = Pattern.compile(
					"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
					Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes.addAttribute("t",
						contactDetails.getTowerId());
				redirectAttributes
						.addFlashAttribute(
								"dangerMessage",
								"The email address  "
										+ contactDetails.getDetail()
										+ " could not be verified, please make sure you follow the rules below.");
				return "redirect:/admin/towers/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("website")) {
			pattern = Pattern
					.compile(
							"^(?:(?:https?|ftp):\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})))(?::\\d{2,5})?(?:\\/\\S*)?$",
							Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes.addAttribute("t",
						contactDetails.getTowerId());
				redirectAttributes
						.addFlashAttribute(
								"dangerMessage",
								"The website address  "
										+ contactDetails.getDetail()
										+ " could not be verified, please make sure you follow the rules below.");
				return "redirect:/admin/towers/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("twitter")) {
			pattern = Pattern.compile("^[A-z0-9]*$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes.addAttribute("t",
						contactDetails.getTowerId());
				redirectAttributes
						.addFlashAttribute(
								"dangerMessage",
								"The Twitter username  "
										+ contactDetails.getDetail()
										+ " could not be verified, please make sure you follow the rules below.");
				return "redirect:/admin/towers/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("facebook")) {
			pattern = Pattern.compile("^[A-z0-9/]*$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes.addAttribute("t",
						contactDetails.getTowerId());
				redirectAttributes
						.addFlashAttribute(
								"dangerMessage",
								"The Facebook group/page URL  "
										+ contactDetails.getDetail()
										+ " could not be verified, please make sure you follow the rules below.");
				return "redirect:/admin/towers/addcontact";
			}
		}

		redirectAttributes.addFlashAttribute("message",
				"Contact Details successfully added!");
		redirectAttributes.addAttribute("t", contactDetails.getTowerId());
		return "redirect:/admin/towers/edit";
	}

	/**
	 * Fetches tower, contact id from page, deletes contact using service, returns view
	 * for editing tower
	 * 
	 * @param t string identifying tower by towerId
	 * @param c string identifying contact by contactId
	 * @return redirect:/admin/towers/edit view if successful
	 */
	@RequestMapping(value = "/admin/towers/dodeletecontact", method = RequestMethod.GET)
	public String doDeleteContact(Model model, @RequestParam("c") int c,
			@RequestParam("t") int t, RedirectAttributes redirectAttributes) {

		contactDetailsService.deleteContactDetail(c);

		redirectAttributes.addFlashAttribute("message",
				"Contact Details successfully deleted!");
		redirectAttributes.addAttribute("t", t);
		return "redirect:/admin/towers/edit";
	}

	/*
	 * 
	 * ADMIN PEALS REQUEST MAPPINGS
	 * 
	 */

	@RequestMapping("/admin/peals")
	public String showPeals(Model model,
			@ModelAttribute("message") String message) {
		model.addAttribute("message", message);
		Map<Integer, String> hm = towerService.getTowerDescriptorMap();
		model.addAttribute("towers", hm);
		return "/admin/peals";
	}

	/**
	 * Creates a new instance of a peal and returns to the add peal view
	 * 
	 * @return /admin/towers/addpeal view
	 */
	@RequestMapping("/admin/peals/add")
	public String showAddPeal(Model model) {
		Peal peal = new Peal();
		Map<Integer, String> hm = towerService.getTowerDescriptorMap();
		model.addAttribute("towers", hm);
		model.addAttribute("peal", peal);
		return "/admin/peals/addpeal";
	}

	/**
	 * Fetches peal details from page using POST, checks for errors, adds peal to
	 * database using service
	 * 
	 * @param peal
	 *            peal object fetched from form submission
	 * @return redirect:/admin/peals view if successful
	 */
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

	/**
	 * Fetches peal id from page, fetches peal using service, returns view
	 * for editing peal
	 * 
	 * @param p string identifying peal by pealId
	 * @return /admin/peals/editpeal view if successful
	 */
	@RequestMapping(value = "/admin/peals/edit", method = RequestMethod.GET)
	public String showEditPeal(Model model, @RequestParam("p") String p) {
		int pealId = Integer.parseInt(p);
		Peal peal = pealService.getPeal(pealId);
		Map<Integer, String> hm = towerService.getTowerDescriptorMap();
		model.addAttribute("towers", hm);
		model.addAttribute("peal", peal);

		return "/admin/peals/editpeal";
	}

	/**
	 * Fetches peal id from page, saves edited peal using service, returns view
	 * for peal list
	 * 
	 * @param p string identifying peal by pealId
	 * @return redirect:/admin/peals view if successful
	 */
	@RequestMapping(value = "/admin/peals/doedit", method = RequestMethod.POST)
	public String doEditPeal(Model model, Peal peal, BindingResult result,
			@RequestParam("p") String p, RedirectAttributes redirectAttributes) {
		model.addAttribute("p", p);
		if (result.hasErrors()) {
			redirectAttributes.addAttribute("p", p);
			return ("redirect:/admin/peals/edit");
		}
		pealService.editPeal(peal);
		return ("redirect:/admin/peals");
	}

	/**
	 * Fetches peal id from page, deletes peal using service, returns view
	 * for peal list
	 * 
	 * @param p string identifying peal by pealId
	 * @return redirect:/admin/peals view if successful
	 */
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

	/**
	 * Creates a new instance of a country and returns to the add country view
	 * 
	 * @return /admin/countries/addcountry view
	 */
	@RequestMapping("/admin/countries/add")
	public String showAddCountry(Model model) {
		Country country = new Country();

		model.addAttribute("country", country);
		return "/admin/countries/addcountry";
	}

	/**
	 * Fetches country details from page using POST, checks for errors, adds country to
	 * database using service
	 * 
	 * @param country
	 *            country object fetched from form submission
	 * @return redirect:/admin/countries view if successful
	 */
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

	/**
	 * Fetches country id from page, fetches country using service, returns view
	 * for editing country
	 * 
	 * @param c string identifying country by countryId
	 * @return /admin/editcountry view if successful
	 */
	@RequestMapping(value = "/admin/countries/edit", method = RequestMethod.GET)
	public String showEditCountry(Model model, @RequestParam("c") String c) {
		Country country = countryService.getCountry(c);
		model.addAttribute("country", country);

		return "/admin/countries/editcountry";
	}

	/**
	 * Fetches country id from page, saves edited country using service, returns view
	 * for country list
	 * 
	 * @param c string identifying country by countryId
	 * @return redirect:/admin/countries view if successful
	 */
	@RequestMapping(value = "/admin/countries/doedit", method = RequestMethod.POST)
	public String doEditCountry(Model model, Country country,
			BindingResult result, @RequestParam("c") String c,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("c", c);
		if (result.hasErrors()) {
			redirectAttributes.addAttribute("c", c);
			return ("redirect:/admin/countries/edit");
		}
		countryService.editCountry(country);
		return ("redirect:/admin/countries");
	}

	/**
	 * Fetches country id from page, deletes country using service, returns view
	 * for country list
	 * 
	 * @param c string identifying country by countryId
	 * @return redirect:/admin/countries view if successful
	 */
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

	/**
	 * Creates a new instance of a diocese and returns to the add diocese view
	 * 
	 * @return /admin/dioceses/adddiocese view
	 */
	@RequestMapping("/admin/dioceses/add")
	public String showAddDiocese(Model model) {
		Diocese diocese = new Diocese();

		model.addAttribute("diocese", diocese);
		return "/admin/dioceses/adddiocese";
	}

	
	/**
	 * Fetches diocese details from page using POST, checks for errors, adds diocese to
	 * database using service
	 * 
	 * @param diocese
	 *            diocese object fetched from form submission
	 * @return redirect:/admin/dioceses view if successful
	 */
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

	/**
	 * Fetches diocese id from page, fetches diocese using service, returns view
	 * for editing diocese
	 * 
	 * @param d string identifying diocese by dioceseId
	 * @return /admin/towers/editdiocese view if successful
	 */
	@RequestMapping(value = "/admin/dioceses/edit", method = RequestMethod.GET)
	public String showEditDiocese(Model model, @RequestParam("d") String d) {
		Diocese diocese = dioceseService.getDiocese(d);
		model.addAttribute("diocese", diocese);

		return "/admin/dioceses/editdiocese";
	}

	/**
	 * Fetches diocese id from page, saves edited diocese using service, returns view
	 * for diocese list
	 * 
	 * @param d string identifying diocese by dioceseId
	 * @return redirect:/admin/dioceses view if successful
	 */
	@RequestMapping(value = "/admin/dioceses/doedit", method = RequestMethod.POST)
	public String doEditDiocese(Model model, Diocese diocese,
			BindingResult result, @RequestParam("d") String d,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("d", d);
		if (result.hasErrors()) {
			redirectAttributes.addAttribute("d", d);
			return ("redirect:/admin/dioceses/edit");
		}
		dioceseService.editDiocese(diocese);
		return ("redirect:/admin/dioceses");
	}

	/**
	 * Fetches diocese id from page, deletes diocese using service, returns view
	 * for diocese list
	 * 
	 * @param d string identifying diocese by dioceseId
	 * @return redirect:/admin/dioceses view if successful
	 */
	@RequestMapping(value = "/admin/dioceses/dodelete", method = RequestMethod.GET)
	public String deleteDiocese(Model model, @RequestParam("d") String d,
			RedirectAttributes redirectAttributes) {

		dioceseService.deleteDiocese(d);

		redirectAttributes.addFlashAttribute("message",
				"Diocese successfully deleted.");
		return "redirect:/admin/dioceses";

	}


	/*
	 * 
	 * ADMIN USER REQUEST MAPPINGS
	 * 
	 */
	
	@RequestMapping(value = "/admin/users")
	public String showUsers(Model model) {

		List<User> users = userService.getUsers();

		model.addAttribute("users", users);

		return "/admin/users";

	}

	/**
	 * Creates a new instance of a user and returns to the add user view
	 * 
	 * @return /admin/users/add view
	 */
	@RequestMapping(value = "/admin/users/add")
	public String addUser(Model model) {

		User user = new User();
		model.addAttribute("user", user);

		return "/admin/users/add";

	}
	
	/**
	 * Fetches user details from page using POST, checks for errors, adds user to
	 * database using service
	 * 
	 * @param user
	 *            user object fetched from form submission
	 * @return redirect:/admin/users view if successful
	 */
	@RequestMapping(value = "/admin/users/doadd", method = RequestMethod.POST)
	public String doAddUser(Model model, @Valid User user,
			BindingResult result, RedirectAttributes redirectAttributes,
			HttpServletRequest httpRequestServlet) {

		VerificationToken verificationToken = null;
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("dangerMessage",
					"That user could not be added, please try again.");
			return "redirect:/admin/users/add";
		} else if (userService.exists(user)) {
			redirectAttributes
					.addFlashAttribute("dangerMessage",
							"That email already exists in the database, please try again.");
			return "redirect:/admin/users/add";
		} else {
			user.setPassword(UUID.randomUUID().toString());
			String token = UUID.randomUUID().toString();
			verificationToken = new VerificationToken(token, user);
			verificationService.addToken(verificationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			String contextPath = httpRequestServlet.getContextPath();
			StringBuffer sb = new StringBuffer();
			sb.append("An administrator has created you an account on "
					+ siteName + " \n\n");
			sb.append("Please visit the below URL to set your password:\n\n");
			sb.append(websiteUrl + contextPath + "/reset?token="
					+ verificationToken.getToken() + "\n\n");
			sb.append("Please note: this link will expire in two days, and can only be used once.\n\n");
			sb.append("Many Thanks,\n\n");
			sb.append("The " + siteName + " team");

			mailMessage.setTo(user.getEmail());
			mailMessage.setFrom(fromEmail);
			mailMessage.setSubject(siteName + " - Account Created");
			mailMessage.setText(sb.toString());
			mailSender.send(mailMessage);
			redirectAttributes
					.addFlashAttribute(
							"message",
							"User "
									+ user.getEmail()
									+ " has been created and emailed a request to set their password.");
			return "redirect:/admin/users";
		}
	}

	/**
	 * Fetches user id from page, fetches user using service, returns view
	 * for editing user
	 * 
	 * @param u string identifying user by userId
	 * @return /admin/towers/editdiocese view if successful
	 */
	@RequestMapping(value = "/admin/users/edit", method = RequestMethod.GET)
	public String editUser(Model model, @RequestParam("u") int u) {

		User user = userService.getUser(u);
		model.addAttribute("user", user);

		Map<Integer, String> hm = towerService.getTowerDescriptorMap();
		model.addAttribute("towers", hm);

		Map<Boolean, String> booleanMap = new LinkedHashMap<Boolean, String>();
		booleanMap.put(true, "Yes");
		booleanMap.put(false, "No");
		model.addAttribute("yesno", booleanMap);

		return "/admin/users/edit";

	}

	/**
	 * Fetches user id from page, saves edited user using service, returns view
	 * for user list
	 * 
	 * @param u string identifying user by userId
	 * @return redirect:/admin/users view if successful
	 */
	@RequestMapping(value = "/admin/users/doedit", method = RequestMethod.POST)
	public String editUser(Model model, @Valid User user, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("dangerMessage",
					"The user could not be edited, please try again.");
			redirectAttributes.addAttribute("u", user.getId());
			return "redirect:/admin/users/edit";
		} else if (userService.existsById(user)) {
			userService.updateNoPassEncode(user);
			redirectAttributes.addFlashAttribute("message",
					"User " + user.getEmail() + " successfully edited!");
			return "redirect:/admin/users";
		} else {
			redirectAttributes.addFlashAttribute("dangerMessage",
					"An unexpected error occurred.");
			redirectAttributes.addAttribute("u", user.getId());
			return "redirect:/admin/users/edit";
		}

	}

	/**
	 * Fetches user id from page, creates new token, emails user to reset password, returns view
	 * for user list with message
	 * 
	 * @param u string identifying user by userId
	 * @return redirect:/admin/users view if successful
	 */
	@RequestMapping(value = "/admin/users/reset", method = RequestMethod.GET)
	public String resetUser(Model model, @RequestParam("u") int id,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpRequestServlet) {

		User tempUser = new User();
		tempUser.setId(id);
		VerificationToken verificationToken = null;
		User user = null;

		if (userService.existsById(tempUser)) {
			user = userService.getUser(id);
			String token = UUID.randomUUID().toString();
			verificationToken = new VerificationToken(token, user);
			verificationService.addToken(verificationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			String contextPath = httpRequestServlet.getContextPath();
			StringBuffer sb = new StringBuffer();
			sb.append("An administrator has submitted a request to reset your password. \n\n");
			sb.append("Please visit the below URL to reset your password:\n\n");
			sb.append(websiteUrl + contextPath + "/reset?token="
					+ verificationToken.getToken() + "\n\n");
			sb.append("Please note: this link will expire in two days, and can only be used once.\n\n");
			sb.append("Many Thanks,\n\n");
			sb.append("The " + siteName + " team");

			mailMessage.setTo(user.getEmail());
			mailMessage.setFrom(fromEmail);
			mailMessage.setSubject(siteName + " - Reset Password");
			mailMessage.setText(sb.toString());
			mailSender.send(mailMessage);
			redirectAttributes
					.addFlashAttribute(
							"message",
							"User "
									+ user.getEmail()
									+ " has been emailed a request to reset their password.");
			return "redirect:/admin/users";
		} else {
			redirectAttributes.addFlashAttribute("message",
					"An unforseen error occured, that user was not found.");
			return "redirect:/admin/users";
		}
	}

}
