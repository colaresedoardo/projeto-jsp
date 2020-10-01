
<jsp:useBean id="calcula" class="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<!--  Trabalhando JSTL -->
	<%-- <c:out value="${'bem vindo ao JSTL'}"></c:out> --%>
	<!-- traz o código do google -->
	<%-- <c:import var="data"url="https://www.google.com.br"></c:import> --%>

	<%-- <c:set var="data" value="${500*6 }"></c:set> --%>
	<!-- Remove os valores da variável data -->
	<%-- <c:remove var="data"/> --%>
	<%-- <c:out value="${data}"></c:out> --%>

	<!-- Pegando erro o catch. -->
	<%-- <c:catch var="error"> --%>
	<%-- 	<% int var =100/1; %> --%>

	<%-- </c:catch> --%>

	<%-- <c:if test="${error != null }"> --%>
	<%-- 	erro é ${error.message} --%>
	<%-- </c:if> --%>


<!--     Semelhante ao SWITCH  --> 
<%-- 	<c:set var="numero" value="${100/2}"></c:set> --%>

<%-- 	<c:choose> --%>
<%-- 		<c:when test="${ numero >=50 }"> --%>
<%-- 			<c:out value="${'maior ou igual a 50' }"></c:out> --%>
<%-- 		</c:when> --%>
<%-- 		<c:when test="${ numero < 50 }"> --%>
<%-- 			<c:out value="${'menor q 50' }"></c:out> --%>
<%-- 		</c:when> --%>
<%-- 	</c:choose> --%>

<!-- Usando o for -->
<%-- <c:forEach var="n" begin="1" end="10"> --%>
<%-- 	item: ${n} --%>
<!-- 	<br /> -->
<%-- </c:forEach> --%>

<!-- 	Quebrando strings, semalhante ao split -->
<%-- <c:forTokens items="Ana-Edoardo-Mariele-Jessica" delims="-" var="nomes"> --%>
<%-- 	nome: <c:out value="${nomes}"></c:out> --%>
<!-- 	<br/> -->
<%-- </c:forTokens> --%>

<!-- 	Montando url a partir do JSTL -->
<%-- <c:url value="/acessoLiberado.jsp" var="acesso"> --%>
<%-- 	<c:param name="code" value="21"></c:param> --%>
<%-- </c:url> --%>
<%-- exemplo de montagem de url: ${acesso} --%>


<!-- 	Trabalhando com redirecionamento de páginas. -->
<%-- <c:set var="num" value="60"></c:set> --%>
<%-- <c:if test="${num > 50 }"> --%>
<%-- 	<c:redirect url="https://www.google.com"></c:redirect> --%>
<%-- </c:if> --%>



	<h1>Bem vindo</h1>

	<form action="LoginServlet" method="POST">

		Login:<input type="text" name="login" /> Senha:<input type="password"
			name="senha"> <input type="submit" name="enviar">
	</form>
</body>
</html>