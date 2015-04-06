<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3">
			<h3>Recover your Password</h3>
			<h4>Enter the email address associated with your account</h4>
			<hr />
			<c:if test="${not empty message }">
				<div class="alert alert-danger" role="alert">
					<c:out value="${message}"></c:out>
				</div>
			</c:if>
			<sf:form method="post"
				action="${pageContext.request.contextPath}/recoverpassword"
				modelAttribute="email" commandName="email">
				<div class="form-group">
					<label for="email">Email</label> <input type="text" name="email"
						class="form-control" id="email" placeholder="Email" />
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
				<hr />
					Once submitted, you will receive an email that will provide you
					with a link where you can set a new password.
			</sf:form>
		</div>
	</div>
</div>