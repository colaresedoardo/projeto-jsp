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

	<%-- 	<c:out value="${produto}"></c:out> --%>
	<h2>Cadastro de produto</h2>
	<form action="produto" method="POST" id="form1">
		id <input type="text" name="id" value="${prod.id }" readonly="readonly"><br />
		Nome <input type="text" name="nome" value="${prod.nome }"><br />
		quantidade <input type="text" name="qtd" value="${prod.qtd }"><br />
		preco <input type="text" name="preco" value="${prod.preco }"><br />
		<input type="submit" value="salvar" onclick="return validarCampos()"> <input type="submit"
			value="cancelar"
			onclick="document.getElementById('form1').action ='produto?acao=reset'">
	</form>

	<c:if test="${produtos != null }">


		<table border="1px solid">
			<tr>
				<th>Id</th>
				<th>nome</th>
				<th>qtd</th>
				<th>preço</th>
			</tr>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td><c:out value="${produto.id }"></c:out></td>
					<td><c:out value="${produto.nome}"></c:out></td>
					<td><c:out value="${produto.qtd}"></c:out></td>
					<td><c:out value="${produto.preco}"></c:out></td>
					<td><a href="produto?acao=delete&produto_id=${produto.id }">excluir</a>
					</td>
					<td><a href="produto?acao=editar&produto_id=${produto.id }">editar</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
		<script type="text/javascript">
		
		function validarCampos(){
			
			nome = 	form1.nome.value;
			if(nome ==""){
				alert("Nome Obrigatório");
				form1.nome.focus();
				return false;
			}
			
		}
	
	</script>
	
</body>
</html>
