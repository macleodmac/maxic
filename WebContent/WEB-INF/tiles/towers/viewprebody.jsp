<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="modal-title">
	<strong>${tower.dedication}</strong>
	<c:if test="${not empty tower.placeName}">
		<small> ${tower.placeName}, </small>
	</c:if>
	<c:if test="${not empty tower.placeName2}">
		<small>${tower.placeName2}</small>
	</c:if>
	<small><small> id: ${tower.towerId}</small></small>
</h2>