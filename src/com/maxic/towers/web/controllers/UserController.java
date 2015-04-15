package com.maxic.towers.web.controllers;

import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maxic.towers.web.model.EditUser;
import com.maxic.towers.web.model.TowerVisit;
import com.maxic.towers.web.model.User;
import com.maxic.towers.web.model.VerificationToken;
import com.maxic.towers.web.service.TowerService;
import com.maxic.towers.web.service.TowerVisitService;
import com.maxic.towers.web.service.UserService;
import com.maxic.towers.web.service.VerificationService;

@Controller
public class UserController {

	private UserService userService;
	private VerificationService verificationService;
	private TowerVisitService towerVisitService;
	private TowerService towerService;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setTowerVisitService(TowerVisitService towerVisitService) {
		this.towerVisitService = towerVisitService;
	}

	@Autowired
	public void setVerificationService(VerificationService verificationService) {
		this.verificationService = verificationService;
	}

	@Autowired
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}

	@RequestMapping(value = "/newaccount")
	public String createUser(Model model) {

		User user = new User();

		model.addAttribute("user", user);

		return "/newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createUser(Model model, @Valid User user,
			BindingResult result, WebRequest request, Errors errors,
			HttpServletRequest httpRequestServlet,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors() || userService.exists(user)) {
			redirectAttributes
					.addFlashAttribute(
							"message",
							"That email address already exists in the database. Please login, or try a different email address.");
			return "redirect:/newaccount";
		}

		user.setRole("ROLE_USER");
		VerificationToken verificationToken = null;
		try {

			String token = UUID.randomUUID().toString();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			verificationToken = new VerificationToken(token, user);
			verificationService.addToken(verificationToken);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			String contextPath = httpRequestServlet.getContextPath();
			StringBuffer sb = new StringBuffer();
			sb.append("Thank you for creating a TowerFinder account.\n\n");
			sb.append("Please visit the below URL to verify your account:\n\n");
			sb.append("http://localhost:8080" + contextPath + "/verify?token="
					+ verificationToken.getToken() + "\n\n");
			sb.append("Please note: this link will expire in two days, and can only be used once.\n\n");
			sb.append("Many Thanks,\n\n");
			sb.append("The TowerFinder team");

			mailMessage.setTo(user.getEmail());
			mailMessage.setFrom("noreply@towerfinder.com");
			mailMessage.setSubject("TowerFinder - Account Created");
			mailMessage.setText(sb.toString());
			mailSender.send(mailMessage);
		} catch (DuplicateKeyException e) {
			result.rejectValue(
					"email",
					"DuplicateKey.user.email",
					"That email address already exists in the database. Please login, or try a different email address.");
			return "/newaccount";
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute(
					"message",
					"An unexpected error occurred. Please try again later, or if it continues, contact the admin team.");
			user.setPassword(null);
			return "/newaccount";
		}

		return "redirect:/accountcreated";
	}

	@RequestMapping(value = "/accountcreated")
	public String showAccountCreated() {
		return "/accountcreated";
	}

	@RequestMapping(value = "/login")
	public String showLogin(Model model) {
		return "/login";
	}

	@RequestMapping(value = "/loggedout")
	public String showLoggedOut(Model model) {
		return "/loggedout";
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String verifyUser(Model model, @RequestParam("token") String token,
			RedirectAttributes redirectAttributes) {

		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		VerificationToken userToken = null;
		User user = null;

		if (verificationService.validToken(verificationToken)) {
			try {
				userToken = verificationService.getVerificationToken(token);
				user = userToken.getUser();

				return "/verified";
			} catch (Exception e) {
				return "/badtoken";
			} finally {
				try {
					userService.enable(user);
					verificationService.verify(verificationToken);
				} catch (Exception e) {
					userService.disable(user);
					return "/badtoken";
				}
			}
		} else {
			return "/badtoken";
		}

	}

	@RequestMapping(value = "/verified")
	public String showVerified(Model model) {
		return "/verified";
	}

	@RequestMapping(value = "/forgotpassword")
	public String showForgotPassword(Model model) {
		model.addAttribute("email", new String());
		return "/forgotpassword";
	}

	@RequestMapping(value = "/recoverpassword", method = RequestMethod.POST)
	public String recoverPassword(Model model, String email,
			HttpServletRequest httpRequestServlet,
			RedirectAttributes redirectAttributes) {

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
			sb.append("Thank you for your request to reset your TowerFinder password. \n\n");
			sb.append("Please visit the below URL to reset your password:\n\n");
			sb.append("http://localhost:8080" + contextPath + "/reset?token="
					+ verificationToken.getToken() + "\n\n");
			sb.append("Please note: this link will expire in two days, and can only be used once.\n\n");
			sb.append("Many Thanks,\n\n");
			sb.append("The TowerFinder team");

			mailMessage.setTo(user.getEmail());
			mailMessage.setFrom("abc@gmail.com");
			mailMessage.setSubject("TowerFinder - Reset Password");
			mailMessage.setText(sb.toString());
			mailSender.send(mailMessage);
			return "redirect:/resetpassword";
		} else {
			redirectAttributes.addFlashAttribute("message",
					"That email address was not found in the database, sorry.");
			return "redirect:/forgotpassword";
		}
	}

	@RequestMapping(value = "/resetpassword")
	public String showResetPassword(Model model) {
		return "/resetpassword";
	}

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String resetPassword(Model model,
			@RequestParam("token") String token,
			RedirectAttributes redirectAttributes) {

		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		VerificationToken userToken = null;
		User user = null;

		if (verificationService.validToken(verificationToken)) {
			userToken = verificationService.getVerificationToken(token);
			user = userToken.getUser();
			System.out.println(user);
			user.setPassword(null);
			model.addAttribute(user);
			verificationService.verify(verificationToken);
			return "/changepassword";
		} else {
			return "/badtoken";
		}
	}

	@RequestMapping(value = "/doreset", method = RequestMethod.POST)
	public String doResetPassword(Model model, @Valid User user,
			BindingResult result, WebRequest request, Errors errors,
			HttpServletRequest httpRequestServlet) {
		System.out.println(user);
		if (result.hasErrors()) {

			for (ObjectError error : result.getAllErrors()) {
				System.out.println("ERROR:" + error.toString());
			}
			return "/changepassword";
		} else {
			user.setEnabled(true);
			System.out.println(user);
			userService.update(user);
			return "/resetsuccess";

		}
	}

	@RequestMapping(value = "/account")
	public String showAccount(Model model, Principal principal) {
		String email = principal.getName();

		model.addAttribute("email", email);
		return "/account";
	}

	@RequestMapping(value = "/account/edit")
	public String showEditAccount(Model model, Principal principal) {
		String email = principal.getName();
		User currentUser = userService.getUser(email);
		System.out.println(currentUser);
		currentUser.setPassword(null);
		EditUser editUser = new EditUser();
		editUser.setUser(currentUser);

		model.addAttribute("editUser", editUser);
		return "/account/edit";
	}

	@RequestMapping(value = "/account/doedit")
	public String showMyVisits(Model model, @Valid EditUser editUser,
			BindingResult result, WebRequest request, Errors errors,
			HttpServletRequest httpRequestServlet,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("ERROR:" + error.toString());
			}
			redirectAttributes
					.addFlashAttribute("dangerMessage",
							"There was an error in the input and the account details could not be edited.");
			return "redirect:/account/edit";

		} else {

			User user = editUser.getUser();

			System.out.println("Current User: " + user);
			User checkingUser = userService.getUser(user.getEmail());

			if (passwordEncoder.matches(user.getPassword(),
					checkingUser.getPassword())) {

				if (!editUser.getNewEmail().isEmpty()) {

					SimpleMailMessage mailMessage = new SimpleMailMessage();
					SimpleMailMessage newEmailMessage = new SimpleMailMessage();
					StringBuffer sb = new StringBuffer();
					sb.append("This is email notification to confirm your email address has been changed: \n\n");
					sb.append("Old email address: " + user.getEmail() + "\n");
					sb.append("New email address: " + editUser.getNewEmail()
							+ "\n\n");
					sb.append("If you did not wish to make this change please contact an administrator. \n\n");
					sb.append("Many Thanks,\n\n");
					sb.append("The TowerFinder team");

					mailMessage.setTo(user.getEmail());
					mailMessage.setFrom("noreply@towerfinder.com");
					mailMessage.setSubject("TowerFinder - Email Changed");
					mailMessage.setText(sb.toString());

					newEmailMessage.setTo(editUser.getNewEmail());
					newEmailMessage.setFrom("noreply@towerfinder.com");
					newEmailMessage.setSubject("TowerFinder - Email Changed");
					newEmailMessage.setText(sb.toString());

					user.setEmail(editUser.getNewEmail());

					mailSender.send(mailMessage);
					mailSender.send(newEmailMessage);

				}

				if (!editUser.getNewPassword().isEmpty()) {
					user.setPassword(editUser.getNewPassword());
				}

				if (!editUser.getNewName().isEmpty()) {
					user.setName(editUser.getNewName());
				}
				
				

				System.out.println("Saving User: " + user);
				userService.update(user);

				if (!editUser.getNewEmail().isEmpty()) {
					redirectAttributes.addFlashAttribute("successMessage",
							"Your email address has been changed, you can now login using "
									+ editUser.getNewEmail());
					return "redirect:/j_spring_security_logout";
				}
				redirectAttributes.addFlashAttribute("successMessage",
						"Your details have been successfully changed.");
				return "redirect:/account/edit";
			} else {
				redirectAttributes
						.addFlashAttribute("dangerMessage",
								"Your current password could not be verified, please try again.");
				return "redirect:/account/edit";
			}

		}

	}

	@RequestMapping(value = "/account/visits")
	public String showMyVisits(Model model, Principal principal) {
		String email = principal.getName();
		User currentUser = userService.getUser(email);
		currentUser.setPassword(null);
		int id = currentUser.getId();
		List<TowerVisit> visits = towerVisitService.getVisitsByUserId(id);
		model.addAttribute("visits", visits);
		return "/account/visits";
	}

	@RequestMapping(value = "/account/visits/add", method = RequestMethod.GET)
	public String addVisit(Model model, Principal principal,
			@RequestParam("t") int t) {
		String email = principal.getName();
		User currentUser = userService.getUser(email);
		currentUser.setPassword(null);
		int id = currentUser.getId();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		Date date = new Date(cal.getTime().getTime());

		TowerVisit visit = new TowerVisit(t, 0, id, date, false, false, false,
				null);

		model.addAttribute("visit", visit);

		Map<Integer, String> hm = towerService.getTowerDescriptorMap();
		model.addAttribute("towers", hm);

		Map<Boolean, String> booleanMap = new LinkedHashMap<Boolean, String>();
		booleanMap.put(true, "Yes");
		booleanMap.put(false, "No");
		model.addAttribute("yesno", booleanMap);

		return "/account/visits/add";
	}

	@RequestMapping(value = "/account/visits/doadd", method = RequestMethod.POST)
	public String doAddVisit(Model model, @Valid TowerVisit towerVisit,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("dangerMessage",
					"Visit not added. An error occured in the input");
			redirectAttributes.addAttribute("t", towerVisit.getTowerId());
			return "redirect:/account/visits/add";
		} else {
			towerVisitService.addTowerVisit(towerVisit);
			redirectAttributes.addFlashAttribute("message",
					"Tower visit successfully added!");
			return "redirect:/account/visits";
		}
	}

}