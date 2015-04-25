<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-inverse navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/"
				data-ytta-id="-">Tower Finder</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/towers"
					data-ytta-id="-">Towers</a></li>
				<li><a href="${pageContext.request.contextPath}/peals?t=all"
					data-ytta-id="-">Peals</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/about"
					data-ytta-id="-">About</a></li>
				<sec:authorize access="isAnonymous()">
					<li><a href="${pageContext.request.contextPath}/login"
						data-ytta-id="-">Login</a></li>
					<li><a href="${pageContext.request.contextPath}/newaccount"
						data-ytta-id="-">Create Account</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"
						data-ytta-id="-">Admin<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li class="dropdown-header">Administration</li>
							<li><a
								href="${pageContext.request.contextPath}/admin/towers"
								data-ytta-id="-">Towers</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/peals"
								data-ytta-id="-">Peals</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/countries"
								data-ytta-id="-">Countries</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/dioceses"
								data-ytta-id="-">Dioceses</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/users"
								data-ytta-id="-">Users</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/messages"
								data-ytta-id="-">Messages</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/dove"
								data-ytta-id="-">Update DB</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Help</li>
							<li><a
								href="${pageContext.request.contextPath}/admin/manual"
								data-ytta-id="-">Manual</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/documentation"
								data-ytta-id="-">Documentation</a></li>
						</ul></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication var="user" property="principal" />
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"
						data-ytta-id="-">Account<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li class="dropdown-header">Account</li>
							<li><a
								href="${pageContext.request.contextPath}/account/visits"
								data-ytta-id="-">My Visits</a></li>
							<li><a
								href="${pageContext.request.contextPath}/account/edit"
								data-ytta-id="-">Edit Details</a></li>
							<sec:authorize access="hasRole('ROLE_CAPTAIN')">
								<li class="divider"></li>
								<li class="dropdown-header">Tower Captain</li>

								<li><a
									href="${pageContext.request.contextPath}/captain/edit"
									data-ytta-id="-">Edit Tower</a></li>
							</sec:authorize>
							<li class="divider"></li>
							<li class="dropdown-header">Help</li>
							<li><a
								href="${pageContext.request.contextPath}/admin/manual"
								data-ytta-id="-">Manual</a></li>
						</ul></li>
					<li><a
						href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>