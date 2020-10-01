package servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanUsuario;

import dao.DaoUsuario;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/salvarUsuario")
@MultipartConfig
public class Usuario extends HttpServlet {

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		try {

			DaoUsuario daoUsuario = new DaoUsuario();
			String acao = request.getParameter("acao");
			String idUsuario = request.getParameter("user");
			int idUser;
			if (idUsuario == null) {

				idUser = 0;
			} else {
				idUser = Integer.parseInt(idUsuario);
			}

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(idUser);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

			else if (acao.equalsIgnoreCase("editar")) {
				BeanUsuario login = daoUsuario.consultar(idUser);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuario", login);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listarTodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");

				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}
			else if (acao.equalsIgnoreCase("download")) {
				String tipo = request.getParameter("tipo");
				BeanUsuario login =daoUsuario.consultar(idUser);
				if(login !=null) {
					
					String contentType="";
					byte[] fileBytes =null;
					if(tipo.equalsIgnoreCase("imagem")) {
						contentType = login.getContentType();
						fileBytes = new Base64().decodeBase64(login.getFotoBase64());
					}else if(tipo.equalsIgnoreCase("pdf")){
						contentType = login.getContentTypeCurriculo();
						fileBytes = new Base64().decodeBase64(login.getCurriculo());
					}
					
					String[] extensao = contentType.split("\\/");
					response.setHeader("Content-Disposition","attachment;filename=arquivo."+extensao[1]);
					
					
					InputStream is = new ByteArrayInputStream(fileBytes);
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					while((read = is.read(bytes))!= -1) {
						os.write(bytes,0, read);
					}
					os.flush();
					os.close();
				}
			}
		} catch (ServletException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		DaoUsuario daoUsuario = new DaoUsuario();

		String acao = request.getParameter("acao");
		try {
//			Açao do botão cancelar
			if (acao == null) {
				System.out.println("é null");
				acao = "";
			}

			if (acao.equalsIgnoreCase("reset") && acao != null) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else {

				String id = request.getParameter("cod");
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				String nome = request.getParameter("nome");

				String cep = request.getParameter("cep");
				String rua = request.getParameter("rua");
				String bairro = request.getParameter("bairro");
				String cidade = request.getParameter("cidade");
				String estado = request.getParameter("estado");
				String sexo = request.getParameter("sexo");
				String perfil = request.getParameter("perfil");
				BeanUsuario usuario = new BeanUsuario();

				usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
				usuario.setLogin(login);
				usuario.setSenha(senha);
				usuario.setNome(nome);
				usuario.setCep(cep);
				usuario.setRua(rua);
				usuario.setBairro(bairro);
				usuario.setCidade(cidade);
				usuario.setEstado(estado);
				usuario.setSexo(sexo);
				usuario.setPerfil(perfil);
				
				if(request.getParameter("ativo") != null && request.getParameter("ativo").equalsIgnoreCase("on")) {
					usuario.setAtivo(true);
				}else{
					usuario.setAtivo(false);
				}
				
				/* Inicio upload de arquivo NÃO ESTÁ CRIANDO A LISTA */
				if (ServletFileUpload.isMultipartContent(request)) {
					Part imagemFoto = request.getPart("foto");
					Part curriculoPdf = request.getPart("curriculo");
					if(imagemFoto != null && imagemFoto.getInputStream().available()>0) {
						String fotoBase64 = new Base64().encodeBase64String(converteStringParaByte(imagemFoto.getInputStream()));
						String contentType = imagemFoto.getContentType();
						
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(contentType);
						
						
					}else {
						usuario.setFotoBase64(request.getParameter("fotoTemp"));
						usuario.setContentType(request.getParameter("fotoTempContext"));
					}
					
					if(curriculoPdf != null  && curriculoPdf.getInputStream().available()>0) {
						String curriculoPdf64 = new Base64().encodeBase64String(converteStringParaByte(curriculoPdf.getInputStream()));
						String contentType = curriculoPdf.getContentType();
						usuario.setCurriculo(curriculoPdf64);
						usuario.setContentTypeCurriculo(contentType);
					}else {
						usuario.setCurriculo(request.getParameter("curriculoTemp"));
						usuario.setContentTypeCurriculo(request.getParameter("curriculoTempContext"));
					}
			
				}

				/* Fim do upload de arquivo */
				if ((nome.isEmpty() || nome.equals("") && (senha.isEmpty() || senha.equals("")))) {
					request.setAttribute("msg", "O login e senha são obrigatórios");
				} else {
					if (id == null || id.isEmpty()) {
						if (daoUsuario.validarLogin(login)) {
							daoUsuario.salvar(usuario);

						} else {
							request.setAttribute("msg", "O login " + login + " já existe");
							request.setAttribute("usuario", usuario);
						}

					} else {

						if (daoUsuario.validarLoginUpdate(login, usuario.getId())) {
							daoUsuario.atualizar(usuario);
						} else {
							request.setAttribute("msg", "O login " + login + " já existe");
						}

					}
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	/*Converte o fluxo de dados de uma imgem para um array de Byte*/
	private byte[] converteStringParaByte(InputStream imagem) throws IOException {
			ByteArrayOutputStream boas = new ByteArrayOutputStream();
			int reads = imagem.read();
			while (reads != -1) {
				boas.write(reads);
				reads = imagem.read();
			}
			return  boas.toByteArray();
		
	}
}
