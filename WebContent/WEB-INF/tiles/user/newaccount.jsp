<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
	function onLoad() {
		$('#password').keyup(checkMatch);
		$('#confirmpass').keyup(checkMatch);
		$('#email').keyup(checkMatch);
		$('#passalert').css('display', 'none');
	}

	function checkMatch() {
		var pass = $('#password').val();
		var cPass = $('#confirmpass').val();
		var email = $('#email').val();
		
		if (pass.length > 4 || cPass.length > 4) {
			if (pass == cPass) {
				$('#matchpass').text("Passwords match.");
				$('#passalert').addClass("alert-success");
				$('#passalert').removeClass("alert-danger");
				$('#passalert').css('display', '');
				if (email.length > 6) {
					$('#submitbtn').prop('disabled', false);
				}
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
		<h3>Create an Account</h3>

		<sf:form method="post"
			action="${pageContext.request.contextPath}/createaccount"
			modelAttribute="user" commandName="user">

			<c:if test="${not empty message }">
				<div class="alert alert-danger" role="alert">${message}</div>
			</c:if>
			<sf:errors path="email" cssClass="alert alert-danger" element="div" />
			<sf:errors path="password" cssClass="alert alert-danger"
				element="div" />
				<div class="form-group">
				<label for="email">Display Name</label>
				<sf:input type="text" class="form-control" path="name" id="name"
					placeholder="Name" required="true" />
			</div>
			<div class="form-group">
				<label for="email">Email</label>
				<sf:input type="text" class="form-control" path="email" id="email"
					placeholder="Email" required="true" />
				 <p class="help-block">Your email will act as your login for the website.</p>
			</div>
			<div class="form-group">
				<label for="email">Password</label>
				<sf:input type="password" class="form-control" path="password"
					id="password" placeholder="Password" required="true" />
				 <p class="help-block">Your password must be at least 8 characters long.</p>
			</div>
			<div class="form-group">
				<label for="confirmpass">Confirm Password</label> <input
					type="password" class="form-control" id="confirmpass"
					placeholder="Confirm Password" />
			</div>
			<div class="alert" id="passalert" role="alert">
				<div id="matchpass"></div>
				<div id="passlength"></div>
			</div>
			<button type="submit" id="submitbtn" class="btn btn-primary" disabled>Submit</button>
		</sf:form>
	</div>
</div>
