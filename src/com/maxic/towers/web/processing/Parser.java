package com.maxic.towers.web.processing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.maxic.towers.web.dao.Tower;

public class Parser {

	public static void main(String[] args) {

		String inputFile = "dove.txt";
		BufferedReader br = null;
		String line = null;
		String delimiter = "\\\\";
		int i = 0;
		ArrayList<String[]> towerList = new ArrayList<String[]>();
		
		try {

			br = new BufferedReader(new FileReader(inputFile));
			while ((line = br.readLine()) != null) {

				String[] towers = line.split(delimiter);
				Tower tower = new Tower();
				
				tower.setDoveId(towers[0]);
				tower.setLatitude(Float.parseFloat(towers[36]));
				tower.setLongitude(Float.parseFloat(towers[37]));
				
				System.out.println("Tower [" + towers[0] + " " + towers[36]
						+ " " + towers[37] + "]");
				i++;
				
				
				if (i > 200) {
					break;
				}
			}

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

		String[] tower1 = towerList.get(1);
		
		for (String towerproperty : tower1) {
			System.out.println(towerproperty);
		}
		
//		StringBuffer s = new StringBuffer();
//		for (int j = 0; j < towerList.size(); j++) {
//			s.append((j+1) + " TOWER: " + towerList.get(j)[0] + "\n");
//			System.out.println(towerList.get(j)[0]);
//		}
		
		System.out.println("Done");
	}

}
