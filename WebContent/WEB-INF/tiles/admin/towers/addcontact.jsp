<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/towers/doaddcontact"
		modelAttribute="contact" commandName="contact">

		<div class="row">
			<div class="col-xs-12 col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-6">
				<c:if test="${not empty message }">
					<div class="alert alert-success" role="alert" style="margin-bottom: 0px;">
						<c:out value="${message}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty dangerMessage }">
					<div class="alert alert-danger" role="alert" style="margin-bottom: 0px;">
						<c:out value="${dangerMessage}"></c:out>
					</div>
				</c:if>
			</div>
			<div class="col-xs-12 col-sm-3 text-right">
				<div class="form-group">

					<button type="submit" class="btn btn-primary">Add Contact Details</button>
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
					<sf:select class="form-control" path="type" id="type"
						items="${contactTypes}" required="true" />

				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="form-group">
					<label for="detail">Contact Details</label>
					<sf:input type="text" class="form-control" path="detail"
						id="detail" placeholder="Detail" required="true" />
				</div>
			</div>
		</div>
	</sf:form>
	<div class="row">
		<div class="col-xs-12">
			<h3>Valid contact types:</h3>
			<ul>
				<li><strong>Phone:</strong> Must be the full phone number, with no spaces. <em>e.g. 01234567890</em></li>
				<li><strong>Email:</strong> Must be a valid email address. <em>e.g. helpme@towerfinder.com</em></li>
				<li><strong>Website:</strong> Must be the full URL including 'http://'. <em>e.g. http://www.towerfinder.com</em></li>
				<li><strong>Twitter:</strong> Must be the username only, excluding '@' symbol <em>e.g. TowerFinder</em></li>
				<li><strong>Facebook:</strong> Must be everything in the group/page URL after www.facebook.com/. <em>e.g. for www.facebook.com/towerFinder it would be towerFinder</em></li>
			</ul>
		</div>
	</div>
</div>