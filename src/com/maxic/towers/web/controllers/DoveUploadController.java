package com.maxic.towers.web.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maxic.towers.web.model.ContactDetails;
import com.maxic.towers.web.model.Country;
import com.maxic.towers.web.model.Diocese;
import com.maxic.towers.web.model.DoveFileWrapper;
import com.maxic.towers.web.model.FileValidator;
import com.maxic.towers.web.model.Practice;
import com.maxic.towers.web.model.Tower;
import com.maxic.towers.web.service.ContactDetailsService;
import com.maxic.towers.web.service.CountryService;
import com.maxic.towers.web.service.DioceseService;
import com.maxic.towers.web.service.PracticeService;
import com.maxic.towers.web.service.TowerService;

@Controller
@RequestMapping("/admin/dove")
public class DoveUploadController {

	@Autowired
	FileValidator validator;

	@Autowired
	TowerService towerService;

	@Autowired
	ContactDetailsService contactDetailsService;

	@Autowired
	PracticeService practiceService;

	@Autowired
	DioceseService dioceseService;

	@Autowired
	CountryService countryService;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model) {
		DoveFileWrapper fileModel = new DoveFileWrapper();
		model.addAttribute("file", fileModel);
		return "admin/dove/uploadfile";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fileUploaded(Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		System.out.println(request.toString());
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multiRequest.getFile("file");

		System.out.println(file.getContentType());
		System.out.println(file.getOriginalFilename());

		boolean success = true;
		try {
			if (file.getOriginalFilename()
					.equalsIgnoreCase("dove.txt")) {
				success = this.processDoveTxt(file);
			} else if (file.getOriginalFilename().equalsIgnoreCase(
					"newpks.txt")) {
				success = this.processNewPks(file);
			} else {
				success = false;
			}
		} catch (Exception e) {
			success = false;
	}

		if (success) {
			model.addAttribute("successMessage",
					"The database was successfully updated!");
		} else {
			model.addAttribute("dangerMessage",
					"The file could not be uploaded. Please try again.");
		}

		return "admin/dove/uploadfile";
	}

	private boolean processNewPks(MultipartFile multipartFile) {

		boolean success = true;
		try {
			Map<String, String> pks = parseNewPks(multipartFile);
			for (Map.Entry<String, String> entry : pks.entrySet()) {
				String oldId = entry.getKey().toString();
				String newId = entry.getValue();
				if (towerService.existsByDoveId(oldId)) {
					Tower tower = towerService.getTowerByDoveId(oldId);
					tower.setDoveId(newId);
					towerService.addTower(tower);
					System.out.println("Updating " + oldId + " to " + newId);
				} else {
					System.out.println("Already up to date!");
				}

			}
		} catch (Exception e) {
			success = false;
		}

		return success;
	}

	private boolean processDoveTxt(MultipartFile multipartFile) {
		List<Tower> towerList = this.parseDoveFile(multipartFile);
		boolean success = true;
		try {
			if (towerList != null) {
				for (Tower tower : towerList) {

					if (!countryService.countryExists(tower.getCountry()
							.getIsoCode())) {
						countryService.addCountry(tower.getCountry());
					}

					if (!dioceseService.dioceseExists(tower.getDiocese()
							.getDioceseId())) {
						dioceseService.addDiocese(tower.getDiocese());
					}

					Tower tempTower = new Tower();
					tempTower.setTowerId(0);
					if (towerService.existsByDoveId(tower.getDoveId())) {
						tempTower = towerService.getTowerByDoveId(tower
								.getDoveId());
						tower.setTowerId(tempTower.getTowerId());
						towerService.addTower(tower);
						System.out.println("EXISTS: " + tower);
					} else {
						towerService.addTower(tower);
						System.out.println("DOESN'T EXIST: " + tower);
					}

					tempTower = towerService
							.getTowerByDoveId(tower.getDoveId());

					if (!tower.getWebsite().isEmpty()) {
						if (!contactDetailsService.existsByDetail(
								tempTower.getTowerId(), tower.getWebsite())) {
							ContactDetails contactDetails = new ContactDetails(
									tempTower.getTowerId(), 0, "Website",
									tower.getWebsite());
							contactDetailsService
									.addContactDetails(contactDetails);
						}
					}

					if (!tower.getPracticeNight().isEmpty()) {

						if (!practiceService.existsByNight(
								tempTower.getTowerId(),
								tower.getPracticeNight())) {
							Practice practice = new Practice(
									tempTower.getTowerId(), 0, "",
									tower.getPracticeNight(),
									tower.getPracticeTime(),
									tower.getPracticeFrequency(), false);
							practiceService.addPractice(practice);
						}

					}
				}
			}
		} catch (Exception e) {
			success = false;
		}

		return success;
	}

	private Map<String, String> parseNewPks(MultipartFile multipartFile) {

		BufferedReader br = null;
		String line = null;
		String delimiter = "\\\\";

		Map<String, String> map = new TreeMap<String, String>();

		try {

			InputStream is = multipartFile.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));

			br.readLine(); // Skip Headers

			while ((line = br.readLine()) != null) {

				String[] ids = line.split(delimiter, -1);

				map.put(ids[0], ids[1]);

			}
			is.close();
			return map;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return map;

	}

	private ArrayList<Tower> parseDoveFile(MultipartFile multipartFile) {

		BufferedReader br = null;
		String line = null;
		String delimiter = "\\\\";
		ArrayList<Tower> towerList = new ArrayList<Tower>();

		try {

			InputStream is = multipartFile.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));

			br.readLine(); // Skip Headers

			while ((line = br.readLine()) != null) {

				String[] towerProperties = line.split(delimiter, -1);
				for (String property : towerProperties) {
					if (property.isEmpty() || property.equals("")) {
						property = null;
						System.out.println("Replacing " + property + " with 0");
					}
				}

				// Get Latitude and Longitude in Float form.
				Float latitude = 0f;
				Float longitude = 0f;
				Float satNavLatitude = 0f;
				Float satNavLongitude = 0f;
				try {
					latitude = Float.parseFloat(towerProperties[36]);
				} catch (NumberFormatException e) {
					System.out.println("Latitude was empty!");
				}

				try {
					longitude = Float.parseFloat(towerProperties[37]);
				} catch (NumberFormatException e) {
					System.out.println("Longitude was empty!");
				}

				try {
					satNavLatitude = Float.parseFloat(towerProperties[2]);
				} catch (NumberFormatException e) {
					System.out.println("SNLatitude was empty!");
				}

				try {
					satNavLongitude = Float.parseFloat(towerProperties[3]);
				} catch (NumberFormatException e) {
					System.out.println("SNLongitude was empty!");
				}
				boolean sim = false;
				boolean toilet = false;
				boolean gfr = false;
				boolean ringable = true;

				if (towerProperties[5].equalsIgnoreCase("t")) {
					sim = true;
				}

				if (towerProperties[21].equalsIgnoreCase("t")) {
					toilet = true;
				}

				if (towerProperties[20].equalsIgnoreCase("gf")) {
					gfr = true;
				}

				if (towerProperties[22].equalsIgnoreCase("u/r")) {
					ringable = false;
				}

				if (!towerProperties[31].isEmpty()) {
					ContactDetails contactDetails = new ContactDetails();
					contactDetails.setType("Website");
					contactDetails.setDetail(towerProperties[31]);
				}

				System.out.println("Listing: " + towerProperties[39]);

				String doveId = towerProperties[0];
				int towerbaseId = Integer.parseInt(towerProperties[5]);
				String placeName = towerProperties[10];
				String placeName2 = towerProperties[11];
				String placeNameCL = towerProperties[12];
				String gridReference = towerProperties[1];
				String postCode = towerProperties[4];
				Country country = new Country(towerProperties[8],
						towerProperties[7]);
				Diocese diocese = new Diocese(towerProperties[9],
						towerProperties[9]);
				String dedication = towerProperties[13];
				String listedGrade = towerProperties[38];
				String extraInfo = towerProperties[30];
				String affiliation = towerProperties[33];
				String accessDetails = "";
				String towerCaptain = "";
				int numberOfBells = Integer.parseInt(towerProperties[14]);
				String website = towerProperties[31];
				String practiceNight = towerProperties[24];
				String practiceTime = towerProperties[25];
				String practiceRegularity = towerProperties[26];

				if (towerProperties[24].equalsIgnoreCase("mon")) {
					practiceNight = "Monday";
				} else if (towerProperties[24].equalsIgnoreCase("tue")) {
					practiceNight = "Tuesday";
				} else if (towerProperties[24].equalsIgnoreCase("wed")) {
					practiceNight = "Wednesday";
				} else if (towerProperties[24].equalsIgnoreCase("thu")) {
					practiceNight = "Thursday";
				} else if (towerProperties[24].equalsIgnoreCase("fri")) {
					practiceNight = "Friday";
				} else if (towerProperties[24].equalsIgnoreCase("sat")) {
					practiceNight = "Saturday";
				} else if (towerProperties[24].equalsIgnoreCase("sun")) {
					practiceNight = "Sunday";
				}

				if (!website.isEmpty()) {
					if (!website.startsWith("http://")) {
						website = "http://" + website;
					}
				}

				Tower tower = new Tower(0, doveId, towerbaseId, placeName,
						placeName2, placeNameCL, ringable, gridReference,
						latitude, longitude, postCode, satNavLatitude,
						satNavLongitude, country, diocese, dedication,
						listedGrade, gfr, sim, toilet, extraInfo, affiliation,
						accessDetails, towerCaptain, numberOfBells, website,
						practiceNight, practiceTime, practiceRegularity);

				towerList.add(tower);

			}

			is.close();
			return towerList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return towerList;

	}

}
