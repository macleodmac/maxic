function initialize() {

	var map;
	var myLatLng = new google.maps.LatLng(53.0669277, -3.3439732)
	var mapOptions = {
		zoom : 7,
		center : myLatLng
	};
	map = new google.maps.Map(document.getElementById('map-canvas'),
			mapOptions);
	var contentString = "Hello World!";

	var infowindow = new google.maps.InfoWindow({
		content : contentString
	});

	var marker = new google.maps.Marker({
		position : myLatLng,
		map : map,
		title : 'Uluru (Ayers Rock)'
	});

	google.maps.event.addListener(marker, 'click', function() {
		window.location.href = "http://www.google.com";
	});

}

google.maps.event.addDomListener(window, 'load', initialize);