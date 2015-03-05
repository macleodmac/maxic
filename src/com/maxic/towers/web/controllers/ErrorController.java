package com.maxic.towers.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping(value = "/400")
	public String error400(Model model) {
		model.addAttribute("id", "400");
		model.addAttribute("message", "Bad Request, please try again!");
		return "/error";
	}
	
	@RequestMapping(value = "/401")
	public String error401(Model model) {
		model.addAttribute("id", "401");
		model.addAttribute("message", "You're not authorised to do that, sorry.");
		return "/error";
	}

	@RequestMapping(value = "/403")
	public String error403(Model model) {
		model.addAttribute("id", "403");
		model.addAttribute("message", "That's forbidden, sorry.");
		return "/error";
	}

	@RequestMapping(value = "/404")
	public String error404(Model model) {
		model.addAttribute("id", "404");
		model.addAttribute("message", "Oops! That page wasn't found, sorry.");
		return "/error";
	}

	@RequestMapping(value = "/500")
	public String error500(Model model) {
		model.addAttribute("id", "500");
		model.addAttribute("message", "Looks like something went wrong at our end, please try again.");
		return "/error";
	}

}