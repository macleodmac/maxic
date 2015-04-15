<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function onLoad() {
		$('#password').keyup(checkMatch);
		$('#confirmpass').keyup(checkMatch);
		$('#passalert').css('display', 'none');
	}

	function checkMatch() {
		var pass = $('#password').val();
		var cPass = $('#confirmpass').val();

		if (pass.length > 4 || cPass.length > 4) {
			if (pass == cPass) {
				$('#matchpass').text("Passwords match.");
				$('#passalert').addClass("alert-success");
				$('#passalert').removeClass("alert-danger");
				$('#passalert').css('display', '');
				$('#submitbtn').prop('disabled', false);
			} else {
				$('#matchpass').text("Passwords do not match.");
				$('#passalert').addClass("alert-danger");
				$('#passalert').removeClass("alert-success");
				$('#passalert').css('display', '');
				$('#submitbtn').prop('disabled', true);
			}

		}

	}

	$(document).ready(onLoad);
</script>
<div class="container">
	<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3">
		<h3>Reset Password</h3>
		<sf:form method="post"
			action="${pageContext.request.contextPath}/doreset"
			modelAttribute="user" commandName="user">
			<div class="form-group">
				<label for="name">Email Address</label>
				<p class="form-control-static">${user.email}</p>
				<sf:input type="hidden" path="email" id="email" />
				<sf:input type="hidden" path="name" id="name" />
			</div>
			<div class="form-group">
				<label for="email">Password</label>
				<sf:input type="password" class="form-control" path="password"
					id="password" placeholder="Password" />
			</div>
			<div class="form-group">
				<label for="confirmpass">Confirm Password</label> <input
					type="password" class="form-control" id="confirmpass"
					placeholder="Confirm Password" />
			</div>
			<div class="alert" id="passalert" role="alert">
				<div id="matchpass"></div>
				<div id="passlength"></div>
				<sf:input type="hidden" path="role" id="role" />
				<sf:input type="hidden" path="enabled" id="enabled" />
				<sf:input type="hidden" path="id" id="id" />
			</div>
			<button type="submit" id="submitbtn" class="btn btn-default" disabled>Submit</button>
		</sf:form>
	</div>
</div>
