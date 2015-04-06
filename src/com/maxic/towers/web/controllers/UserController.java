package com.maxic.towers.web.controllers;

import java.security.Principal;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
import com.maxic.towers.web.model.User;
import com.maxic.towers.web.model.VerificationToken;
import com.maxic.towers.web.service.UserService;
import com.maxic.towers.web.service.VerificationService;

@Controller
public class UserController {

	private UserService userService;
	private VerificationService verificationService;

	@Autowired
	ApplicationEventPublisher eventPublisher;

	@Autowired
	private MailSender mailSender;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setVerificationService(VerificationService verificationService) {
		this.verificationService = verificationService;
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

		if (result.hasErrors()) {
			return "/newaccount";
		}
		if (userService.exists(user)) {
			model.addAttribute(
					"message",
					"That email address already exists in the database. Please login, or try a different email address.");
			return "/newaccount";
		}

		user.setRole("ROLE_USER");
		VerificationToken verificationToken = null;
		try {

			String token = UUID.randomUUID().toString();
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
			userService.addUser(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue(
					"email",
					"DuplicateKey.user.email",
					"That email address already exists in the database. Please login, or try a different email address.");
			return "/newaccount";
		} catch (Exception e) {
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

		if (result.hasErrors()) {

			for (ObjectError error : result.getAllErrors()) {
				System.out.println("ERROR:" + error.toString());
			}
			return "/changepassword";
		}
		userService.updatePassword(user);
		return "/resetsuccess";
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
		
		EditUser editUser = new EditUser();
		editUser.setEnabled(currentUser.isEnabled());
		editUser.setOldEmail(currentUser.getEmail());
		editUser.setRole(currentUser.getRole());
		
		model.addAttribute("user", editUser);
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
			User user = new User();
			user.setEnabled(editUser.isEnabled());
			user.setRole(editUser.getRole());
			
			if (editUser.getNewEmail().isEmpty()) {
				user.setEmail(editUser.getOldEmail());
			} else {
				user.setEmail(editUser.getNewEmail());
			}
			
			if (editUser.getNewPassword().isEmpty()) {
				user.setPassword(editUser.getCurrentPassword());
			} else {
				user.setPassword(editUser.getNewPassword());
			}
			
			
			redirectAttributes
					.addFlashAttribute("successMessage",
							"Your new email now has to be verified, please check your inbox for details.");
			return "redirect:/account/edit";
		}

	}

	@RequestMapping(value = "/account/visits")
	public String showMyVisits(Model model, Principal principal) {
		String email = principal.getName();
		User currentUser = userService.getUser(email);
		currentUser.setPassword(null);
		User user = new User();
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("user", user);
		return "/account/visits";
	}

}