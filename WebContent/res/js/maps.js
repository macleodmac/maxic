function initialize() {
	var mapCanvas = document.getElementById('fullscreen-map');
	var mapOptions = {
		center : new google.maps.LatLng(53.3795514, -1.4709423),
		zoom : 8,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}
	var map = new google.maps.Map(mapCanvas, mapOptions);

	var towers = [];
	towers.push(new google.maps.LatLng(53.3795514, -1.4709423));
	towers.push(new google.maps.LatLng(53.6795514, -1.0709423));
	towers.push(new google.maps.LatLng(53.0795514, -1.7709423));
	var arrayLength = towers.length;

	for (var i = 0; i < arrayLength; i++) {
		var towerMarker = new google.maps.Marker({
			position : towers[i],
			map : map,
		});
		
		google.maps.event.addListener(towerMarker, 'click', function() {
		    map.setZoom(10);
		    map.setCenter(towerMarker.getPosition());
		  });
	}
	
}
google.maps.event.addDomListener(window, 'load', initialize);