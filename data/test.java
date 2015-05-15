@Scheduled(fixedDelay = 10800000)
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