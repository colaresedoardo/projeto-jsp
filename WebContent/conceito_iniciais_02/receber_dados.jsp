<!-- usando o bean criado no servidor backend -->
<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- 		Pegando dados usando o jsp -->
<%-- 	<jsp:setProperty property="*" name="calcula" /> --%>
	
<%-- 	<jsp:getProperty property="nome" name="calcula"/> --%>
<!-- 	<br/> -->
<%-- 	<jsp:getProperty property="ano" name="calcula"/> --%>
<!-- 	<br/> -->
<%-- 	<jsp:getProperty property="sexo" name="calcula"/> --%>
	
<!-- 		Usando Expression Language para capturar os dados -->
			
		<h4>Exemplo de expression Language</h4>
		usuario: ${sessionScope.user}
		<br/> 
		nome: ${param.nome}
		<br/> 
		ano: ${param.ano}
		<br/>
		sexo:${param.sexo}
		
		
</body>
</html>