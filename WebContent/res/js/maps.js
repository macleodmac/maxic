function initialize() {
		var mapCanvas = document.getElementById('fullscreen-map');
		var mapOptions = {
			center : new google.maps.LatLng(53.3795514, -1.4709423),
			zoom : 8,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}
		var map = new google.maps.Map(mapCanvas, mapOptions);
	}
google.maps.event.addDomListener(window, 'load', initialize);