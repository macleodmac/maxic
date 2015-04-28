<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById('currentLink').value = document.URL;
	}
</script>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-md-8 col-md-offset-2">
			<h2>${tower}
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="pull-right">
						<a class="btn btn-sm btn-default"
							href="${pageContext.request.contextPath}/admin/peals/edit?p=${peal.pealId}">Edit</a>
					</div>
				</sec:authorize>
			</h2>
			<hr />
			<h3>
				<strong>${peal.changes}</strong> <strong>${peal.method}</strong>
			</h3>
			<h4>${peal.dateRung} in ${peal.time}</h4>
			<c:if test="${not empty peal.composer}">
					<h4>Composed by ${peal.composer }</h4>
				</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-md-4 col-md-offset-2">
			<table class="table">
				<c:if test="${not empty peal.ringer1}">
					<tr>
						<th>1 (1-2)</th>
						<td>${peal.ringer1}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer2}">
					<tr>
						<th>2 (3-4)</th>
						<td>${peal.ringer2}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer3}">
					<tr>
						<th>3 (5-6)</th>
						<td>${peal.ringer3}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer4}">
					<tr>
						<th>4 (7-8)</th>
						<td>${peal.ringer1}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer5}">
					<tr>
						<th>5 (9-10)</th>
						<td>${peal.ringer2}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer6}">
					<tr>
						<th>6 (11-12)</th>
						<td>${peal.ringer6}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer7}">
					<tr>
						<th>7 (13-14)</th>
						<td>${peal.ringer1}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer8}">
					<tr>
						<th>8 (15-16)</th>
						<td>${peal.ringer8}</td>
					</tr>
				</c:if>
			</table>
		</div>
		<div class="col-xs-12 col-md-4">
			<table class="table">
				<c:if test="${not empty peal.ringer9}">
					<tr>
						<th>9</th>
						<td>${peal.ringer9}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer10}">
					<tr>
						<th>10</th>
						<td>${peal.ringer10}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer11}">
					<tr>
						<th>11</th>
						<td>${peal.ringer11}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer12}">
					<tr>
						<th>12</th>
						<td>${peal.ringer12}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer13}">
					<tr>
						<th>13</th>
						<td>${peal.ringer13}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer14}">
					<tr>
						<th>14</th>
						<td>${peal.ringer14}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer15}">
					<tr>
						<th>15</th>
						<td>${peal.ringer15}</td>
					</tr>
				</c:if>
				<c:if test="${not empty peal.ringer16}">
					<tr>
						<th>16</th>
						<td>${peal.ringer16}</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-md-4 col-md-offset-2">
			<div class="form-group">
				<a class="btn btn-sm btn-default"
					href="${pageContext.request.contextPath}/towers/view?t=${peal.tower.id}">View
					Tower</a> <a class="btn btn-sm btn-default"
					href="${pageContext.request.contextPath}/peals?t=${peal.tower.id}">View
					More Performances</a>
			</div>
			<div class="form-group">
				<label for="currentLink">Sharing Link</label> <input
					onClick="this.setSelectionRange(0, this.value.length)" type="text"
					class="form-control" id="currentLink" value="" />
			</div>
		</div>
	</div>
</div>