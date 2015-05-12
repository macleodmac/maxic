<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>

<!--[if lte IE 9]>
  <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6.2/html5shiv.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.3.0/respond.js"></script>
<![endif]-->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/map.css">
<!-- 
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script> -->
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?libraries=places&sensor=false"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/FastMarkerOverlay.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/markerclusterer_compiled.js"></script>


<script src="${pageContext.request.contextPath}/static/js/maps.js"></script>
</head>
<body>
	<header>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</header>
	<tiles:insertAttribute name="content"></tiles:insertAttribute>
</body>
</html>