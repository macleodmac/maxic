<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/towers/doaddcontact"
		modelAttribute="contact" commandName="contact">

		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/admin/towers/edit?t=${contact.towerId}">Back</a>
				</div>
			</div>
			<div class="col-xs-6 text-right">
				<div class="form-group">
					<button type="submit" class="btn btn-default">Add Contact Details</button>
				</div>
			</div>
		</div>

		<div class="row">
			<hr />
		</div>
		<div class="row">
			<div class="col-xs-2 col-sm-4">
				<div class="form-group">
					<label for="towerId">Tower</label>
					<p class="form-control-static">${contact.towerId}</p>
					<sf:input type="hidden" path="towerId" id="towerId" />
					<sf:input type="hidden" path="contactId" id="contactId" />
				</div>
			</div>
			<div class="col-xs-10 col-sm-4">
				<div class="form-group">
					<label for="type">Contact Type</label>
					<sf:input type="text" class="form-control" path="type"
						id="type" placeholder="Type" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="form-group">
					<label for="detail">Contact Details</label>
					<sf:input type="text" class="form-control" path="detail"
						id="detail" placeholder="Detail" />
				</div>
			</div>
		</div>
	</sf:form>
</div>