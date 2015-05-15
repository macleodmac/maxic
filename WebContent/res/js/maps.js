var map;
var ringable;
var groundFloorRing;
var userVisited;
var minBells;
var maxBells;
var iconURL = './static/img/map-icon-small.png';
var markers = [];
var mc;
var mcOptions = {
		gridSize : 50,
		maxZoom : 12,
		styles : [ {
			height : 31,
			url : "http://i.imgur.com/CFgOmj6.png",
			width : 31
		}, {
			height : 56,
			url : "http://i.imgur.com/FxruIMK.png",
			width : 56
		}, {
			height : 66,
			url : "http://i.imgur.com/Yq5EC2Y.png",
			width : 66
		}, {
			height : 78,
			url : "http://i.imgur.com/RKJhlv2.png",
			width : 78
		}, {
			height : 90,
			url : "http://i.imgur.com/ScgMcEd.png",
			width : 90

		} ]
	};
function initialize() {

	ringable = '1';
	groundFloorRing = '0';
	userVisited = 'yes';
	minBells = '1';
	maxBells = '16';
	$('#viewTower').modal({
		show : false,
		remote : ''
	});
	// Get Map Div
	var mapCanvas = document.getElementById('fullscreen-map');

	// Set map Options
	var mapOptions = {
		zoom : 12,
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		mapTypeControl : false,
		zoomControl : true,
		zoomControlOptions : {
			style : google.maps.ZoomControlStyle.LARGE,
		},
		panControl : true,
		streetViewControl : false,
	}

	// Create map
	map = new google.maps.Map(mapCanvas, mapOptions);

	// Try HTML5 geolocation
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = new google.maps.LatLng(position.coords.latitude,
					position.coords.longitude);

			map.setCenter(pos);
		}, function() {
			handleNoGeolocation(true);
		});
	} else {
		// Browser doesn't support geolocation
		handleNoGeolocation(false);
	}

	function handleNoGeolocation(errorFlag) {
		if (errorFlag) {
			var content = 'Error: The Geolocation service failed.';
		} else {
			var content = 'Error: Your browser doesn\'t support geolocation.';
		}

		var options = {
			map : map,
			position : new google.maps.LatLng(53.3795514, -1.4709423),
			content : content
		};

		map.setCenter(options.position);
	}

	// Get towers json
	reloadMarkers();

	// Create the search box and link it to the UI element.
	var input = /** @type {HTMLInputElement} */
	(document.getElementById('pac-input'));
	map.controls[google.maps.ControlPosition.TOP_RIGHT].push(input);

	// Create the search box and link it to the UI element.
	var filterOptions = /** @type {HTMLInputElement} */
	(document.getElementById('filterOptions'));
	map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(filterOptions);

	var searchBox = new google.maps.places.SearchBox(/** @type {HTMLInputElement} */
	(input));

	google.maps.event.addListener(searchBox, 'places_changed', function() {
		var places = searchBox.getPlaces();

		if (places.length == 0) {
			return;
		}
		for (var i = 0, marker; marker = markers[i]; i++) {
			marker.setMap(null);
		}

		// For each place, get the icon, place name, and location.
		var bounds = new google.maps.LatLngBounds();
		var newPos = new google.maps.LatLng()
		for (var i = 0, place; place = places[i]; i++) {

			newPos = new google.maps.LatLng(place.geometry.location.A,
					place.geometry.location.F);
		}
		map.setCenter(newPos);
	});

	$('#ringable, #groundFloorRing, #userVisited, #minBells, #maxBells')
			.change(function() {
				reloadMarkers();
				return;
			});
}

google.maps.event.addDomListener(window, 'load', initialize);

function reloadMarkers() {
	var json = null;
	var requestUrl = './json/towermap';

	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	if (mc != null) {
		mc.clearMarkers();
	}
	markers = [];

	if ($('#ringable').is(":checked")) {
		ringable = '1';
	} else {
		ringable = '0';
	}

	if ($('#groundFloorRing').is(":checked")) {
		groundFloorRing = '1';
	} else {
		groundFloorRing = '0';
	}

	if ($('#userVisited').is(":checked")) {
		userVisited = '1';
	} else {
		userVisited = '0';
	}

	if ($('#userVisited').is(":checked")) {
		userVisited = '1';
	} else {
		userVisited = '0';
	}

	minBells = $('#minBells').val();
	maxBells = $('#maxBells').val();
	console.log(minBells);
	requestUrl += '?ringable=' + ringable;
	requestUrl += '&groundFloorRing=' + groundFloorRing;
	requestUrl += '&userVisited=' + userVisited;
	requestUrl += '&minBells=' + minBells;
	requestUrl += '&maxBells=' + maxBells;
	$.ajax({
		'async' : false,
		'global' : false,
		'url' : requestUrl,
		'dataType' : "json",
		'success' : function(data) {
			json = data;
		}
	});

	for (var i = 0; i < json.towers.length; i++) {
		var obj = json.towers[i];
		var location = new google.maps.LatLng(json.towers[i]["la"],
				json.towers[i]["lo"]);

		var towerMarker = new google.maps.Marker({
			position : location,
			icon : iconURL,
			title : json.towers[i]["t"].toString()
		});

		google.maps.event.addListener(towerMarker, 'click', function() {
			console.log(this.getTitle());
			$('#viewTower').removeData('bs.modal');
			$('#viewTower').modal({
				remote : './towers/modal?t=' + this.getTitle()
			});
			$('#viewTower').modal('show');
		});

		markers.push(towerMarker);

		

	}

	mc = new MarkerClusterer(map, markers, mcOptions);

	return json;
};

