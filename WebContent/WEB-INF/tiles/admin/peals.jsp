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
			<c:if test="${empty peals}">
				<div class="alert alert-info" role="alert">
					Nothing to see here! Why not <a class="alert-link"
						href="${pageContext.request.contextPath}/admin/peals/add">add
						a peal?</a>
				</div>
			</c:if>
			<table class="table" data-height="400" data-side-pagination="server"
				data-pagination="true" data-page-list="[5, 10, 20, 50, 100, 200]">
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
				href="${pageContext.request.contextPath}/admin/peals/add">Add a
				Peal</a>
		</div>
	</div>
</div>