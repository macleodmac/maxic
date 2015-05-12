<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/users/doadd"
		modelAttribute="user" commandName="user">

		<div class="row">
			<div class="col-xs-6">
			</div>
			<div class="col-xs-6 text-right">
				<div class="form-group">
					<button type="submit" class="btn btn-primary">Save Changes</button>
				</div>
			</div>
		</div>

		<div class="row">
			<hr />
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-3">
				<div class="form-group">
					<label for="name">Name</label>
					<sf:input type="text" class="form-control" path="name" id="name"
						placeholder="Name" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-3">
				<div class="form-group">
					<label for="email">Email</label>
					<sf:input type="text" class="form-control" path="email" id="email"
						placeholder="Email" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-3">
				<div class="form-group">
					<label for="role">Role</label>
					<sf:select path="role" class="form-control">
						<sf:option value="ROLE_USER">ROLE_USER</sf:option>
						<sf:option value="ROLE_CAPTAIN">ROLE_CAPTAIN</sf:option>
						<sf:option value="ROLE_ADMIN">ROLE_ADMIN</sf:option>
					</sf:select>
				</div>
			</div>
			

		</div>
	</sf:form>
</div>