<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<sf:form method="post"
		action="${pageContext.request.contextPath}/doadd" commandName="tower">

		<table class="formtable">
			<tr>
				<td>Name:</td>
				<td><sf:input name="doveId" path="doveId" type="text"  /><br />
					<sf:errors path="doveId" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><sf:input name="latitude" path="latitude" type="text" /><br />
					<sf:errors path="latitude" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><sf:input name="latitude" path="latitude" type="text" /><br />
					<sf:errors path="latitude" cssClass="error"></sf:errors></td>
			</tr>
			<tr>
				<td></td>
				<td><input value="Create Tower" type="submit" /></td>
			</tr>
		</table>
	</sf:form>

</body>
</html>