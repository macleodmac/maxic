package com.maxic.towers.web.controllers;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maxic.towers.web.model.ContactDetails;
import com.maxic.towers.web.model.Practice;
import com.maxic.towers.web.model.TowerWrapper;
import com.maxic.towers.web.service.ContactDetailsService;
import com.maxic.towers.web.service.CountryService;
import com.maxic.towers.web.service.DioceseService;
import com.maxic.towers.web.service.PracticeService;
import com.maxic.towers.web.service.TowerService;
import com.maxic.towers.web.service.UserService;

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
	private UserService userService;

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

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * 
	 * TOWER CAPTAIN TOWERS REQUEST MAPPINGS
	 */

	@RequestMapping(value = "/captain/view")
	public String captainViewTower(Model model, Principal principal,
			RedirectAttributes redirectAttributes) {
		String name = principal.getName();
		int towerId = userService.getUser(name).getTowerCaptain();
		redirectAttributes.addAttribute("t", towerId);
		return "redirect:/towers/view";
	}

	/**
	 * Fetches tower id from principal details, fetches tower using service,
	 * returns view for editing tower
	 * 
	 * @param principal
	 *            user principal object
	 * @return /captain/edit view if successful
	 */
	@RequestMapping(value = "/captain/edit")
	public String captainEditTower(Model model, Principal principal) {

		String name = principal.getName();
		int id = userService.getUser(name).getTowerCaptain();

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
		return "/captain/edit";
	}

	/**
	 * Fetches tower id from posted object, checks for errors, if none saves the
	 * tower to the database
	 * 
	 * @param towerWrapper
	 *            towerWrapper object
	 * @return redirect:/captain/edit view if successful
	 */
	@RequestMapping(value = "/captain/doedit", method = RequestMethod.POST)
	public String doEdit(Model model, TowerWrapper towerWrapper,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			redirectAttributes
					.addFlashAttribute("dangerMessage",
							"An unexpected validation error occured and the tower could not be edited.");
			return ("redirect:/captain/edit");
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
					redirectAttributes
							.addFlashAttribute(
									"dangerMessage",
									"The phone number  "
											+ contactDetails.getDetail()
											+ " could not be verified, please make sure you follow the rules.");
					return "redirect:/captain/edit";
				}
			} else if (contactType.equalsIgnoreCase("email")) {
				pattern = Pattern.compile(
						"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
						Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes
							.addFlashAttribute(
									"dangerMessage",
									"The email address  "
											+ contactDetails.getDetail()
											+ " could not be verified, please make sure you follow the rules.");
					return "redirect:/captain/edit";
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
					redirectAttributes
							.addFlashAttribute(
									"dangerMessage",
									"The website address  "
											+ contactDetails.getDetail()
											+ " could not be verified, please make sure you follow the rules.");
					return "redirect:/captain/edit";
				}
			} else if (contactType.equalsIgnoreCase("twitter")) {
				pattern = Pattern.compile("^[A-z0-9]*$",
						Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes
							.addFlashAttribute(
									"dangerMessage",
									"The Twitter username  "
											+ contactDetails.getDetail()
											+ " could not be verified, please make sure you follow the rules.");
					return "redirect:/captain/edit";
				}
			} else if (contactType.equalsIgnoreCase("facebook")) {
				pattern = Pattern.compile("^[A-z0-9/]*$",
						Pattern.CASE_INSENSITIVE);
				matcher = pattern.matcher(contactDetails.getDetail());
				if (matcher.matches()) {
					contactDetailsService.editContactDetails(contactDetails);
				} else {
					redirectAttributes
							.addFlashAttribute(
									"dangerMessage",
									"The Facebook group/page URL "
											+ contactDetails.getDetail()
											+ " could not be verified, please make sure you follow the rules.");
					return "redirect:/captain/edit";
				}
			}
		}

		for (Practice practice : towerWrapper.getPracticeList()) {
			practiceService.editPractice(practice);
		}

		redirectAttributes.addFlashAttribute("message",
				"Tower successfully edited!");
		return ("redirect:/captain/edit");
	}

	/**
	 * Creates a new instance of a practice and returns to the tower edit page
	 * 
	 * @return /captain/towers/add view
	 */
	@RequestMapping(value = "/captain/addpractice")
	public String addPractice(Model model, Principal principal) {

		String name = principal.getName();
		int id = userService.getUser(name).getTowerCaptain();

		Set<String> days = new LinkedHashSet<String>();
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");
		days.add("Thursday");
		days.add("Friday");
		days.add("Saturday");
		days.add("Sunday");
		model.addAttribute("days", days);

		Practice practice = new Practice(id, 0, null, null, "00:00:00", null,
				true);
		model.addAttribute("practice", practice);

		Map<Boolean, String> booleanMap = new LinkedHashMap<Boolean, String>();
		booleanMap.put(true, "Yes");
		booleanMap.put(false, "No");
		model.addAttribute("yesno", booleanMap);

		return "/captain/addpractice";
	}

	/**
	 * Fetches practice from page using POST, checks for errors, adds practice to
	 * database using service
	 * 
	 * @param practice
	 *            practice object fetched from form submission
	 * @return redirect:/captain/edit view if successful
	 */
	@RequestMapping(value = "/captain/doaddpractice", method = RequestMethod.POST)
	public String doAddPractice(Model model, @Valid Practice practice,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("practice", practice);
			redirectAttributes
					.addFlashAttribute(
							"dangerMessage",
							"There was an issue validating the input. Make sure no fields were left blank and the help below was followed.");
			return "redirect:/captain/addpractice";

		}
		practiceService.addPractice(practice);

		redirectAttributes.addFlashAttribute("message",
				"Practice successfully added!");
		return "redirect:/captain/edit";
	}

	/**
	 * Fetches practice from page using id, deletes practice from database 
	 * 
	 * @param pr int practice id
	 * @return redirect:/captain/edit view if successful
	 */
	@RequestMapping(value = "/captain/deletepractice", method = RequestMethod.GET)
	public String doDeletePractice(Model model, @RequestParam("pr") int pr,
			Principal principal, RedirectAttributes redirectAttributes) {

		String name = principal.getName();
		int id = userService.getUser(name).getTowerCaptain();

		if (practiceService.getPractice(pr).getTowerId() == id) {
			practiceService.deletePractice(pr);
			redirectAttributes.addFlashAttribute("message",
					"Practice successfully deleted!");
			return ("redirect:/captain/edit");
		} else {
			return "redirect:/403";
		}
	}

	/**
	 * Creates a new instance of a contact detail and returns to the tower edit page
	 * 
	 * @return /captain/towers/add view
	 */
	@RequestMapping(value = "/captain/addcontact")
	public String addContact(Model model, Principal principal) {

		String name = principal.getName();
		int id = userService.getUser(name).getTowerCaptain();

		ContactDetails contact = new ContactDetails(id, 0, null, null);
		model.addAttribute("contact", contact);

		Set<String> contactTypes = new LinkedHashSet<String>();
		contactTypes.add("Phone");
		contactTypes.add("Email");
		contactTypes.add("Website");
		contactTypes.add("Twitter");
		contactTypes.add("Facebook");
		model.addAttribute("contactTypes", contactTypes);

		return "/captain/addcontact";
	}

	/**
	 * Fetches contact from page using POST, checks for errors, adds contact to
	 * database using service
	 * 
	 * @param contactDetails
	 *            contactDetails object fetched from form submission
	 * @return redirect:/captain/edit view if successful
	 */
	@RequestMapping(value = "/captain/doaddcontact", method = RequestMethod.POST)
	public String doAddContact(Model model,
			@Valid ContactDetails contactDetails, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/captain/addcontact";

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
				redirectAttributes
						.addFlashAttribute(
								"dangerMessage",
								"The phone number  "
										+ contactDetails.getDetail()
										+ " could not be verified, please make sure you follow the rules below.");
				return "redirect:/captain/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("email")) {
			pattern = Pattern.compile(
					"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
					Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes
						.addFlashAttribute(
								"dangerMessage",
								"The email address  "
										+ contactDetails.getDetail()
										+ " could not be verified, please make sure you follow the rules below.");
				return "redirect:/captain/addcontact";
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
				redirectAttributes
						.addFlashAttribute(
								"dangerMessage",
								"The website address  "
										+ contactDetails.getDetail()
										+ " could not be verified, please make sure you follow the rules below.");
				return "redirect:/captain/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("twitter")) {
			pattern = Pattern.compile("^[A-z0-9]*$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes
						.addFlashAttribute(
								"dangerMessage",
								"The Twitter username  "
										+ contactDetails.getDetail()
										+ " could not be verified, please make sure you follow the rules below.");
				return "redirect:/captain/addcontact";
			}
		} else if (contactType.equalsIgnoreCase("facebook")) {
			pattern = Pattern.compile("^[A-z0-9/]*$", Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(contactDetails.getDetail());
			if (matcher.matches()) {
				contactDetailsService.addContactDetails(contactDetails);
			} else {
				redirectAttributes
						.addFlashAttribute(
								"dangerMessage",
								"The Facebook group/page URL  "
										+ contactDetails.getDetail()
										+ " could not be verified, please make sure you follow the rules below.");
				return "redirect:/captain/addcontact";
			}
		}

		redirectAttributes.addFlashAttribute("message",
				"Contact Details successfully added!");
		redirectAttributes.addAttribute("t", contactDetails.getTowerId());
		return "redirect:/captain/edit";
	}

	/**
	 * Fetches contact from page using id, deletes contact from database 
	 * 
	 * @param c int contact id
	 * @return redirect:/captain/edit view if successful
	 */
	@RequestMapping(value = "/captain/deletecontact", method = RequestMethod.GET)
	public String doDeleteContact(Model model, @RequestParam("c") int c,
			RedirectAttributes redirectAttributes, Principal principal) {

		String name = principal.getName();
		int id = userService.getUser(name).getTowerCaptain();

		if (contactDetailsService.getContactDetail(c).getTowerId() == id) {
			contactDetailsService.deleteContactDetail(c);
			redirectAttributes.addFlashAttribute("message",
					"Contact Details successfully deleted!");
			return ("redirect:/captain/edit");
		} else {
			return "redirect:/403";
		}

	}

}
