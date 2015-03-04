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
//		,styles : [
//				{
//					height : 53,
//					url : "http://icons.iconarchive.com/icons/fps.hu/free-christmas-flat-circle/96/bell-icon.png",
//					width : 53
//				},
//				{
//					height : 56,
//					url : "http://icons.iconarchive.com/icons/fps.hu/free-christmas-flat-circle/96/bell-icon.png",
//					width : 56
//				},
//				{
//					height : 66,
//					url : "http://icons.iconarchive.com/icons/fps.hu/free-christmas-flat-circle/96/bell-icon.png",
//					width : 66
//				},
//				{
//					height : 78,
//					url : "http://icons.iconarchive.com/icons/fps.hu/free-christmas-flat-circle/96/bell-icon.png",
//					width : 78
//				},
//				{
//					height : 96,
//					url : "http://icons.iconarchive.com/icons/fps.hu/free-christmas-flat-circle/96/bell-icon.png",
//					width : 96
//
//				} ]
	};

	for (var i = 0; i < json.towers.length; i++) {
		var obj = json.towers[i];
		var location = new google.maps.LatLng(json.towers[i]["la"],
				json.towers[i]["lo"]);

		var towerMarker = new google.maps.Marker({
			position : location,
			url : './towers/view?t=' + json.towers[i]["t"],
			// map : map,
			icon : iconURL
		});

		google.maps.event.addListener(towerMarker, 'click', function() {
			window.location.href = this.url;
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