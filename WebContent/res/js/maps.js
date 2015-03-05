var map;

function initialize() {
	var mapCanvas = document.getElementById('fullscreen-map');
	var mapOptions = {
		center : new google.maps.LatLng(53.3795514, -1.4709423),
		zoom : 12,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}
	map = new google.maps.Map(mapCanvas, mapOptions);

	var json = (function() {
		var json = null;
		$.ajax({
			'async' : false,
			'global' : false,
			'url' : './gettowers',
			'dataType' : "json",
			'success' : function(data) {
				json = data;
			}
		});
		return json;
	})();

	var iconURL = './static/img/church.png';
	var markers = [];
	var mcOptions = {
		gridSize : 50,
		maxZoom : 12
	 ,styles : [
	 {
	 height : 31,
	 url :
	 "http://i.imgur.com/5oeQDPd.png",
	 width : 31
	 },
	 {
	 height : 56,
	 url :
	 "http://i.imgur.com/lUekSlS.png",
	 width : 56
	 },
	 {
	 height : 66,
	 url :
	 "http://i.imgur.com/lUekSlS.png",
	 width : 66
	 },
	 {
	 height : 78,
	 url :
	 "http://i.imgur.com/lUekSlS.png",
	 width : 78
	 },
	 {
	 height : 90,
	 url :
	 "http://i.imgur.com/lUekSlS.png",
	 width : 90
	
	 } ]
	};

	for (var i = 0; i < json.towers.length; i++) {
		var obj = json.towers[i];
		var location = new google.maps.LatLng(json.towers[i]["la"],
				json.towers[i]["lo"]);

		var towerMarker = new google.maps.Marker({
			position : location,
			url : './towers/modal?t=' + json.towers[i]["t"],
			// map : map,
			icon : iconURL,
			title : json.towers[i]["t"].toString()
		});

		google.maps.event.addListener(towerMarker, 'click', function() {
			$('#viewTower').attr('data-remote',
					('./towers/modal?t=' + this.getTitle()));
			$('#viewTower').modal('show');

		});

		markers.push(towerMarker);

	}

	var mc = new MarkerClusterer(map, markers, mcOptions);
}

function showMarkers() {
	var bounds = map.getBounds();
	console.log(bounds);
	window.alert(bounds);
	// Call you server with ajax passing it the bounds

	// In the ajax callback delete the current markers and add new markers
}

google.maps.event.addDomListener(window, 'load', initialize);
google.maps.event.addListener(map, 'idle', function() {
	window.alert(map.getBounds());
});

$('body').on('hidden.bs.modal', '.modal', function() {
	$(this).removeData('bs.modal');
});