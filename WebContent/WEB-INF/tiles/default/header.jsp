<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--[if lte IE 9]>
  <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6.2/html5shiv.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.3.0/respond.js"></script>
<![endif]--> 
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
				<li><a href="${pageContext.request.contextPath}/peals?t=all" data-ytta-id="-">Peals</a></li>
				
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/about" data-ytta-id="-">About</a></li>
				<li><a href="#" data-ytta-id="-">Login</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"
					data-ytta-id="-">Admin<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li class="dropdown-header">Administration</li>
						<li><a href="${pageContext.request.contextPath}/admin/dashboard" data-ytta-id="-">Dashboard</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/towers" data-ytta-id="-">Towers</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/peals" data-ytta-id="-">Peals</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/countries" data-ytta-id="-">Countries</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/dioceses" data-ytta-id="-">Dioceses</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/user" data-ytta-id="-">Users</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/messages" data-ytta-id="-">Messages</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Help</li>
						<li><a href="${pageContext.request.contextPath}/admin/manual" data-ytta-id="-">Manual</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/documentation" data-ytta-id="-">Documentation</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>