
<jsp:useBean id="calcula" class="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/estilo.css">

<!-- <style  href="resources/css/estilo.css" type="text/css"></style> -->
</head>
<body>


	<div class="login-page">
		<div class="form">
		
			<form class="login-form" action="LoginServlet">
				<input type="text" placeholder="usuario" name="login" /> <input
					type="password" placeholder="password" name="senha" />
				<button>login</button>

			</form>
		</div>
	</div>




</body>
</html>