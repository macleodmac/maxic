<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/map.css">
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/FastMarkerOverlay.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/markerclusterer_compiled.js"></script>
<script src="${pageContext.request.contextPath}/static/js/maps.js"></script>
<style>
.modal .modal-body {
    max-height: 450px;
    overflow-y: auto;
}
</style>

<div id="fullscreen-map"></div>

<div class="modal" id="viewTower" aria-labelledby="myModalLabel"
	aria-hidden="true"
	data-remote="#">
	<div class="modal-dialog modal-vertical-centered modal-lg">
		<div class="modal-content"></div>
	</div>
</div>