<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="media-left">
	<img class="media-object" height="30px" width="30px" style="margin-top: 5px;" src="${pageContext.request.contextPath}/static/img/flags/${towerWrapper.tower.country.isoCode}.png" alt="Flag">
</div>
<div class="media-body">
	<h2 class="modal-title">

		<strong>${towerWrapper.tower.dedication}</strong>
		<c:if test="${not empty towerWrapper.tower.placeName}">
			<small> ${towerWrapper.tower.placeName}, </small>
		</c:if>
		<c:if test="${not empty towerWrapper.tower.placeName2}">
			<small>${towerWrapper.tower.placeName2}</small>
		</c:if>
		<small><small> id: ${towerWrapper.tower.towerId}</small></small>
		
		<sec:authorize access="hasRole('ROLE_ADMIN')">
				<div class="pull-right" style="padding-right: 10px;">
					<a class="btn btn-sm btn-info"
						href="${pageContext.request.contextPath}/admin/towers/edit?t=${towerWrapper.tower.towerId}">Edit</a>
				</div>
			</sec:authorize>
	</h2>
</div>