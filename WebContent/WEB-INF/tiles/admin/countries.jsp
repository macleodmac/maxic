<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<div class="row">
		<div class="col-xs-12">
			<c:if test="${not empty message }">
				<div class="alert alert-info" role="alert">
					<c:out value="${message}"></c:out>
				</div>
			</c:if>
			<c:if test="${empty countries}">
				<div class="alert alert-info" role="alert">
					Nothing to see here! Why not <a class="alert-link"
						href="${pageContext.request.contextPath}/admin/countries/add">add
						a country?</a>
				</div>
			</c:if>
			<table class="table">
				<thead>
					<tr>
						<th>ISO Code</th>
						<th>Name</th>
						<th></th>
					</tr>
				</thead>

				<tfoot>
					<tr>
						<th>ISO Code</th>
						<th>Name</th>
						<th></th>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach var="country" items="${countries}">
						<tr>
							<td>${country.isoCode}</td>
							<td>${country.name}</td>
							<td><a class="btn btn-xs btn-default"
								href="${pageContext.request.contextPath}/admin/countries/edit?c=${country.isoCode}">Edit</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<a class="btn btn-default"
				href="${pageContext.request.contextPath}/admin/countries/add">Add a
				Country</a>
		</div>
	</div>
</div>