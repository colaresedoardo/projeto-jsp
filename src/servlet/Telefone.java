package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanTelefone;
import dao.DaoTelefone;

/**
 * Servlet implementation class Telefone
 */
@WebServlet("/salvarTelefone")
public class Telefone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			String userId = request.getParameter("user");
			if(userId != null) {
				request.getSession().setAttribute("idUsuario", userId);
			}else {
				 userId = (String) request.getSession().getAttribute("idUsuario");
			}
			
			String acao = request.getParameter("acao");
			DaoTelefone daoTelefone = new DaoTelefone();
			String idTelefone = request.getParameter("idFone");
			

			if (acao == null && idTelefone == null) {
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				
				request.setAttribute("telefones", daoTelefone.listar(Integer.parseInt(userId)));
				view.forward(request, response);
			}
			
			else if (acao.equalsIgnoreCase("editar")) {
				
				BeanTelefone telefone = daoTelefone.consultar(Integer.parseInt(idTelefone));
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefone", telefone);
				view.forward(request, response);
			}
			else if(acao.equalsIgnoreCase("delete")) {
				daoTelefone.delete(Integer.parseInt(idTelefone));
			
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar(Integer.parseInt(userId)));
				view.forward(request, response);
			}
	
			

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String userId = (String) request.getSession().getAttribute("idUsuario");
			if(userId == null) {
				userId = (String) request.getAttribute("user");
			}
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");
			String idTelefone = request.getParameter("idTelefone");
			DaoTelefone daoTelefone = new DaoTelefone();
			
			BeanTelefone telefone = new BeanTelefone();
			telefone.setId_usuario(Long.parseLong(userId));
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			
			
			if (idTelefone == null || idTelefone.isEmpty()) {
		

				daoTelefone.salvar(telefone);

			} else {
				telefone.setId(Long.parseLong(idTelefone));
				daoTelefone.atualizar(telefone);

			}

			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
			request.setAttribute("telefones", daoTelefone.listar(Integer.parseInt(userId)));
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
