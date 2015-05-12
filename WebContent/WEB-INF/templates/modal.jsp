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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/main.css">
<link href='http://fonts.googleapis.com/css?family=Racing+Sans+One'
	rel='stylesheet' type='text/css'>

</head>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<tiles:insertAttribute name="prebody"></tiles:insertAttribute>
	</div>
	<div class="modal-body">

		<!--  insert common content -->
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
		
	</div>
	<div class="modal-footer">
		<a class="btn btn-default btn-primary pull-left"
			href="${pageContext.request.contextPath}/towers/view?t=${towerWrapper.tower.towerId}">View
			Alone</a>
		<a class="btn btn-default btn-primary pull-left"
			href="http://dove.cccbr.org.uk/detail.php?DoveID=${towerWrapper.tower.doveId}">View
			on Dove's Guide</a>
		<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>

	</div>
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>