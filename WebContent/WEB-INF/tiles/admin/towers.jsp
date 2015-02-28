<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					href="${pageContext.request.contextPath}/admin/towers/add">add
					a tower?</a>
			</div>
		</c:if>
		<table class="table" data-height="400" data-side-pagination="server"
			data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]">
			<thead>
				<tr>
					<th>TowerID</th>
					<th>DoveID</th>
					<th>Latitude</th>
					<th>Longitude</th>
					<th></th>
				</tr>
			</thead>

			<tfoot>
				<tr>
					<th>TowerID</th>
					<th>DoveID</th>
					<th>Latitude</th>
					<th>Longitude</th>
					<th></th>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach var="row" items="${towers}">
					<tr>
						<td>${row.towerId}</td>
						<td>${row.doveId}</td>
						<td>${row.latitude}</td>
						<td>${row.longitude}</td>
						<td><a class="btn btn-xs btn-default"
							href="${pageContext.request.contextPath}/admin/towers/edit?t=${row.towerId}">Edit</a></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<a class="btn btn-default"
			href="${pageContext.request.contextPath}/admin/towers/add">Add a
			Tower</a>
	</div>
</div>