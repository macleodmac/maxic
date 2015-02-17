<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Hello World!</h1>

<table>

<c:forEach var="row" items="${towers}">
    <tr>
    	<td>${row.towerId}</td>
    	<td>${row.placeName}</td>
    	<td>${row.latitude}</td>
    	<td>${row.longitude}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>