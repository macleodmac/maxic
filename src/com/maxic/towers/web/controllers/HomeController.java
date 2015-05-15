package com.maxic.towers.web.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping("/")
	public String showHome() {
		logger.info("Showing homepage.");
		return "home";
	}

	@RequestMapping(value = "/about")
	public String showAbout() {
		return "/about";
	}

	@RequestMapping(value = "/help")
	public String showHelp() {
		return "/help";
	}
}
