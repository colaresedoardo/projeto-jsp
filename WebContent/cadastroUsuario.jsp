<%@page import="beans.BeanUsuario"%>
<%@page import="dao.DaoUsuario"%>
<%@page import="servlet.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
</head>
<body>
	<a href="acessoLiberado.jsp">Voltar</a>
	<a href="index.jsp">Sair</a>
	<h1>Formulário para cadastrar usuário</h1>
	<h2>${msg}</h2>
	<form action="salvarUsuario" method="POST" id="form1" enctype="multipart/form-data">
		Código <input type="text" name="cod" value="${usuario.id }"><br />
		Nome <input type="text" name="nome" value="${usuario.nome}"><br />
		Login <input type="text" name="login" value="${usuario.login }"><br />
		senha <input type="password" name="senha" value="${usuario.senha }"><br />
		cep <input type="text" id="cep" name="cep" onblur="consultarCep();"
			value="${usuario.cep}"><br /> rua <input type="text"
			id="rua" name="rua" value="${usuario.rua }"><br /> bairro <input
			type="text" id="bairro" name="bairro" value="${usuario.bairro}"><br />
		cidade <input type="text" id="cidade" name="cidade"
			value="${usuario.cidade }"><br />
	 estado <input type="text"
			id="estado" name="estado" value="${usuario.estado }"><br />
	
	  <span>Ativo</span>
	  	 <input type="checkbox"
			id="ativo" name="ativo" 
			<%if(request.getAttribute("usuario") != null){
				BeanUsuario user = (BeanUsuario) request.getAttribute("usuario");
				if(user.isAtivo()){
					out.print(" ");
					out.print("checked=\"checked\"");
					out.print(" ");
				}
			}%>
			 ><br />
	Sexo: <input type="radio" name="sexo" value="masculino"
		<%if(request.getAttribute("usuario") != null){
				BeanUsuario user = (BeanUsuario) request.getAttribute("usuario");
				if(user.getSexo() != null && user.getSexo().equalsIgnoreCase("masculino")){
					out.print(" ");
					out.print("checked=\"checked\"");
					out.print(" ");
				}
			}%>
	
	
	> masculino </input>
	      <input type="radio" name="sexo" value="feminino"
	      
	      <%if(request.getAttribute("usuario") != null){
				BeanUsuario user = (BeanUsuario) request.getAttribute("usuario");
				if(user.getSexo() != null && user.getSexo().equalsIgnoreCase("feminino")){
					out.print(" ");
					out.print("checked=\"checked\"");
					out.print(" ");
				}
			}%>
	      > feminino</input>
			<br />
			<%if(request.getAttribute("usuario") != null){
				BeanUsuario user = (BeanUsuario) request.getAttribute("usuario");
				if(user.getSexo() != null && user.getSexo().equalsIgnoreCase("feminino")){
					out.print(" ");
					out.print("checked=\"checked\"");
					out.print(" ");
				}
			}%>
			Perfil do usuario
			 <select name="perfil">
				<option>Não selecionado </option>
				
			    <option value="administrador" >administrador</option>
				<option value="secretario">secretário</option>
				<option value="gerente">gerente</option>
			</select>
			<br />
	File:<input type = "file" name="foto" >	<br />
	<input type="text" style="display: none;" name="fotoTemp" readonly="readonly" value="${ usuario.fotoBase64}">
	<input type="text" style="display: none;" name="fotoTempContext" readonly="readonly" value="${ usuario.contentType}">
	
	curriculo em pdf:<input type = "file" name="curriculo" >	<br />
		<input type="text" style="display: none;" name="curriculoTemp" readonly="readonly" value="${ usuario.curriculo}">
	<input type="text" style="display: none;" name="curriculoTempContext" readonly="readonly" value="${ usuario.contentTypeCurriculo}">
	<br />
		<input type="submit" value="salvar" onclick="return validarCampos()">
		<input type="submit" value="cancelar"
			onclick="document.getElementById('form1').action ='salvarUsuario?acao=reset'">
	</form>

	<table style="width: 50%">
		<tr>
			<th>id</th>
			<th>nome</th>
			<th>rua</th>

		</tr>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td><c:out value="${usuario.id }"></c:out></td>
				<td><c:out value="${usuario.nome}"></c:out></td>
				<td><c:out value="${usuario.rua}"></c:out></td>
				<td><a href="salvarUsuario?acao=download&tipo=imagem&user=${usuario.id }"> <img alt="foto" src= '<c:out value="${usuario.tempFotoUsuario }" ></c:out>' width="32px" height="32px"> </a></td>
				<td><a href="salvarUsuario?acao=download&tipo=pdf&user=${usuario.id }"> curriculo</a></td>
				<td><a href="salvarUsuario?acao=delete&user=${usuario.id }">excluir</a>
				</td>
				<td><a href="salvarUsuario?acao=editar&user=${usuario.id }">editar</a>

				</td>
				<td><a href="salvarTelefone?user=${usuario.id }">Telefone</a>

				</td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		function consultarCep() {
			var cep = $("#cep").val()

			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

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