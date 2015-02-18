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
			<div class="row">
				<div class="col-xs-12">
					<h1>TowerFinder</h1>
				</div>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<c:if test="${not empty message }">
					<div class="alert alert-info" role="alert">
						<c:out value="${message}"></c:out>
					</div>
				</c:if>
				<c:if test="${empty towers}">
					<div class="alert alert-info" role="alert">
						Nothing to see here! Why not <a class="alert-link"
							href="${pageContext.request.contextPath}/addtower">add a
							tower?</a>
					</div>
				</c:if>
				<table class="table">
					<thead>
						<tr>
							<th>TowerID</th>
							<th>DoveID</th>
							<th>lat</th>
							<th>long</th>
							<th></th>
						</tr>
					</thead>


					<c:forEach var="row" items="${towers}">

						<tr>
							<td>${row.towerId}</td>
							<td>${row.doveId}</td>
							<td>${row.latitude}</td>
							<td>${row.longitude}</td>
							<td><a class="btn btn-xs btn-default"
								href="${pageContext.request.contextPath}/edittower?t=${row.towerId}">Edit</a></td>
						</tr>

					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<a class="btn btn-xs btn-default"
					href="${pageContext.request.contextPath}/addtower">Add a Tower</a>
			</div>
		</div>
	</div>
</body>
</html>