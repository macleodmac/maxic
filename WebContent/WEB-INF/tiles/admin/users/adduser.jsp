<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/users/doadd"
		modelAttribute="user" commandName="user">

		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/admin/users">Back</a>
				</div>
			</div>
			<div class="col-xs-6 text-right">
				<div class="form-group">
					<button type="submit" class="btn btn-default">Add User</button>
				</div>
			</div>
		</div>

		<div class="row">
			<hr />
		</div>
		<div class="row">
			<div class="col-xs-10 col-sm-4">
				<div class="form-group">
					<label for="type">Email</label>
					<sf:input type="text" class="form-control" path="email" id="email"
						placeholder="Email" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="form-group">
					<label for="detail">Role</label>
					<sf:select path="role" class="form-control">
						<sf:option value="ROLE_USER">ROLE_USER</sf:option>
						<sf:option value="ROLE_EDITOR">ROLE_EDITOR</sf:option>
						<sf:option value="ROLE_ADMIN">ROLE_ADMIN</sf:option>
					</sf:select>
				</div>
			</div>
		</div>
	</sf:form>
</div>