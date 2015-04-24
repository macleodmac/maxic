package com.maxic.towers.web.processing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.maxic.towers.web.model.ContactDetails;
import com.maxic.towers.web.model.Country;
import com.maxic.towers.web.model.Diocese;
import com.maxic.towers.web.model.Tower;

public class Parser {

	public static ArrayList<Tower> parseDoveFile(String fileLocation) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext();

		Resource resource = appContext
				.getResource("file:/"+fileLocation);
		
		BufferedReader br = null;
		String line = null;
		String delimiter = "\\\\";
		ArrayList<Tower> towerList = new ArrayList<Tower>();

		try {

			InputStream is = resource.getInputStream();
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
		try {
			((BufferedReader) appContext).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return towerList;

	}

}
