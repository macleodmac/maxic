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
			<c:if test="${empty dioceses}">
				<div class="alert alert-info" role="alert">
					Nothing to see here! Why not <a class="alert-link"
						href="${pageContext.request.contextPath}/admin/dioceses/add">add
						a diocese?</a>
				</div>
			</c:if>
			<table class="table">
				<thead>
					<tr>
						<th>Diocese Short Name</th>
						<th>Diocese Display Name</th>
						<th></th>
					</tr>
				</thead>

				<tfoot>
					<tr>
						<th>Diocese Short Name</th>
						<th>Diocese Display Name</th>
						<th></th>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach var="diocese" items="${dioceses}">
						<tr>
							<td>${diocese.dioceseId}</td>
							<td>${diocese.name}</td>
							<td><a class="btn btn-xs btn-default"
								href="${pageContext.request.contextPath}/admin/dioceses/edit?d=${diocese.dioceseId}">Edit</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<a class="btn btn-default"
				href="${pageContext.request.contextPath}/admin/dioceses/add">Add a
				Diocese</a>
		</div>
	</div>
</div>