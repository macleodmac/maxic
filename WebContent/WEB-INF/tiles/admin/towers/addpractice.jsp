<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/towers/doaddpractice"
		modelAttribute="practice" commandName="practice">

		<div class="row">
			<div class="col-xs-6">
				<div class="form-group">
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/admin/towers/edit?t=${practice.towerId}">Back</a>
				</div>
			</div>
			<div class="col-xs-6 text-right">
				<div class="form-group">
					<button type="submit" class="btn btn-default">Add Practice</button>
				</div>
			</div>
		</div>

		<div class="row">
			<hr />
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="towerId">Tower ID</label>
					<p class="form-control-static">${practice.towerId}</p>
					<sf:input type="hidden" path="towerId" id="towerId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="practiceId">Practice ID</label>
					<p class="form-control-static">${practice.practiceId}</p>
					<sf:input type="hidden" path="practiceId" id="practiceId" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="day">Day</label>
					<sf:input type="text" class="form-control" path="day"
						id="day" placeholder="Day" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="time">Time</label>
					<sf:input type="text" class="form-control" path="time"
						id="time" placeholder="Time" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="regularity">Regularity</label>
					<sf:input type="text" class="form-control" path="regularity"
						id="regularity" placeholder="regularity" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="visitorsWelcome">Visitors Welcome?</label>
					<sf:input type="text" class="form-control" path="visitorsWelcome"
						id="visitorsWelcome" placeholder="Visitors?" />
				</div>
			</div>
		</div>
	</sf:form>
</div>