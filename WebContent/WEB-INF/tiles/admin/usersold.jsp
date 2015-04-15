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
						<th>ID</th>
						<th>Email</th>
						<th>Role</th>
						<th>Enabled</th>
						<th></th>
					</tr>
				</thead>

				<tfoot>
					<tr>
						<th>ID</th>
						<th>Email</th>
						<th>Role</th>
						<th>Enabled</th>
						<th></th>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.id}</td>
							<td>${user.email}</td>
							<td>${user.role}</td>
							<td>${user.enabled}</td>
							<td><a class="btn btn-xs btn-default"
								href="${pageContext.request.contextPath}/admin/users/edit?u=${user.id}">Edit</a> <a
								class="btn btn-xs btn-default"
								href="${pageContext.request.contextPath}/admin/users/reset?u=${user.email}">Reset</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<a class="btn btn-default"
				href="${pageContext.request.contextPath}/admin/users/add">Create
				a User</a>
		</div>
	</div>
</div>