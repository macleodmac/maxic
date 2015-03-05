<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script
	src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script>
<script
	src="http://cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<link rel="stylesheet"
	href="http://cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.css">

<script type="text/javascript">
	$(document).ready(function() {
		$('#example').DataTable({
			"ordering" : false
		});
	});
</script>
<div class="container">
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
		</div>
	</div>

	<table id="example" class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Place</th>
				<th></th>
			</tr>
		</thead>

		<tfoot>
			<tr>
				<th>ID</th>
				<th>Place</th>
				<th></th>
			</tr>
		</tfoot>
		<tbody>
			<c:forEach var="tower" items="${towers}">
				<tr>
					<td>${tower.id}</td>
					<td>${tower.de}</td>
					<td><a class="btn btn-xs btn-default"
						href="${pageContext.request.contextPath}/admin/towers/edit?t=${tower.id}">Edit</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="row">
		<div class="col-xs-12">
			<a class="btn btn-default"
				href="${pageContext.request.contextPath}/admin/towers/add">Add a
				Tower</a>
		</div>
	</div>
</div>
<br />