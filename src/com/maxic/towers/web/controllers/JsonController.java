package com.maxic.towers.web.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maxic.towers.web.dao.JsonObject;
import com.maxic.towers.web.model.Tower;
import com.maxic.towers.web.model.TowerDescriptor;
import com.maxic.towers.web.model.TowerShort;
import com.maxic.towers.web.model.User;
import com.maxic.towers.web.service.ContactDetailsService;
import com.maxic.towers.web.service.CountryService;
import com.maxic.towers.web.service.DioceseService;
import com.maxic.towers.web.service.PealService;
import com.maxic.towers.web.service.PracticeService;
import com.maxic.towers.web.service.TowerService;
import com.maxic.towers.web.service.TowerVisitService;
import com.maxic.towers.web.service.UserService;
import com.maxic.towers.web.service.VerificationService;

@Controller
public class JsonController {

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
	private TowerVisitService towerVisitService;

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
	
	@Autowired
	public void setTowerVisitService(TowerVisitService towerVisitService) {
		this.towerVisitService = towerVisitService;
	}

	@RequestMapping(value = "/admin/json/towers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String towerJson(
			HttpServletRequest request) throws IOException {

		int pageNo = 0;
		int pageLength = 10;
		String searchTerm = "";
		
		if (request.getParameter("iDisplayStart") != null) {
			pageNo = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
		}

		searchTerm = request.getParameter("sSearch");
		pageLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		List<Tower> towerList;
		int towerCount = towerService.getNumberOfTowers();
		int towerListCount;

		if (searchTerm != null && !searchTerm.equals("")) {
			towerList = towerService.getPaginatedTowersByTerm(pageLength, (pageNo - 1) * 10, searchTerm);
			towerListCount = towerService.getNumberOfTowersBySearchTerm(searchTerm);
		} else {
			towerList = towerService.getPaginatedTowers(pageLength, (pageNo - 1) * 10);
			towerListCount = towerCount;
		}

		JsonObject<Tower> towerJson = new JsonObject<Tower>();
		
		towerJson.setiTotalDisplayRecords(towerListCount);
		towerJson.setiTotalRecords(towerCount);
		towerJson.setAaData(towerList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String returnJson = gson.toJson(towerJson);

		return returnJson;

	}
	
	@RequestMapping(value = "/admin/json/users", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String userJson(HttpServletRequest request) throws IOException {

		int pageNo = 0;
		int pageLength = 10;
		String searchTerm = "";
		if (request.getParameter("iDisplayStart") != null) {
			pageNo = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
		}
		if (request.getParameter("sSearch") != null) {
			searchTerm = request.getParameter("sSearch");
		}
		
		if (request.getParameter("iDisplayLength") != null) {
			pageLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		}
		List <User> userList;
		
		int userCount = userService.getNumberofUsers();
		int userListCount;

		if (!searchTerm.isEmpty() && searchTerm != null && !searchTerm.equals("")) {
			userList = userService.getPaginatedUsersByTerm(pageLength, (pageNo - 1) * 10, searchTerm);
			userListCount = userService.getNumberOfUsersBySearchTerm(searchTerm);
		} else {
			userList = userService.getPaginatedUsers(pageLength, (pageNo - 1) * 10);
			userListCount = userCount;
		}

		JsonObject<User> userJson = new JsonObject<User>();
		System.out.println("UserListCount " + userListCount);
		userJson.setiTotalDisplayRecords(userListCount);
		userJson.setiTotalRecords(userCount);
		System.out.println("UserCount " + userCount);
		userJson.setAaData(userList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String returnJson = gson.toJson(userJson);

		return returnJson;

	}
	
	@RequestMapping(value = "/json/towers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String userTowerJson(
			HttpServletRequest request) throws IOException {

		int pageNo = 0;
		int pageLength = 10;
		String searchTerm = "";
		
		if (request.getParameter("iDisplayStart") != null) {
			pageNo = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
		}

		searchTerm = request.getParameter("sSearch");
		pageLength = Integer.valueOf(request.getParameter("iDisplayLength"));
		List<TowerDescriptor> towerList;
		int towerCount = towerService.getNumberOfTowers();
		int towerListCount;

		if (searchTerm != null && !searchTerm.equals("")) {
			towerList = towerService.getPaginatedTowerDescriptorsBySearchTerm(pageLength, (pageNo - 1) * 10, searchTerm);
			towerListCount = towerService.getNumberOfTowersBySearchTerm(searchTerm);
		} else {
			towerList = towerService.getPaginatedTowerDescriptors(pageLength, (pageNo - 1) * 10);
			towerListCount = towerCount;
		}

		JsonObject<TowerDescriptor> towerJson = new JsonObject<TowerDescriptor>();
		
		towerJson.setiTotalDisplayRecords(towerListCount);
		towerJson.setiTotalRecords(towerCount);
		towerJson.setAaData(towerList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String returnJson = gson.toJson(towerJson);

		return returnJson;

	}
	
	@RequestMapping(value = "/json/towermap", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String towerMapJson(
			HttpServletRequest request, Principal principal) throws IOException {

		boolean ringable = true;
		boolean groundFloorRing = true;
		boolean userVisited = false;
		int minimumBells = 0;
		int maximumBells = 99;
		int results = 10;
		String diocese = ""; 
		
		if (request.getParameter("ringable") != null) {
			if (request.getParameter("ringable").equals("1")) {
				ringable = true;
			} else {
				ringable = false;
			}
		}
		
		if (request.getParameter("groundFloorRing") != null) {
			if (request.getParameter("groundFloorRing").equals("1")) {
				groundFloorRing = true;
			} else {
				groundFloorRing = false;
			}
		}
		
		if (request.getParameter("userVisited") != null) {
			if (request.getParameter("userVisited").equals("1")) {
				userVisited = true;
			} else {
				userVisited = false;
			}
		}
		
		if (request.getParameter("minBells") != null) {
			minimumBells = Integer.valueOf(request.getParameter("minBells"));
		}
		
		if (request.getParameter("maxBells") != null) {
			maximumBells = Integer.valueOf(request.getParameter("maxBells"));
		}

		List<Tower> towers = towerService.getMapTowers(diocese, minimumBells, maximumBells, ringable, groundFloorRing, results);

		JsonObject<Tower> towerJson = new JsonObject<Tower>();
		
		towerJson.setiTotalDisplayRecords(5);
		towerJson.setiTotalRecords(6);
		towerJson.setAaData(towers);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String returnJson = gson.toJson(towerJson);

		return returnJson;

	}
	
	
	@RequestMapping(value = "/towers/gettowers", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getTowers() {

		List<TowerShort> towers = null;

		towers = towerService.getTowersShort();

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("towers", towers);
		data.put("number", towers.size());

		return data;
	}

	
}
