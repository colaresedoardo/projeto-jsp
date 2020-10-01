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
	<a href="acessoLiberado.jsp">Voltar</a>
	<a href="index.jsp">Sair</a>
	<h1>Cadastro de telefones</h1>
	
	<form action="salvarTelefone" method="POST" id="form1">
		id usuário <input type="text" name="idUsuario" value="${telefone.id_usuario }" readonly="readonly"><br />
		id telefone<input type="text" name="idTelefone" value="${telefone.id}" readonly="readonly"><br />
		número <input type="text" placeholder="informe o número"    name="numero" value="${telefone.numero }"><br />
		Tipo<select name="tipo" >
			<option> Casa</option>
			<option> Contato</option>
			<option> Celular</option>
		</select>
		<br />
		<input type="submit" value="salvar" onclick="return validarCampos()">
		<input type="submit" value="cancelar"
			onclick="document.getElementById('form1').action ='salvarUsuario?acao=reset'">
	</form>
	<c:if test="${telefones != null }"> 

	<table  style="width:30%">
	<tr>
		<th>identificador usuario </th>
		<th>número</th>
		<th>tipo</th>
		
		
		
	</tr>
		<c:forEach items="${telefones}" var="fone">
			<tr>
				<td><c:out value="${fone.id_usuario }"></c:out></td>
				<td><c:out value="${fone.numero}"></c:out></td>
				<td><c:out value="${fone.tipo}"></c:out></td>
				<td><a href="salvarTelefone?acao=delete&idFone=${fone.id }">excluir</a>
				</td>
				<td><a href="salvarTelefone?acao=editar&idFone=${fone.id }">editar</a>
				
				</td>
			</tr>
		</c:forEach>
	</table>
	
	</c:if>
	<script type="text/javascript">
		function consultarCep(){
			var cep = $("#cep").val()
			
			
			 $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                 if (!("erro" in dados)) {
                     //Atualiza os campos com os valores da consulta.
                     $("#rua").val(dados.logradouro);
                     $("#bairro").val(dados.bairro);
                     $("#cidade").val(dados.localidade);
                     $("#estado").val(dados.uf);
                 } //end if.
                 else {
                     //CEP pesquisado não foi encontrado.
                     limpa_formulário_cep();
                     alert("CEP não encontrado.");
                 }
             });
			
		}
	
		function validarCampos() {
			nome = form1.nome.value;
			if (nome == "") {
				alert("Nome Obrigatório");
				form1.nome.focus();
				return false;
			}

		}
	</script>

</body>
</html>