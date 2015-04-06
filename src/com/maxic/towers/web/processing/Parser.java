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

	public ArrayList<Tower> parseDoveFile() {

		ApplicationContext appContext = new ClassPathXmlApplicationContext();

		Resource resource = appContext
				.getResource("classpath:/com/maxic/towers/web/processing/dove.txt");

		BufferedReader br = null;
		String line = null;
		String delimiter = "\\\\";
		ArrayList<Tower> towerList = new ArrayList<Tower>();

		try {

			InputStream is = resource.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));

			br.readLine();

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

				ArrayList<ContactDetails> detailsList = null;
				if (!towerProperties[31].isEmpty()) {
					detailsList = new ArrayList<ContactDetails>();
					ContactDetails contactDetails = new ContactDetails();
					contactDetails.setWebsite(towerProperties[31]);
					detailsList.add(contactDetails);
				}
				
				
				
				
				
				
				System.out.println("Listing: " + towerProperties[39]);
				Tower tower = new Tower(0, towerProperties[0],
						Integer.parseInt(towerProperties[5]),
						towerProperties[10], towerProperties[11],
						towerProperties[12], ringable, towerProperties[1],
						latitude, longitude, towerProperties[4],
						satNavLatitude, satNavLongitude, new Country(
								towerProperties[8], towerProperties[7]),
						new Diocese(towerProperties[9], towerProperties[9]), 0,
						towerProperties[13], towerProperties[38], gfr, sim,
						toilet, towerProperties[30], 0, "", "", "", null);

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

	static void main(String[] args) {
		Parser parser = new Parser();
		parser.parseDoveFile();
	}

}
