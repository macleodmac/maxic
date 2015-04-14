<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-md-8 col-md-offset-2">
			<c:if test="${not empty successMessage}">
				<div class="alert alert-success" role="alert">${successMessage}</div>
			</c:if>
			<div class="jumbotron">
				<h2>You are now logged out.</h2>
				<hr />
				You can <a href='${pageContext.request.contextPath}/'>return to
					the home page</a> or <a href='${pageContext.request.contextPath}/login'>log
					in again.</a>
			</div>

		</div>
	</div>
</div>