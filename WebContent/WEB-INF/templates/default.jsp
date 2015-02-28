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
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">

</head>
<body>
	<header>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</header>
	<div class="container">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
	</div>
	<hr />
	<div class="footer">
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
</body>
</html>