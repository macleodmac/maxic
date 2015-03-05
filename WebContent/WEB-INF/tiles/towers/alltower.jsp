<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table">
	<tr>
		<th>Tower Finder ID</th>
		<td>${towerAll.tower.towerId}</td>
	</tr>
	<tr>
		<th>Location</th>
		<td>
			<p>
				<strong>${towerAll.tower.placeName}</strong>
			</p> <c:if test="${not empty towerAll.tower.placeName2}">
				<p>${towerAll.tower.placeName2}</p>
			</c:if> <c:if test="${not empty towerAll.tower.dedication}">
				<p>${towerAll.tower.dedication}</p>
			</c:if> <c:if test="${not empty towerAll.tower.placeNameCL}">
				<p>County Lists: ${towerAll.tower.placeNameCL}</p>
			</c:if>
		</td>
	</tr>
	<c:if
		test="${towerAll.tower.latitude ne 0.0 && towerAll.tower.longitude ne 0.0}">
		<tr>
			<th>Co-Ordinates</th>
			<td>${towerAll.tower.latitude},${towerAll.tower.longitude}</td>
		</tr>
	</c:if>
	<c:if test="${not empty towerAll.tower.gridReference}">
		<tr>
			<th>Grid Reference</th>
			<td>${towerAll.tower.gridReference}</td>
		</tr>
	</c:if>
	<c:if test="${not empty towerAll.tower.postCode}">
		<tr>
			<th>Postcode</th>
			<td>${towerAll.tower.postCode}</td>
		</tr>
	</c:if>
	<c:if test="${not empty towerAll.tower.countyId}">
		<tr>
			<th>County</th>
			<td>${towerAll.tower.countyId}</td>
		</tr>
	</c:if>
	<c:if
		test="${towerAll.tower.satNavLatitude ne 0.0 && towerAll.tower.satNavLongitude ne 0.0}">
		<tr>
			<th>Sat Nav Location</th>
			<td>${towerAll.tower.satNavLatitude},
				${towerAll.tower.satNavLongitude}</td>
		</tr>
	</c:if>
	<tr>
		<th>Dove ID</th>
		<td>${towerAll.tower.doveId}</td>
	</tr>

	<c:forEach items="${towerAll.contactDetails}" var="contacts">
		<tr>
			<th>Email Address #${contacts.contactId}</th>
			<td>${contacts.email}</td>
		</tr>
	</c:forEach>

</table>