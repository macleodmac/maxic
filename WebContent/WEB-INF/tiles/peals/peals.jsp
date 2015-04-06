<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<form class="form-inline">
				<a class="btn btn-primary"
					href="${pageContext.request.contextPath}/peals/add">Add a
					Peal</a> <a class="btn btn-default disabled"
					href="${pageContext.request.contextPath}/admin/peals/add">Filter
					by Tower</a>
				<div class="form-group">
					<label for="exampleInputName2">From</label> <input type="date"
						class="form-control" id="exampleInputName2" placeholder="Date" disabled>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail2">to</label> <input type="date"
						class="form-control" id="exampleInputEmail2" placeholder="Date" disabled>
				</div>
				<button type="submit" class="btn btn-default">Filter By
					Date</button>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<hr />
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<table class="table">
				<thead>
					<tr>
						<th>dateRung</th>
						<th>towerId</th>
						<th>Changes</th>
						<th>method</th>
						<th>view</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="peal" items="${peals}">
						<tr>
							<td>${peal.dateRung}</td>
							<td>${peal.towerId}</td>
							<td>${peal.changes}</td>
							<td>${peal.method}</td>
							<td><a class="btn btn-xs btn-default"
								href="${pageContext.request.contextPath}/peals/view?p=${peal.pealId}">View</a>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<a class="btn btn-xs btn-default"
										href="${pageContext.request.contextPath}/admin/peals/edit?p=${peal.pealId}">Edit</a>
								</sec:authorize></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
