<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-xs-12 col-md-4 text-center"
		style="background-color: black;">
		<img src="${pageContext.request.contextPath}/static/img/cathedral.jpg" />
		
	</div>
	<div class="col-xs-12 col-md-8">
		<table class="table">
			<tr>
				<th>Location</th>
				<td>
					<p>
						<strong>${tower.placeName}</strong>
						<c:if test="${not empty tower.placeName2}"> ${tower.placeName2}</c:if>
					</p> 
					<c:if test="${not empty tower.dedication}">
						<p>${tower.dedication}</p> 
					</c:if>
					<c:if test="${not empty tower.placeNameCL}">
						<p>County Lists: ${tower.placeNameCL}</p>
					</c:if>
				</td>
			</tr>
			<c:if test="${tower.latitude ne 0.0 && tower.longitude ne 0.0}">
				<tr>
					<th>Co-Ordinates</th>
					<td>${tower.latitude},  ${tower.longitude}</td>
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
			<c:if test="${not empty tower.countyId}">
				<tr>
					<th>County</th>
					<td>${tower.countyId}</td>
				</tr>
			</c:if>
			<c:if test="${not empty tower.countryId}">
				<tr>
					<th>Country</th>
					<td>${tower.countryId}</td>
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
					<td>${tower.satNavLatitude},  ${tower.satNavLongitude}</td>
				</tr>
			</c:if>
			<tr>
				<th>View on Dove's Guide</th>
				<td><a href="//dove.cccbr.org.uk/detail.php?DoveID=${tower.doveId}">View</a></td>
			</tr>
		</table>
	</div>
</div>