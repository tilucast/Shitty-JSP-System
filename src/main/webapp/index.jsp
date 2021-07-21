<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Willkommen !!</h1>
	
	<h4>${Message}</h4>
	
	<input type="hidden" value="<%= request.getParameter("url") %>" />
	
	<form action="ServletLogin" method="post">
		<label for="login">
			<p>Login</p>
			<input name="login" id="login" required/>
		</label>
		<label for="password">
			<p>Password</p>
			<input name="password" id="password" type="password"/>
		</label>
		<button type="submit">Send parameters</button>
	</form>
</body>
</html>