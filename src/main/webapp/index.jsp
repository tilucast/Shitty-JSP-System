<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body
	class="flex flex-col justify-center items-center h-full min-h-screen font-mono"
>
	<h1 class="text-4xl m-8 text-purple-700 font-semibold">Willkommen !!</h1>
	
	<h4>${Message}</h4>
	
	<input type="hidden" value="<%= request.getParameter("url") %>" />
	
	<form 
		action="ServletLogin" 
		method="post"
		class="flex justify-between flex-col text-2xl h-56"
	>
		<label for="login">
			<p class="my-2">Login</p>
			<input 
				name="login" 
				id="login" 
				required
				class="rounded border-2 border-gray-800 focus:outline-none focus:ring focus:border-blue-300"
			/>
		</label>
		<label for="password">
			<p class="my-2">Password</p>
			<input 
				name="password" 
				id="password" 
				required
				class="rounded border-2 border-gray-800 focus:outline-none focus:ring focus:border-blue-300"
			/>
		</label>
		<button 
			type="submit"
			class="ring rounded-sm focus:ring-4 bg-blue-500 text-gray-100"
		>
		Send parameters</button>
	</form>
</body>
</html>