package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanUsuario;
import dao.DaoLogin;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BeanUsuario login =  new BeanUsuario();
		DaoLogin daoLogin = new DaoLogin();
		
//		System.out.println("seu login: "+ request.getParameter("login")+" sua senha: "+request.getParameter("senha"));
		login.setLogin(request.getParameter("login"));
		login.setSenha(request.getParameter("senha"));
		
		try {
			if(daoLogin.validarLogin(login.getLogin(),login.getSenha())) {
				RequestDispatcher dispatcher =  request.getRequestDispatcher("acessoLiberado.jsp");
				dispatcher.forward(request, response);
				
			}else {
				RequestDispatcher dispatcher =  request.getRequestDispatcher("acessoNegado.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
