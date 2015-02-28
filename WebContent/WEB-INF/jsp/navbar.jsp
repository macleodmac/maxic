<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#" data-ytta-id="-">Tower Finder</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#" data-ytta-id="-">Towers</a></li>
            <li><a href="#" data-ytta-id="-">Peals</a></li>
			<li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" data-ytta-id="-">Dropdown <span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="#" data-ytta-id="-">Action</a></li>
                    <li><a href="#" data-ytta-id="-">Another action</a></li>
                    <li><a href="#" data-ytta-id="-">Something else here</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header">Nav header</li>
                    <li><a href="#" data-ytta-id="-">Separated link</a></li>
                    <li><a href="#" data-ytta-id="-">One more separated link</a></li>
                  </ul>
                </li>
          </ul>
		  <ul class="nav navbar-nav navbar-right">
            <li><a href="#" data-ytta-id="-">About</a></li>
            <li><a href="#" data-ytta-id="-">Login</a></li>
          </ul>
		  <!-- Button trigger modal 
			<button type="button" data-toggle="modal" data-target="#myModal">
			  Launch demo modal
			</button>-->

			<!-- Modal -->
			
        </div><!--/.nav-collapse -->
      </div>
    </nav>
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
				<c:if test="${empty peals}">
					<div class="alert alert-info" role="alert">
						Nothing to see here! Why not <a class="alert-link"
							href="${pageContext.request.contextPath}/admin/peals/add">add a
							peal?</a>
					</div>
				</c:if>
				<table class="table" data-height="400" data-side-pagination="server" data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]">
					<thead>
						<tr>
							<th>PealId</th>
							<th>dateRung</th>
							<th>method</th>
							<th>towerId</th>
							<th></th>
						</tr>
					</thead>

					<tfoot>
						<tr>
							<th>PealId</th>
							<th>dateRung</th>
							<th>method</th>
							<th>towerId</th>
							<th></th>
						</tr>
					</tfoot>
					<tbody>
						<c:forEach var="row" items="${peals}">
							<tr>
								<td>${row.pealId}</td>
								<td>${row.dateRung}</td>
								<td>${row.method}</td>
								<td>${row.towerId}</td>
								<td><a class="btn btn-xs btn-default"
									href="${pageContext.request.contextPath}/admin/peals/edit?t=${row.pealId}">Edit</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<a class="btn btn-default"
					href="${pageContext.request.contextPath}/admin/peals/add">Add a Peal</a>
			</div>
		</div>
	</div>
</body>
</html>