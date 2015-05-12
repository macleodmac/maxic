<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function onLoad() {
		$('#enabled').change(checkEnabled);
		$('#towerCaptain').change(checkTower);
		$('#role').change(checkTower);
		checkEnabled();
		checkTower();
	}

	function checkEnabled() {
		var enabled = $('#enabled').val();
		if (enabled === 'true') {
			$('#enabledalert').css('display', 'none');
		} else {
			$('#enabledalert').css('display', '');
		}

	}

	function checkTower() {
		var towerCaptain = $('#towerCaptain').val();
		var role = $('#role').val();

		if (role === 'ROLE_CAPTAIN') {
			$('#captaindiv').css('display', '');
			if (towerCaptain === '0') {
				$('#toweralert').css('display', '');
			} else {
				$('#toweralert').css('display', 'none');
			}
		} else {
			$('#toweralert').css('display', 'none');
			$('#captaindiv').css('display', 'none');
		}

	}

	$(document).ready(onLoad);
</script>

<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/users/doedit"
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
			<div class="col-xs-12">
				<c:if test="${not empty message }">
					<div class="alert alert-success" role="alert">
						<c:out value="${message}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty dangerMessage }">
					<div class="alert alert-danger" role="alert">
						<c:out value="${dangerMessage}"></c:out>
					</div>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-2">
				<div class="form-group">
					<label for="name">Name</label>

					<sf:input type="text" class="form-control" path="name" id="name"
						placeholder="Name" required="true" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-3">
				<div class="form-group">
					<label for="email">Email</label>
					<sf:input type="text" class="form-control" path="email" id="email"
						placeholder="Email" required="true" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-2">
				<div class="form-group">
					<label for="enabled">Enabled</label>
					<sf:select class="form-control" path="enabled" id="enabled"
						items="${yesno}" required="true" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-2">
				<div class="form-group">
					<label for="role">Role</label>
					<sf:select path="role" class="form-control" required="true">
						<sf:option value="ROLE_USER">ROLE_USER</sf:option>
						<sf:option value="ROLE_CAPTAIN">ROLE_CAPTAIN</sf:option>
						<sf:option value="ROLE_ADMIN">ROLE_ADMIN</sf:option>
					</sf:select>
				</div>
			</div>
			<div class="col-xs-12 col-sm-3" id="captaindiv" >
				<div class="form-group">
					<label for="towerCaptain">Tower Captain</label>
					<sf:select class="form-control" path="towerCaptain"
						id="towerCaptain" items="${towers}" required="true" />
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="alert alert-warning" id="enabledalert" role="alert">If
					a user is not enabled they will not be able to login.</div>
				<div class="alert alert-warning" id="toweralert" role="alert">This
					user has the role ROLE_CAPTAIN but has not been assigned a tower.</div>
			</div>
		</div>
		<sf:input type="hidden" path="id" required="true" />
		<sf:input type="hidden" path="password" required="true" />
	</sf:form>
</div>