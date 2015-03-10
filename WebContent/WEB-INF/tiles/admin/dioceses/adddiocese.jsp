<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/dioceses/doadd"
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
					<button type="submit" class="btn btn-default">Add Diocese</button>
				</div>
			</div>
		</div>

		<div class="row">
			<hr />
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-2">
				<div class="form-group">
					<label for="dioceseId">Short Name</label>
					<sf:input type="text" class="form-control" path="dioceseId"
						id="dioceseId" placeholder="Diocese Short Name" />
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
</div>