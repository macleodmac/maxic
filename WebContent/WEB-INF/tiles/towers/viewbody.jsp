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
				<th>Info</th>
				<td>${towerWrapper.tower.extraInfo}</td>
			</tr>
			<tr>
				<th>News</th>
				<td>Sample News</td>
			</tr>
		</table>
		<h4>
			Latest Performances <c:if test="${not empty peals}"><a class="btn btn-default btn-xs pull-right"
				href="${pageContext.request.contextPath}/peals?t=${towerWrapper.tower.towerId}">View All</a></c:if>
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
							<td><a class="btn btn-default btn-xs pull-right" href="${pageContext.request.contextPath}/peals/view?p=${peal.pealId}">View</a></td>
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
						<th></th>
						<th>Day</th>
						<th>Time</th>
						<th></th>
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
							<td>${contact.detail}</td>
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





<!-- <table class="table">
	<tr>
		<th>Location</th>
		<td>
			<p>
				<strong>${tower.placeName}</strong>
				<c:if test="${not empty tower.placeName2}"> ${tower.placeName2}</c:if>
			</p> <c:if test="${not empty tower.dedication}">
				<p>${tower.dedication}</p>
			</c:if> <c:if test="${not empty tower.placeNameCL}">
				<p>County Lists: ${tower.placeNameCL}</p>
			</c:if>
		</td>
	</tr>
	<c:if test="${tower.latitude ne 0.0 && tower.longitude ne 0.0}">
		<tr>
			<th>Co-Ordinates</th>
			<td>${tower.latitude},${tower.longitude}</td>
		</tr>
	</c:if>
	<c:if test="${not empty tower.gridReference}">
		<tr>
			<th>Grid Reference</th>
			<td>${tower.gridReference}</td>
		</tr>
	</c:if>
	<c:if test="${not empty tower.postCode}">
		<tr>
			<th>Postcode</th>
			<td>${tower.postCode}</td>
		</tr>
	</c:if>
	<c:if test="${not empty tower.diocese.name}">
		<tr>
			<th>Diocese</th>
			<td>${tower.diocese.name}</td>
		</tr>
	</c:if>
	<c:if test="${not empty tower.country.isoCode}">
		<tr>
			<th>Country</th>
			<td>${tower.country.isoCode}</td>
		</tr>
	</c:if>
	<c:if test="${not empty tower.guildId}">
		<tr>
			<th>guildId</th>
			<td>${tower.guildId}</td>
		</tr>
	</c:if>

	<c:if test="${not empty tower.listedGrade}">
		<tr>
			<th>listedGrade</th>
			<td>${tower.listedGrade}</td>
		</tr>
	</c:if>
	<tr>
		<th>bells</th>
		<td>bellNumber</td>
	</tr>
	<tr>
		<th>Tenor</th>
		<td>tenorDetails</td>
	</tr>
	<tr>
		<th>AdditionalInfo</th>
		<td>${tower.extraInfo}</td>
	</tr>
	<c:if
		test="${tower.satNavLatitude ne 0.0 && tower.satNavLongitude ne 0.0}">
		<tr>
			<th>Sat Nav Location</th>
			<td>${tower.satNavLatitude},${tower.satNavLongitude}</td>
		</tr>
	</c:if>
</table> -->