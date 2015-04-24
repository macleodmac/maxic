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
			'url' : './towers/gettowers',
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
		maxZoom : 12,
		styles : [ {
			height : 31,
			url : "http://i.imgur.com/5oeQDPd.png",
			width : 31
		}, {
			height : 56,
			url : "http://i.imgur.com/lUekSlS.png",
			width : 56
		}, {
			height : 66,
			url : "http://i.imgur.com/lUekSlS.png",
			width : 66
		}, {
			height : 78,
			url : "http://i.imgur.com/lUekSlS.png",
			width : 78
		}, {
			height : 90,
			url : "http://i.imgur.com/lUekSlS.png",
			width : 90

		} ]
	};

	for (var i = 0; i < json.towers.length; i++) {
		var obj = json.towers[i];
		var location = new google.maps.LatLng(json.towers[i]["la"],
				json.towers[i]["lo"]);

		var towerMarker = new google.maps.Marker({
			position : location,
			icon : iconURL,
			title : json.towers[i]["t"].toString()
		});

		function emptyTower() {
			$('#viewTower').attr('data-remote', '#');
			return true;
		}

		google.maps.event.addListener(towerMarker, 'click', function() {
			var test = emptyTower();
			$('#viewTower').attr('data-remote',
					('./towers/modal?t=' + this.getTitle()));
			$('#viewTower').modal('show');

		});

		markers.push(towerMarker);

	}

	var mc = new MarkerClusterer(map, markers, mcOptions);
}


google.maps.event.addDomListener(window, 'load', initialize);