<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header>
			<div class="jumbotron">
				<h1>TowerFinder</h1>
			</div>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-xs-6">

				<c:out value="${name}"></c:out>
				<h3><a href="${pageContext.request.contextPath}/addtower">Add a new tower.</a></h2>
				<h3><a href="${pageContext.request.contextPath}/towers">View towers.</a></h2>
				
			</div>
		</div>
	</div>
</body>
</html>