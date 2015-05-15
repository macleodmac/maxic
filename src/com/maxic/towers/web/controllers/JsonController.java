package com.maxic.towers.web.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.maxic.towers.web.model.Peal;
import com.maxic.towers.web.model.Tower;
import com.maxic.towers.web.model.TowerDescriptor;
import com.maxic.towers.web.model.TowerShort;
import com.maxic.towers.web.model.TowerVisit;
import com.maxic.towers.web.model.User;
import com.maxic.towers.web.service.PealService;
import com.maxic.towers.web.service.TowerService;
import com.maxic.towers.web.service.TowerVisitService;
import com.maxic.towers.web.service.UserService;

@Controller
public class JsonController {

	/*
	 * Defining and autowiring services
	 */
	private TowerService towerService;
	private PealService pealService;
	private UserService userService;
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
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setTowerVisitService(TowerVisitService towerVisitService) {
		this.towerVisitService = towerVisitService;
	}

	/**
	 * Fetches towers based on request attributes
	 * 
	 * @param request
	 *            get request from page
	 * @return json object
	 */
	@RequestMapping(value = "/admin/json/towers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String towerJson(HttpServletRequest request)
			throws IOException {

		int pageNo = 0;
		int pageLength = 10;
		String searchTerm = "";
		// Map<String, String[]> params = request.getParameterMap();
		// for (Map.Entry<String, String[]> entry : params.entrySet()) {
		// System.out.println(entry.getKey() + "/" +
		// Arrays.toString(entry.getValue()));
		// }

		if (request.getParameter("iDisplayStart") != null) {
			pageNo = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
		}

		if (request.getParameter("start") != null) {
			pageNo = (Integer.valueOf(request.getParameter("start")) / 10) + 1;
		}

		if (request.getParameter("iDisplayLength") != null) {
			pageLength = Integer
					.valueOf(request.getParameter("iDisplayLength"));
		}
		if (request.getParameter("length") != null) {
			pageLength = Integer.valueOf(request.getParameter("length"));
		}
		searchTerm = request.getParameter("sSearch");

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

		JsonObject<Tower> towerJson = new JsonObject<Tower>();

		towerJson.setiTotalDisplayRecords(towerListCount);
		towerJson.setiTotalRecords(towerCount);
		towerJson.setAaData(towerList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String returnJson = gson.toJson(towerJson);

		return returnJson;

	}

	/**
	 * Fetches users based on request attributes
	 * 
	 * @param request
	 *            get request from page
	 * @return json object
	 */
	@RequestMapping(value = "/admin/json/users", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String userJson(HttpServletRequest request)
			throws IOException {

		int pageNo = 0;
		int pageLength = 10;
		String searchTerm = "";
		if (request.getParameter("iDisplayStart") != null) {
			pageNo = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
		}

		if (request.getParameter("start") != null) {
			pageNo = (Integer.valueOf(request.getParameter("start")) / 10) + 1;
		}

		if (request.getParameter("iDisplayLength") != null) {
			pageLength = Integer
					.valueOf(request.getParameter("iDisplayLength"));
		}
		if (request.getParameter("length") != null) {
			pageLength = Integer.valueOf(request.getParameter("length"));
		}

		if (request.getParameter("sSearch") != null) {
			searchTerm = request.getParameter("sSearch");
		}

		List<User> userList;

		int userCount = userService.getNumberofUsers();
		int userListCount;

		if (!searchTerm.isEmpty() && searchTerm != null
				&& !searchTerm.equals("")) {
			userList = userService.getPaginatedUsersByTerm(pageLength,
					(pageNo - 1) * 10, searchTerm);
			userListCount = userService
					.getNumberOfUsersBySearchTerm(searchTerm);
		} else {
			userList = userService.getPaginatedUsers(pageLength,
					(pageNo - 1) * 10);
			userListCount = userCount;
		}

		JsonObject<User> userJson = new JsonObject<User>();
		userJson.setiTotalDisplayRecords(userListCount);
		userJson.setiTotalRecords(userCount);
		userJson.setAaData(userList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String returnJson = gson.toJson(userJson);

		return returnJson;

	}

	/**
	 * Fetches towers based on request attributes
	 * 
	 * @param request
	 *            get request from page
	 * @return json object
	 */
	@RequestMapping(value = "/json/towers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String userTowerJson(HttpServletRequest request)
			throws IOException {

		int pageNo = 0;
		int pageLength = 10;
		String searchTerm = "";

		if (request.getParameter("iDisplayStart") != null) {
			pageNo = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
		}

		if (request.getParameter("start") != null) {
			pageNo = (Integer.valueOf(request.getParameter("start")) / 10) + 1;
		}

		if (request.getParameter("iDisplayLength") != null) {
			pageLength = Integer
					.valueOf(request.getParameter("iDisplayLength"));
		}
		if (request.getParameter("length") != null) {
			pageLength = Integer.valueOf(request.getParameter("length"));
		}

		searchTerm = request.getParameter("sSearch");
		List<TowerDescriptor> towerList;
		int towerCount = towerService.getNumberOfTowers();
		int towerListCount;

		if (searchTerm != null && !searchTerm.equals("")) {
			towerList = towerService.getPaginatedTowerDescriptorsBySearchTerm(
					pageLength, (pageNo - 1) * 10, searchTerm);
			towerListCount = towerService
					.getNumberOfTowersBySearchTerm(searchTerm);
		} else {
			towerList = towerService.getPaginatedTowerDescriptors(pageLength,
					(pageNo - 1) * 10);
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

	/**
	 * Fetches towers based on request attributes
	 * 
	 * @param request
	 *            get request from page
	 * @return json object
	 */
	@RequestMapping(value = "/json/towermap", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String, Object> towerMapJson(
			HttpServletRequest request, Principal principal) throws IOException {

		boolean ringable = true;
		boolean groundFloorRing = true;
		boolean userVisited = false;
		int minimumBells = 0;
		int maximumBells = 99;
		String diocese = "";

		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			System.out.println(entry.getKey() + "/"
					+ Arrays.toString(entry.getValue()));
		}

		if (request.getParameter("ringable") != null) {
			if (request.getParameter("ringable").equals("1")) {
				ringable = true;
			} else {
				ringable = false;
			}
		}

		if (request.getParameter("groundFloorRing") != null) {
			if (request.getParameter("groundFloorRing").equals("1")) {
				groundFloorRing = false;
			} else {
				groundFloorRing = true;
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

		List<TowerShort> towers = towerService.getMapTowers(diocese,
				minimumBells, maximumBells, ringable, groundFloorRing);
		List<TowerVisit> towerVisits = null;
		Set<TowerShort> finalSet = new HashSet<TowerShort>();
		if (request.getParameter("userVisited") != null) {
			System.out.println("NOT NULL");
			if (request.getParameter("userVisited").equals("1")) {
				System.out.println("USER VISITED");
				towerVisits = towerVisitService.getVisitsByUserId(userService
						.getUser(principal.getName()).getId());
				for (TowerVisit visit : towerVisits) {
					for (TowerShort tower : towers) {
						if (visit.getTower().getId() == tower.getT()) {
							System.out.println("Adding");
							finalSet.add(tower);
							break;
						}
					}

				}
			} else {
				finalSet = new HashSet<TowerShort>(towers);
			}
		} else {
			finalSet = new HashSet<TowerShort>(towers);
		}

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("towers", finalSet);
		data.put("number", towers.size());

		return data;
	}

	@RequestMapping(value = "/towers/gettowers", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getTowers(HttpServletRequest request) {

		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			System.out.println(entry.getKey() + "/"
					+ Arrays.toString(entry.getValue()));
		}

		List<TowerShort> towers = null;

		towers = towerService.getTowersShort();

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("towers", towers);
		data.put("number", towers.size());

		return data;
	}

	/**
	 * Fetches peals based on request attributes
	 * 
	 * @param request
	 *            get request from page
	 * @return json object
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/json/peals", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String pealJson(HttpServletRequest request)
			throws IOException {

		// Map<String, String[]> params = request.getParameterMap();
		// for (Map.Entry<String, String[]> entry : params.entrySet()) {
		// System.out.println(entry.getKey() + "/" + entry.getValue());
		// }

		int pageNo = 0;
		int pageLength = 10;
		int towerId = 0;
		List<Peal> pealList;
		int pealCount = pealService.getNumberOfPeals();
		int pealListCount = 0;
		String ringer = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String aLongTimeAgo = "01-01-1000";
		Date dateFrom = null;
		Date dateTo = new Date(Calendar.getInstance().getTimeInMillis());
		System.out.println(pealCount + "/" + pealListCount);
		if (request.getParameter("iDisplayStart") != null) {
			pageNo = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
		}

		if (request.getParameter("start") != null) {
			pageNo = (Integer.valueOf(request.getParameter("start")) / 10) + 1;
		}

		if (request.getParameter("iDisplayLength") != null) {
			pageLength = Integer
					.valueOf(request.getParameter("iDisplayLength"));
		}
		if (request.getParameter("length") != null) {
			pageLength = Integer.valueOf(request.getParameter("length"));
		}

		if (request.getParameter("tower") != null) {
			towerId = Integer.valueOf(request.getParameter("tower"));
		}

		if (request.getParameter("ringer") != null) {
			ringer = request.getParameter("ringer");
		}

		try {
			dateFrom = new Date(sdf.parse(aLongTimeAgo).getTime());
		} catch (ParseException e) {
		}

		if (request.getParameter("dateFrom") != null) {
			try {
				dateFrom = new Date(sdf.parse(request.getParameter("dateFrom"))
						.getTime());
			} catch (ParseException e) {
			}
		}

		if (request.getParameter("dateTo") != null) {
			try {
				dateTo = new Date(sdf.parse(request.getParameter("dateTo"))
						.getTime());
			} catch (ParseException e) {
			}
		}
		System.out.println("DateFrom: " + dateFrom.toString() + " // "
				+ "DateTo: " + dateTo.toString());

		if (dateTo != null || dateFrom != null || towerId != 0
				|| !ringer.isEmpty()) {
			pealList = pealService.getPaginatedPealsForTower(towerId, dateFrom,
					dateTo, ringer, pageLength, (pageNo - 1) * 10);
			pealListCount = pealService.getNumberPealsForTower(towerId,
					dateFrom, dateTo, ringer);
			System.out.println("HERE");
		} else {
			pealList = pealService.getPaginatedPeals(pageLength,
					(pageNo - 1) * 10);
			pealListCount = pealCount;
			System.out.println("THERE");
		}
		System.out.println(pealCount + "/" + pealListCount);

		for (Peal peal : pealList) {
			peal.setTower(towerService.getTowerDescriptor(peal.getTower()
					.getId()));
		}

		JsonObject<Peal> pealJson = new JsonObject<Peal>();

		pealJson.setiTotalDisplayRecords(pealListCount);
		pealJson.setiTotalRecords(pealCount);
		pealJson.setAaData(pealList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String returnJson = gson.toJson(pealJson);

		return returnJson;

	}

	/**
	 * Fetches visits based on request attributes
	 * 
	 * @param request
	 *            get request from page
	 * @return json object
	 */
	@RequestMapping(value = "/json/visits", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String visitsJson(Principal principal,
			HttpServletRequest request) throws IOException {

		int pageNo = 0;
		int pageLength = 10;
		List<TowerVisit> visitList;
		int visitCount;
		int userId = userService.getUser(principal.getName()).getId();

		if (request.getParameter("iDisplayStart") != null) {
			pageNo = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
		}

		if (request.getParameter("start") != null) {
			pageNo = (Integer.valueOf(request.getParameter("start")) / 10) + 1;
		}

		if (request.getParameter("iDisplayLength") != null) {
			pageLength = Integer
					.valueOf(request.getParameter("iDisplayLength"));
		}
		if (request.getParameter("length") != null) {
			pageLength = Integer.valueOf(request.getParameter("length"));
		}

		visitCount = towerVisitService.getNumberOfVisits(userId);

		visitList = towerVisitService.getPaginatedVisits(userId, pageLength,
				(pageNo - 1) * 10);

		for (TowerVisit visit : visitList) {
			visit.setTower(towerService.getTowerDescriptor(visit.getTower()
					.getId()));
		}

		JsonObject<TowerVisit> visitJson = new JsonObject<TowerVisit>();

		visitJson.setiTotalDisplayRecords(visitCount);
		visitJson.setiTotalRecords(visitCount);
		visitJson.setAaData(visitList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String returnJson = gson.toJson(visitJson);

		return returnJson;

	}
}
