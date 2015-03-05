<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<table class="table">
		<tbody>
			<tr>
				<td>${peal.pealId}</td>
				<td>${peal.dateRung}</td>
				<td>${peal.towerId}</td>
				<td>${peal.changes}</td>
				<td>${peal.method}</td>
			</tr>
		</tbody>
	</table>
</div>