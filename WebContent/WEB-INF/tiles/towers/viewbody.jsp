<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-xs-12 col-sm-6">
		<h3>News and Information</h3>
		<table class="table">
			<tr>
				<th>Info</th>
				<td>Sample Info</td>
			</tr>
			<tr>
				<th>News</th>
				<td>Sample News</td>
			</tr>
		</table>
	</div>
	<div class="col-xs-12 col-sm-6">
		<h3>Practice Details</h3>
		<c:if test="${not empty towerWrapper.practiceList}">
			<table class="table">
				<c:forEach var="practice" items="${towerWrapper.practiceList}">
					<tr>
						<th>Day</th>
						<td>${practice.day}</td>
					</tr>
					<tr>
						<th>Time</th>
						<td>${practice.time}</td>
					</tr>
					<tr>
						<th>Regularity</th>
						<td>${practice.regularity}</td>
					</tr>
					<tr>
						<th>Visitors welcome</th>
						<c:if test="${practice.visitorsWelcome}">
							<td>Yes</td>
						</c:if>
						<c:if test="${not practice.visitorsWelcome}">
							<td>No</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-6">
		<h3>Latest Performances</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Date</th>
					<th>Details</th>
				</tr>
			</thead>
			<tr>
				<td>Peal1</td>
				<td>Peal1 Details</td>
			</tr>
		</table>
	</div>
	<div class="col-xs-12 col-sm-6">
		<h3>Contact Details</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Type</th>
					<th>Details</th>
				</tr>
			</thead>
			<tr>
				<td>Number</td>
				<td>01442 567890</td>
			</tr>
		</table>
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
	<c:forEach var="practice" items="${tower.practices}">
		<tr>
			<td>${practice.towerId}</td>
			<td>${practice.regularity}</td>
		</tr>
	</c:forEach>
</table> -->