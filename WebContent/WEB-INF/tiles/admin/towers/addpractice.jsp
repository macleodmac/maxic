<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/admin/towers/doaddpractice"
		modelAttribute="practice" commandName="practice">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
			</div>
			<div class="col-xs-12 col-sm-6">
				<c:if test="${not empty message }">
					<div class="alert alert-success" role="alert"
						style="margin-bottom: 0px;">
						<c:out value="${message}"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty dangerMessage }">
					<div class="alert alert-danger" role="alert"
						style="margin-bottom: 0px;">
						<c:out value="${dangerMessage}"></c:out>
					</div>
				</c:if>
			</div>
			<div class="col-xs-12 col-sm-3 text-right">
				<div class="form-group">

					<button type="submit" class="btn btn-primary">Save Changes</button>
				</div>
			</div>
		</div>
		<div class="row">
			<hr />
		</div>
		<div class="row">
			<div class="col-xs-2 col-sm-4">
				<div class="form-group">
					<label for="towerId">Tower</label>
					<p class="form-control-static">${practice.towerId}</p>
					<sf:input type="hidden" path="towerId" id="towerId" />
					<sf:input type="hidden" path="practiceId" id="practiceId" />
				</div>
			</div>
			<div class="col-xs-10 col-sm-4">
				<div class="form-group">
					<label for="practiceName">Practice Name</label>
					<sf:input type="text" class="form-control" path="practiceName"
						id="practiceName" placeholder="Name" required="true" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="day">Day</label>
					<sf:select class="form-control" path="day" id="day" items="${days}" required="true" />

				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="time">Time</label>
					<sf:input type="text" class="form-control" path="time" id="time"
						placeholder="Time" required="true" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="regularity">Regularity</label>
					<sf:input type="text" class="form-control" path="regularity"
						id="regularity" placeholder="Regularity" required="true" />
				</div>
			</div>
			<div class="col-xs-6 col-sm-4">
				<div class="form-group">
					<label for="visitorsWelcome">Visitors Welcome?</label>

					<sf:select class="form-control" path="visitorsWelcome"
						id="visitorsWelcome" items="${yesno}" required="true" />
				</div>
			</div>
		</div>
	</sf:form>
	<div class="row">
		<div class="col-xs-12">
			<h3>Help:</h3>
			<ul>
				<li><strong>Practice Name:</strong> An identifying name for the practice. <em>e.g. General or Beginner or Advanced</em></li>
				<li><strong>Time:</strong> Must be in 24hr clock format<em>e.g. 19:00:00</em></li>
				<li><strong>Regularity:</strong> How often the practice occurs. <em>e.g. Weekly or Fortnightly</em></li>
				<li><strong>Visitors Welcome:</strong> Are visitors welcome to turn up?
			</ul>
		</div>
	</div>
</div>