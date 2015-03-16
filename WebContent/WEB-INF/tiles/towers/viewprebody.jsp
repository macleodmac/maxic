<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="modal-title">
	<strong>${towerWrapper.tower.dedication}</strong>
	<c:if test="${not empty towerWrapper.tower.placeName}">
		<small> ${towerWrapper.tower.placeName}, </small>
	</c:if>
	<c:if test="${not empty towerWrapper.tower.placeName2}">
		<small>${towerWrapper.tower.placeName2}</small>
	</c:if>
	<small><small> id: ${towerWrapper.tower.towerId}</small></small>
</h2>