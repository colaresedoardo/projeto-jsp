
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%// out.print(request.getParameter("nome")); %>

	<%="seu nome eh:" +request.getParameter("nome") %>
	
<!-- 	Tag declarativa de vari�veis -->
<%-- 	<%! int v =10;%> --%>
<br />
<%= request.getContextPath()  %>
<br />


<!-- Respondendo para outra p�gina -->
<%-- <%response.sendRedirect("https://www.google.com"); %> --%>

<!-- Usando o objeto application do ServletContext  -->
<%-- <%= application.getInitParameter("estado") %> --%>

<!-- Pegando um atributo de sess�o -->
<%-- <%= session.getAttribute("curso") %> --%>



</body>
</html>