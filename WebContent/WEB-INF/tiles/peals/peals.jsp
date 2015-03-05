<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<table class="table">
				<thead>
					<tr>
						<th>PealId</th>
						<th>dateRung</th>
						<th>towerId</th>
						<th>Changes</th>
						<th>method</th>
						<th>view</th>
					</tr>
				</thead>

				<tfoot>
					<tr>
						<th>PealId</th>
						<th>dateRung</th>
						<th>towerId</th>
						<th>Changes</th>
						<th>method</th>
						<th>view</th>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach var="peal" items="${peals}">
						<tr>
							<td>${peal.pealId}</td>
							<td>${peal.dateRung}</td>
							<td>${peal.towerId}</td>
							<td>${peal.changes}</td>
							<td>${peal.method}</td>
							<td><a class="btn btn-xs btn-default"
								href="${pageContext.request.contextPath}/peals/view?p=${peal.pealId}">View</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>