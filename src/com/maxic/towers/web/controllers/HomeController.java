package com.maxic.towers.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	
	@RequestMapping("/")
	public String showHome(HttpSession session) {
		
		session.setAttribute("name","boris");
		return "home";
	}
}
