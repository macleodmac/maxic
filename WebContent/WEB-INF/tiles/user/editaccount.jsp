<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function onLoad() {
		$('#password').keyup(checkMatch);
		$('#confirmpass').keyup(checkMatch);
		$('#currentpass').keyup(checkMatch);
		$('#newEmail').keyup(checkMatch);
		$('#newName').keyup(checkMatch);
		$('#passalert').css('display', 'none');
		$('#editUser')
				.submit(
						function() {

							if ($('#newEmail').val().length > 4) {
								var c = confirm("You will be logged out when your email is changed. Do you wish to continue?");
								return c;
							} else {
								return true;
							}

						});
	}

	function checkMatch() {
		var pass = $('#password').val();
		var cPass = $('#confirmpass').val();
		var currentPass = $('#currentpass').val();
		var newEmail = $('#newEmail').val();
		var newName = $('#newName').val();

		if (pass.length > 4 || cPass.length > 4) {
			if (pass == cPass) {
				$('#matchpass').text("Passwords match.");
				$('#passalert').addClass("alert-success");
				$('#passalert').removeClass("alert-danger");
				$('#passalert').css('display', '');
				if (currentPass.length < 5) {
					$('#submitbtn').prop('disabled', true);
				} else {
					$('#submitbtn').prop('disabled', false);
				}
			} else {
				$('#matchpass').text("Passwords do not match.");
				$('#passalert').addClass("alert-danger");
				$('#passalert').removeClass("alert-success");
				$('#passalert').css('display', '');
			}

		}

		if (newEmail.length > 6) {
			if (currentPass.length < 8) {
				$('#submitbtn').prop('disabled', true);
			} else {
				$('#submitbtn').prop('disabled', false);
			}
		}

		if (newName.length > 4) {
			if (currentPass.length < 8) {
				$('#submitbtn').prop('disabled', true);
			} else {
				$('#submitbtn').prop('disabled', false);
			}
		}

	}

	$(document).ready(onLoad);
</script>
<div class="container">
	<div class="row">
		<div
			class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3">
			<h3>Edit Account Details</h3>
			<p>You can change your email address, your password, or both
				email and password below. You need to enter your current password
				before changes are applied.</p>
			<p>Note: If you change your email address, you will be logged out
				and will be able to login with your new email address.</p>
			<c:if test="${not empty dangerMessage}">
				<div class="alert alert-danger" role="alert">${dangerMessage}</div>
			</c:if>
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success" role="alert">${successMessage}</div>
			</c:if>
			<hr />
		</div>
	</div>

	<sf:form method="post"
		action="${pageContext.request.contextPath}/account/doedit"
		modelAttribute="editUser" commandName="editUser" id="editUser">

		<div class="row">
			<div
				class="col-xs-12 col-sm-5 col-sm-offset-1 col-md-3 col-md-offset-3">
				<div class="form-group">
					<label for="email">Current Email Address</label>
					<p class="form-control-static">${editUser.user.email}</p>
					<sf:input type="hidden" path="user.email" />
					<sf:input type="hidden" path="user.enabled" />
					<sf:input type="hidden" path="user.role" />
					<sf:input type="hidden" path="user.id" />
					<sf:input type="hidden" path="user.name" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-5 col-md-3">
				<div class="form-group">
					<label for="email">Current Name</label>
					<p class="form-control-static">${editUser.user.name}</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div
				class="col-xs-12 col-sm-5 col-sm-offset-1 col-md-3 col-md-offset-3">
				<div class="form-group">
					<label for="newEmail">New Email Address (optional)</label>
					<sf:input type="text" class="form-control" path="newEmail"
						id="newEmail" placeholder="New Email Address" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-5 col-md-3">
				<div class="form-group">
					<label for="newName">New Name (optional)</label>
					<sf:input type="text" class="form-control" path="newName"
						id="newName" placeholder="New Name" />
				</div>
			</div>
		</div>
		<div class="row">
			<div
				class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3">
				<div class="alert" id="passalert" role="alert">
					<div id="matchpass"></div>
					<div id="passlength"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div
				class="col-xs-12 col-sm-5 col-sm-offset-1 col-md-3 col-md-offset-3">
				<div class="form-group">
					<label for="password">New Password (optional)</label>
					<sf:input type="password" class="form-control" path="newPassword"
						id="password" placeholder="New Password" />
				</div>
			</div>
			<div class="col-xs-12 col-sm-5 col-md-3">
				<div class="form-group">
					<label for="confirmpass">Confirm New Password</label> <input
						type="password" class="form-control" id="confirmpass"
						placeholder="Confirm New Password" />
				</div>
			</div>
		</div>
		<div class="row">
			<div
				class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3">
				<hr />
			</div>
		</div>
		<div class="row">
			<div
				class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3">
				<h3>Confirm Changes</h3>
				<p>Please enter your current password to confirm the changes to
					your account.</p>
				<div class="form-group">
					<label for="currentpass">Current Password</label>
					<sf:input type="password" class="form-control" id="currentpass"
						placeholder="Current Password" path="user.password" />
				</div>
				<button type="submit" id="submitbtn" class="btn btn-default"
					disabled>Submit</button>
			</div>
		</div>
	</sf:form>
</div>
