<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
			<div class="col-xs-6">
				<sf:form method="post"
					action="${pageContext.request.contextPath}/doadd"
					commandName="tower">
					<div class="form-group">
						<label for="doveId">Dove ID</label> <input type="text"
							class="form-control" id="doveId" placeholder="Enter Dove ID" />
					</div>
					<div class="form-group">
						<label for="latitude">Latitude</label> <input type="text"
							class="form-control" id="latitude" placeholder="Enter Latitude" />
					</div>
					<div class="form-group">
						<label for="longitude">Longitude</label> <input type="text"
							class="form-control" id="longitude" placeholder="Enter Longitude" />
					</div>
					<button type="submit" class="btn btn-default">Add Tower</button>
				</sf:form>
			</div>

		</div>
	</div>
</body>
</html>