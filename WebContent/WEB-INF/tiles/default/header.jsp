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
				data-ytta-id="-"> <img id="mainlogo"
             src="${pageContext.request.contextPath}/static/img/logo.png"></a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/towers"
					data-ytta-id="-">towers</a></li>
				<li><a href="${pageContext.request.contextPath}/peals"
					data-ytta-id="-">performances</a></li>
				<li><a href="${pageContext.request.contextPath}/about"
					data-ytta-id="-">about</a></li>
				<li><a href="${pageContext.request.contextPath}/help"
					data-ytta-id="-">help</a></li>
				<sec:authorize access="isAnonymous()">
					<li><a href="${pageContext.request.contextPath}/login"
						data-ytta-id="-">login</a></li>
					<li><a href="${pageContext.request.contextPath}/newaccount"
						data-ytta-id="-">signup</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"
						data-ytta-id="-">admin<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li class="dropdown-header">administration</li>
							<li><a
								href="${pageContext.request.contextPath}/admin/towers"
								data-ytta-id="-">towers</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/peals"
								data-ytta-id="-">performances</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/countries"
								data-ytta-id="-">countries</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/dioceses"
								data-ytta-id="-">dioceses</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/users"
								data-ytta-id="-">users</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/messages"
								data-ytta-id="-">messages</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/dove"
								data-ytta-id="-">database</a></li>
						</ul></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication var="user" property="principal" />
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"
						data-ytta-id="-">account<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li class="dropdown-header">account</li>
							<li><a
								href="${pageContext.request.contextPath}/account/visits"
								data-ytta-id="-">my visits</a></li>
							<li><a
								href="${pageContext.request.contextPath}/account/edit"
								data-ytta-id="-">edit details</a></li>
							<sec:authorize access="hasRole('ROLE_CAPTAIN')">
								<li class="divider"></li>
								<li class="dropdown-header">tower captain</li>

								<li><a
									href="${pageContext.request.contextPath}/captain/edit"
									data-ytta-id="-">edit tower</a></li>
							</sec:authorize>
						</ul></li>
					<li><a
						href="${pageContext.request.contextPath}/j_spring_security_logout">logout</a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>