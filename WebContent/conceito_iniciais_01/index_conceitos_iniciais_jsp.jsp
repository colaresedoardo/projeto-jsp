<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- exemplo de tag customizada -->
<%-- <%@ taglib prefix="myprefix" uri="WEB-INF/testeTag.tld" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- Settando um atribuot de sessão. -->
<%-- <% session.setAttribute("curso", "Informática"); %> --%>
	<h1>Bem vindo</h1>
	
	
<!-- 	Diretiva importando uma classe -->
<%-- 	<%@ page import ="java.util.Date"%> --%>
<%-- 	<%= "Data eh "+ new Date() %> --%>

<%-- 		<%@ page info="Pagina do curso de jsp" %> --%>
<!-- 		getServletInfo: recupera o valor de info            -->


<!-- 	Identificando erro na página -->
<%-- 		<%@ page errorPage="paginaDeErro.jsp" %> --%>
<%-- 		<%= 100/0 %> --%>

<!-- 		incluíndo uma página -->
<%-- 		<%@ include file="paginaInclude.jsp" %> --%>

	<br />
	<% out.print("Seu sucesso na aprovaçção #TJ-SC"); %>
	
<!-- 	Exemplo de tag custumizada. Foi criado uma classe java chamada TagCursoJsp e depois a testeTag.tld -->
<%-- 	<myprefix:Minhatag/> --%>


<!-- 		Enviar dados para outra página	 -->
<%-- 	<jsp:forward page="paginaDeErro.jsp"> --%>
<%-- 		<jsp:param value="Java jsp" name="curso"/> --%>
<%-- 	</jsp:forward> --%>
	
	<jsp:include page="cabecalho.jsp"></jsp:include>
	
	<form action="receberNome.jsp">
		<input type="text" id="nome" name="nome">
		<input type="submit" value="enviar"></input>
	</form>
	
	
	
	
	
	
	
</body>
</html>