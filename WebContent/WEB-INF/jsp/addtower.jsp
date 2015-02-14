<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<sf:form method="post"
		action="${pageContext.request.contextPath}/doadd" commandName="tower">

		<table class="formtable">
			<tr>
				<td>doveId:</td>
				<td><sf:input name="doveId" path="doveId" type="text" /></td>
			</tr>
			<tr>
				<td>Latitude:</td>
				<td><sf:input name="latitude" path="latitude" type="text" /></td>
			</tr>
			<tr>
				<td>Longitude:</td>
				<td><sf:textarea name="longitude" path="longitude" type="text" ></sf:textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input value="Add Tower" type="submit" /></td>
			</tr>
		</table>
	</sf:form>

</body>
</html>