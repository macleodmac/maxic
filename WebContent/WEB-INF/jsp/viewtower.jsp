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
			<div class="col-xs-12">
				<table class="table">
					<tbody>
						<tr>
							<th>TowerID</th>
							<td><c:out value="${tower.towerId}"></c:out></td>
						</tr>
						<tr>
							<th>DoveID</th>
							<td><c:out value="${tower.doveId}"></c:out></td>
						</tr>
						<tr>
							<th>Latitude</th>
							<td><c:out value="${tower.latitude}"></c:out></td>
						</tr>
						<tr>
							<th>Longitude</th>
							<td><c:out value="${tower.longitude}"></c:out></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-11">
				<a class="btn btn-xs btn-default"
					href="${pageContext.request.contextPath}/towers">Back</a>
			</div>
			<div class="col-xs-1">
				<a class="btn btn-xs btn-danger"
					href="${pageContext.request.contextPath}/dodelete?t=${tower.towerId}">Delete</a>
			</div>
		</div>
	</div>
</body>
</html>