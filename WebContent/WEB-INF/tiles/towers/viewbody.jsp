<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table">
	<tr>
		<th>Tower Finder ID</th>
		<td>${tower.towerId}</td>
	</tr>
	<tr>
		<th>Location</th>
		<td>
			<p>
				<strong>${tower.placeName}</strong>
			</p> <c:if test="${not empty tower.placeName2}">
				<p>${tower.placeName2}</p>
			</c:if> <c:if test="${not empty tower.dedication}">
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
	<c:if test="${not empty tower.countyId}">
		<tr>
			<th>County</th>
			<td>${tower.countyId}</td>
		</tr>
	</c:if>
	<c:if
		test="${tower.satNavLatitude ne 0.0 && tower.satNavLongitude ne 0.0}">
		<tr>
			<th>Sat Nav Location</th>
			<td>${tower.satNavLatitude},${tower.satNavLongitude}</td>
		</tr>
	</c:if>
	<tr>
		<th>Dove ID</th>
		<td>${tower.doveId}</td>
	</tr>
</table>