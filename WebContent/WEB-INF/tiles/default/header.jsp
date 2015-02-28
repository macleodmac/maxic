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
				<li class="active"><a href="#" data-ytta-id="-">Towers</a></li>
				<li><a href="#" data-ytta-id="-">Peals</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"
					data-ytta-id="-">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" data-ytta-id="-">Action</a></li>
						<li><a href="#" data-ytta-id="-">Another action</a></li>
						<li><a href="#" data-ytta-id="-">Something else here</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Nav header</li>
						<li><a href="#" data-ytta-id="-">Separated link</a></li>
						<li><a href="#" data-ytta-id="-">One more separated link</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" data-ytta-id="-">About</a></li>
				<li><a href="#" data-ytta-id="-">Login</a></li>
			</ul>
			<!-- Button trigger modal 
			<button type="button" data-toggle="modal" data-target="#myModal">
			  Launch demo modal
			</button>-->

			<!-- Modal -->

		</div>
		<!--/.nav-collapse -->
	</div>
</nav>