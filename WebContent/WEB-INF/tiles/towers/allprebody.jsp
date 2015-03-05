<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="modal-title">
	<strong>${towerAll.tower.placeName}</strong>
	<c:if test="${not empty towerAll.tower.placeName2}">
		<small> ${towerAll.tower.placeName2},</small>
	</c:if>
	<c:if test="${not empty tower.dedication}">
		<small> ${towerAll.tower.dedication}</small>
	</c:if>
	<small><small> id: ${towerAll.tower.towerId}</small></small>
</h2>