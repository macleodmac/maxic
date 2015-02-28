<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			<a class="navbar-brand" href="${pageContext.request.contextPath}/" data-ytta-id="-">Tower Finder</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="#" data-ytta-id="-">Towers</a></li>
				<li><a href="#" data-ytta-id="-">Peals</a></li>
				
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" data-ytta-id="-">About</a></li>
				<li><a href="#" data-ytta-id="-">Login</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"
					data-ytta-id="-">Admin<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li class="dropdown-header">Administration</li>
						<li><a href="${pageContext.request.contextPath}/admin/dashboard" data-ytta-id="-">Dashboard</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/towers" data-ytta-id="-">Towers</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/peals" data-ytta-id="-">Peals</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/user" data-ytta-id="-">Users</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Help</li>
						<li><a href="#" data-ytta-id="-">Manual</a></li>
						<li><a href="#" data-ytta-id="-">Documentation</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>