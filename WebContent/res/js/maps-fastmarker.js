function initialize() {
	var mapCanvas = document.getElementById('fullscreen-map');
	var mapOptions = {
		center : new google.maps.LatLng(53.3795514, -1.4709423),
		zoom : 8,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}
	var map = new google.maps.Map(mapCanvas, mapOptions);

	var json = (function() {
		var json = null;
		$.ajax({
			'async' : false,
			'global' : false,
			'url' : '../towers/gettowers',
			'dataType' : "json",
			'success' : function(data) {
				json = data;
			}
		});
		return json;
	})();
	
	var fastMarkers = [];

	
	for (var i = 0; i < json.towers.length; i++) {
		var obj = json.towers[i];
		var location = new google.maps.LatLng(json.towers[i]["la"],json.towers[i]["lo"]);
		// id, latLng, innerHtmlArray, divClassName, zIndex, leftOffset, topOffset
		var marker = new com.redfin.FastMarker(json.towers[i]["t"], location, ["<img src='/towers/static/js/marker.png'>"], "myMarker", 0, 10/*px*/, 10/*px*/);
		fastMarkers.push(marker);
	}
	
	new com.redfin.FastMarkerOverlay(map, fastMarkers);

		// google.maps.event.addListener(towerMarker, 'click', function() {
		// map.setZoom(10);
		// map.setCenter(towerMarker.getPosition());
		// });

}
google.maps.event.addDomListener(window, 'load', initialize);