
<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	 scope="page"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Bem vindo</h1>
		
	<% session.setAttribute("user", "edoardo"); %>
	<form action="receber_dados.jsp" method="POST">

		<input type="text" id="nome" name="nome">
		<br/>
		<input type="text" id="nome" name="ano">
		<br/>
		<input type="text" id="nome" name="sexo">
		 <br/>
		 <input	type="submit" value="enviar" />
	</form>

</body>
</html>