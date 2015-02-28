<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />

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
			<div class="col-xs-2">
				<a class="btn btn-md btn-default"
					href="${pageContext.request.contextPath}/admin/towers">Towers</a>
			</div>
			<div class="col-xs-2">
				<a class="btn btn-md btn-default"
					href="${pageContext.request.contextPath}/admin/peals">Peals</a>
			</div>

		</div>
	</div>
</body>
</html>