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

import com.maxic.towers.web.dao.Tower;

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
					if (property.isEmpty()) {
						property = "0";
					}
				}
				// Tower tower = new Tower(0, towerProperties[0],
				// Integer.parseInt(towerProperties[5]),
				// towerProperties[10], towerProperties[11],
				// towerProperties[12], "", towerProperties[1],
				// Float.parseFloat(towerProperties[36]),
				// Float.parseFloat(towerProperties[37]),
				// towerProperties[4],
				// Float.parseFloat(towerProperties[2]),
				// Float.parseFloat(towerProperties[3]), 0, 0, 0,
				// towerProperties[13], towerProperties[38], false, false,
				// false, towerProperties[30],
				// Integer.parseInt(towerProperties[39]), "", "", "");

				Tower tower = new Tower(0, towerProperties[0],
						Integer.parseInt(towerProperties[5]),
						towerProperties[10], towerProperties[11],
						towerProperties[12], "", towerProperties[1], 0, 0,
						towerProperties[4], 0, 0, 0, 0, 0, towerProperties[13],
						towerProperties[38], false, false, false,
						towerProperties[30], 0, "", "", "");

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return towerList;

	}

}
