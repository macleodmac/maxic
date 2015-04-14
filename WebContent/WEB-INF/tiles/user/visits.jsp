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
			<table class="table">
				<thead>
					<tr>
						<th>Tower</th>
						<th>Date</th>
						<th>Rung</th>
						<th>QP Rung</th>
						<th>Peal Rung</th>
						<th>Notes</th>
						<th></th>
					</tr>
				</thead>

				<tfoot>
					<tr>
						<th>Tower</th>
						<th>Date</th>
						<th>Rung</th>
						<th>QP Rung</th>
						<th>Peal Rung</th>
						<th>Notes</th>
						<th></th>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach var="visit" items="${visits}">
						<tr>
							<td>${visit.towerId}</td>
							<td>${visit.date}</td>
							<td>${visit.rung}</td>
							<td>${visit.quarterPealRung}</td>
							<td>${visit.pealRung}</td>
							<td>${visit.notes}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<a class="btn btn-default"
				href="${pageContext.request.contextPath}/account/visits/add?t=0">Add a Visit</a>
		</div>
	</div>
</div>