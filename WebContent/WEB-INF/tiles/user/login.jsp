<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3">
		<h3>Login with Email and Password</h3>
		<c:if test="${param.e != null }">
			<div class="alert alert-danger" role="alert">Login failed.
				Check that your email and password are correct, and that you have verified your account.</div>
		</c:if>

		<form name='f'
			action='${pageContext.request.contextPath}/j_spring_security_check'
			method='POST'>
			<div class="form-group">
				<label for="username">Email</label> <input type="text"
					name="j_username" class="form-control" id="username"
					placeholder="Username" />
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					name="j_password" class="form-control" id="username"
					placeholder="Password" />
			</div>
			<div class="form-group">
				<label> <input type="checkbox"
					name="_spring_security_remember_me" /> Remember Me
				</label>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>

		</form>
		<hr />
		<div class="pull-left">
			<a href='${pageContext.request.contextPath}/newaccount'>Create a
				new account</a>
		</div>
		<div class="pull-right">
			<a href='${pageContext.request.contextPath}/forgotpassword'>Forgotten
				your Password?</a>
		</div>
	</div>
</div>