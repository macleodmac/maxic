package com.maxic.towers.web.controllers;

import static org.simmetrics.StringMetricBuilder.with;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.simmetrics.StringMetric;
import org.simmetrics.StringMetrics;
import org.simmetrics.metrics.CosineSimilarity;
import org.simmetrics.simplifiers.Case;
import org.simmetrics.tokenizers.QGram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.maxic.towers.web.model.Peal;
import com.maxic.towers.web.model.TowerDescriptor;
import com.maxic.towers.web.service.PealService;
import com.maxic.towers.web.service.TowerService;

@EnableScheduling
@Controller
public class CrawlerController {

	private TowerService towerService;
	private PealService pealService;

	@Autowired
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}

	@Autowired
	public void setPealService(PealService pealService) {
		this.pealService = pealService;
	}

	class Ringer {

		String number;
		String name;
		boolean conductor;

		public Ringer(String number, String name, boolean conductor) {
			this.number = number;
			this.name = name;
			this.conductor = conductor;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isConductor() {
			return conductor;
		}

		public void setConductor(boolean conductor) {
			this.conductor = conductor;
		}

		@Override
		public String toString() {
			return "Ringer [number=" + number + ", name=" + name
					+ ", conductor=" + conductor + "]";
		}

	}

	/**
	 * Connects to bellboard, gets a list of all the latest performances, checks
	 * if they already exist, if not scrapes their data and adds to the database
	 * Runs every day
	 */
	@Scheduled(fixedDelay = 86400000)
	public void crawlBellboard() {
		String baseUrl = "http://www.bb.ringingworld.co.uk/";
		Document doc;

		List<String> linkList = new ArrayList<String>();
		try {
			doc = Jsoup.connect(baseUrl + "list.php").get();
			Elements rows = doc.getElementsByClass("place");
			for (Element row : rows) {
				String link = row.select("a").first().attr("href");
				linkList.add(link);
			}
		} catch (IOException e) {

		}

		List<String> finalLinkList = new ArrayList<String>();

		for (String link : linkList) {
			String temp = link.replaceAll("[^0-9]", "");
			int tempId = Integer.parseInt(temp);
			if (!pealService.bellboardPealExists(tempId)) {
				finalLinkList.add(link);
			}
		}

		StringMetric metric = with(new CosineSimilarity<String>())
				.simplify(new Case.Lower()).tokenize(new QGram(2)).build();
		List<TowerDescriptor> towers = towerService.getTowerDescriptors();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (TowerDescriptor tower : towers) {
			map.put(tower.getDe(), tower.getId());
		}

		List<String> descriptions = new ArrayList<String>(map.keySet());

		Peal peal;
		for (String link : finalLinkList) {

			// for (int i = 0; i <5; i++) {
			// String link = finalLinkList.get(i);
			peal = parsePage(baseUrl + link);
			String rwId = link.replaceAll("[^0-9]", "");
			try {
				int id = Integer.parseInt(rwId);
				peal.setRingingWorldId(id);
			} catch (NumberFormatException e) {

			}
			System.out.println(peal);

			String bbDescription = peal.getTower().getDe();

			float[] scores = StringMetrics.compare(metric, bbDescription,
					descriptions);
			System.out.println("Description: " + bbDescription);
			List<Float> floatScores = Arrays
					.asList(ArrayUtils.toObject(scores));
			int minIndex = floatScores.indexOf(Collections.min(floatScores));
			String minDesc = descriptions.get(minIndex);
			int maxIndex = floatScores.indexOf(Collections.max(floatScores));
			String maxDesc = descriptions.get(maxIndex);
			System.out.println(minDesc);
			System.out.println(maxDesc);
			int towerId = map.get(maxDesc);
			TowerDescriptor tower = towerService.getTowerDescriptor(towerId);
			peal.setTower(tower);

			pealService.addPeal(peal);
		}

	}

	/**
	 * Connects to the url, fetches data and creates a peal from it
	 * @param  url String url of the performance to be scraped
	 * @return  Peal the peal extracted from information on the page
	 */
	private Peal parsePage(String url) {
		Document doc;
		Peal peal = new Peal();
		try {
			doc = Jsoup.connect(url).get();

			// Extract performance div
			Element performance = doc.getElementsByClass("performance").first();

			// Extract association div
			Element associationElement = performance.getElementsByClass(
					"association").first();

			Element address = performance.getElementsByClass("address").first();
			Element ringerClass = performance.getElementsByClass("ringers")
					.first();
			Element details = performance.getElementsByClass("details").first();

			// Get the placename in full
			Element placeSpan = performance.getElementsByClass("place").first();
			Element place = placeSpan.parent();
			String placeName = place.text();

			// Get changes
			Element changesElement = performance.getElementsByClass("changes")
					.first();
			int changes = 0;
			if (changesElement != null) {
				try {
					changes = Integer.parseInt(changesElement.text());
				} catch (NumberFormatException e) {
					changes = 0;
				}
			}
			Element title = performance.getElementsByClass("title").first();

			Elements footnotes = performance.getElementsByClass("footnote");

			// Get the ringers and parse them
			Elements ringers = ringerClass.getElementsByTag("div");

			List<Ringer> ringerList = new ArrayList<Ringer>();

			for (int i = 1; i < ringers.size(); i++) {
				boolean isConductor = false;
				Element ringer = ringers.get(i);
				String conductor = ringer.html().replaceAll("<.*>", "");
				conductor = conductor.replaceAll("\\s+", "");
				if (!conductor.isEmpty()) {
					isConductor = true;
				}
				String number = ringer.getElementsByTag("span").first().html();
				String name = ringer.getElementsByTag("span").get(1).html();
				Ringer ringer2 = new Ringer(number, name, isConductor);
				ringerList.add(ringer2);
			}

			// Parse the date and time

			Element dateTime = null;

			for (Element div : performance.getElementsByTag("div")) {
				if (div.html().toLowerCase().contains("monday")
						|| div.html().toLowerCase().contains("tuesday")
						|| div.html().toLowerCase().contains("wednesday")
						|| div.html().toLowerCase().contains("thursday")
						|| div.html().toLowerCase().contains("friday")
						|| div.html().toLowerCase().contains("saturday")
						|| div.html().toLowerCase().contains("sunday")
						&& !div.hasClass("footnote")) {
					dateTime = div;
				}
			}

			String[] dateSplit = dateTime.html().split(" in ");
			String date = dateSplit[0];
			String time;
			try {
				time = dateSplit[1];
				// Pattern minutePattern = Pattern
				// .compile("^([0-9]+\\sh\\s?[0-9]{1,2})(.*)");
				// Pattern hourPattern = Pattern
				// .compile("^([0-9]{1,2}\\s?Mins)(.*)");
				// Matcher minuteMatcher = minutePattern.matcher(time);
				// Matcher hourMatcher = hourPattern.matcher(time);
				//
				// if (minuteMatcher.matches()) {
				// time = minuteMatcher.group(1);
				// } else if (hourMatcher.matches()) {
				// time = hourMatcher.group(1);
				// }
			} catch (ArrayIndexOutOfBoundsException e) {
				time = null;
			}

			SimpleDateFormat format = new SimpleDateFormat("EEEE, d MMM yyyy");
			Date parsedDate;
			java.sql.Date sqlDate = null;
			try {
				parsedDate = format.parse(date);
				sqlDate = new java.sql.Date(parsedDate.getTime());
			} catch (ParseException e) {
			}

			if (title != null) {
				peal.setMethod(title.html());
			}
			peal.setChanges(changes);
			peal.setTime(time);

			StringBuffer footer = new StringBuffer();
			for (Element footnote : footnotes) {
				footer.append(footnote.html() + "\n");
			}
			peal.setFootnotes(footer.toString());

			peal.setDateRung(sqlDate);
			if (details != null) {
				peal.setMethodDetails(details.html());
			}
			if (address != null) {
				placeName = placeName + " " + address.text();
			}
			peal.setTower(new TowerDescriptor(1, placeName));

			Iterator<Ringer> ringerIterator = ringerList.iterator();

			for (Ringer ringer : ringerList) {
				if (ringer.getNumber().equals("1")) {
					peal.setRinger1(ringer.getName());
				} else if (ringer.getNumber().equals("2")) {
					peal.setRinger2(ringer.getName());
				} else if (ringer.getNumber().equals("3")) {
					peal.setRinger3(ringer.getName());
				} else if (ringer.getNumber().equals("4")) {
					peal.setRinger4(ringer.getName());
				} else if (ringer.getNumber().equals("5")) {
					peal.setRinger5(ringer.getName());
				} else if (ringer.getNumber().equals("6")) {
					peal.setRinger6(ringer.getName());
				} else if (ringer.getNumber().equals("7")) {
					peal.setRinger7(ringer.getName());
				} else if (ringer.getNumber().equals("8")) {
					peal.setRinger8(ringer.getName());
				} else if (ringer.getNumber().equals("9")) {
					peal.setRinger9(ringer.getName());
				} else if (ringer.getNumber().equals("10")) {
					peal.setRinger10(ringer.getName());
				} else if (ringer.getNumber().equals("11")) {
					peal.setRinger11(ringer.getName());
				} else if (ringer.getNumber().equals("12")) {
					peal.setRinger12(ringer.getName());
				} else if (ringer.getNumber().equals("13")) {
					peal.setRinger13(ringer.getName());
				} else if (ringer.getNumber().equals("14")) {
					peal.setRinger14(ringer.getName());
				} else if (ringer.getNumber().equals("15")) {
					peal.setRinger15(ringer.getName());
				} else if (ringer.getNumber().equals("16")) {
					peal.setRinger16(ringer.getName());
				}
				if (ringer.isConductor()) {
					peal.setLeader(ringer.getName());
				}
			}

		} catch (IOException e) {
		}

		return peal;
	}

}