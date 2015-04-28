<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-md-6 col-md-offset-2">

			<h2>Visit Details</h2>
			<hr />

		</div>
		<div class="col-xs-12 col-md-6 col-md-offset-2">
			<h3>
				<strong>Tower:</strong> ${visit.tower.de} <small><a
					href="${pageContext.request.contextPath}/towers/view?t=${visit.tower.id}">(View)</a>
				</small>
			</h3>
			<h4>
				<strong>Rung: </strong>
				<c:choose>
					<c:when test="${visit.rung}">Yes</c:when>
					<c:otherwise>
					No
					</c:otherwise>
				</c:choose>
			</h4>
			<h4>
				<strong>Quarter Peal Rung: </strong>
				<c:choose>
					<c:when test="${visit.quarterPealRung}">Yes</c:when>
					<c:otherwise>
					No
					</c:otherwise>
				</c:choose>
			</h4>
			<h4>
				<strong>Peal Rung: </strong>
				<c:choose>
					<c:when test="${visit.pealRung}">Yes</c:when>
					<c:otherwise>
					No
					</c:otherwise>
				</c:choose>
			</h4>
			<h4>
				<strong>Notes:</strong>
			</h4>
			<p>${visit.notes}</p>
		</div>
	</div>
</div>