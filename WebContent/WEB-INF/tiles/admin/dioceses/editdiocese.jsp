<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/dioceses/doedit?d=${diocese.dioceseId}"
		modelAttribute="diocese" commandName="diocese">


		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/admin/dioceses">Back</a>
				</div>
			</div>
			<div class="col-xs-6 text-right">
				<div class="form-group">
					<button type="submit" class="btn btn-default">Save Changes</button>
				</div>
			</div>
		</div>

		<div class="row">
			<hr />
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-4">
				<div class="form-group">
					<label for="dioceseId">Diocese Short Name</label>
					<p class="form-control-static" >${diocese.dioceseId}</p>
					<sf:input type="hidden" path="dioceseId" id="dioceseId"></sf:input>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="form-group">
					<label for="name">Display Name</label>
					<sf:input type="text" class="form-control" path="name" id="name" placeholder="Diocese Display Name" />
				</div>
			</div>
		</div>
	</sf:form>
	<div class="row">
		<div class="col-xs-6">
		</div>
		<div class="col-xs-6 text-right">
			<a class="btn btn-danger"
				href="${pageContext.request.contextPath}/admin/dioceses/dodelete?d=${diocese.dioceseId}">Delete</a>
		</div>
	</div>
</div>
