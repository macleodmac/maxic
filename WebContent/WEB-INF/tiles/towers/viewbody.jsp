<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-xs-12 col-sm-6">
		<h4>News and Information</h4>
		<table class="table">
			<tr>
				<th>Ringable</th>
				<c:if test="${towerWrapper.tower.ringable}">
					<td>Yes</td>
				</c:if>
				<c:if test="${not towerWrapper.tower.ringable}">
					<td>No</td>
				</c:if>
			</tr>
			<tr>
				<th>Ground Floor Ring</th>
				<c:if test="${towerWrapper.tower.groundFloorRing}">
					<td>Yes</td>
				</c:if>
				<c:if test="${not towerWrapper.tower.groundFloorRing}">
					<td>No</td>
				</c:if>
			</tr>
			<tr>
				<th>Toilet</th>
				<c:if test="${towerWrapper.tower.toilet}">
					<td>Yes</td>
				</c:if>
				<c:if test="${not towerWrapper.tower.toilet}">
					<td>No</td>
				</c:if>
			</tr>
			
			<c:if test="${not empty towerWrapper.tower.extraInfo}">
				<tr>
					<th>Info</th>
					<td>${towerWrapper.tower.extraInfo}</td>
				</tr>
			</c:if>
			<c:if test="${not empty towerWrapper.tower.accessDetails}">
				<tr>
					<th>Access</th>
					<td>${towerWrapper.tower.accessDetails}</td>
				</tr>
			</c:if>
		</table>
		<h4>
			Latest Performances
			<c:if test="${not empty peals}">
				<a class="btn btn-primary btn-xs pull-right"
					href="${pageContext.request.contextPath}/peals?t=${towerWrapper.tower.towerId}">View
					All</a>
			</c:if>
		</h4>
		<c:if test="${not empty peals}">
			<table class="table">
				<thead>
					<tr>
						<th>Date</th>
						<th>Details</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="peal" items="${peals}">
						<tr>
							<td>${peal.dateRung}</td>
							<td>${peal.changes} ${peal.method}</td>
							<td><a class="btn btn-primary btn-xs pull-right"
								href="${pageContext.request.contextPath}/peals/view?p=${peal.pealId}">View</a></td>
						</tr>
					</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty peals}">
			<div class="alert alert-info" role="alert">No performances
				found.</div>
		</c:if>
	</div>
	<div class="col-xs-12 col-sm-6">
		<h4>Practice Details</h4>
		<c:if test="${not empty towerWrapper.practiceList}">
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Day</th>
						<th>Time</th>
						<th>Frequency</th>
						<th>Visitors?</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="practice" items="${towerWrapper.practiceList}">
						<tr>
							<td>${practice.practiceName}</td>
							<td>${practice.day}</td>
							<td>${practice.time}</td>
							<td>${practice.regularity}</td>
							<c:if test="${practice.visitorsWelcome}">
								<td>Yes</td>
							</c:if>
							<c:if test="${not practice.visitorsWelcome}">
								<td>No</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${empty towerWrapper.practiceList}">
			<div class="alert alert-info" role="alert">No practice
				information held.</div>
		</c:if>
		<h4>Contact Details</h4>
		<c:if test="${not empty towerWrapper.contactDetailsList}">
			<table class="table">
				<thead>
					<tr>
						<th>Type</th>
						<th>Detail</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="contact" items="${towerWrapper.contactDetailsList}">
						<tr>
							<td>${contact.type}</td>
							<td><c:choose>
									<c:when test="${contact.type == 'Website'}">
										<a href="${contact.detail}">Link</a>
									</c:when>
									<c:when test="${contact.type == 'Twitter'}">
										<a href="http://www.twitter.com/${contact.detail}">@${contact.detail}</a>
									</c:when>
									<c:when test="${contact.type == 'Facebook'}">
										<a href="http://www.facebook.com/${contact.detail}">Link</a>
									</c:when>
									<c:otherwise>${contact.detail}</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${empty towerWrapper.contactDetailsList}">
			<div class="alert alert-info" role="alert">No contact details
				held.</div>
		</c:if>
	</div>
</div>