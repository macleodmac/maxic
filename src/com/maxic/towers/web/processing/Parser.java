package com.maxic.towers.web.processing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.maxic.towers.web.dao.Tower;

public class Parser {

	public static void main(String[] args) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		
		Resource resource = appContext.getResource("classpath:com/maxic/towers/web/processing/dove.txt");
		
		
		String inputFile = "dove.txt";
		BufferedReader br = null;
		String line = null;
		String delimiter = "\\\\";
		int i = 0;
		ArrayList<String[]> towerList = new ArrayList<String[]>();
		
		try {

			InputStream is = resource.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			
			br.readLine();
			while ((line = br.readLine()) != null) {
					
				
				String[] towers = line.split(delimiter);
				Tower tower = new Tower();
				
				tower.setDoveId(towers[0]);
				
				System.out.println(towers[0]);
				
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

	}

}
