package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

@WebServlet("/produto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		request.setAttribute("produto", "pagina acessada com sucesso");
		try {
			String acao = request.getParameter("acao");
			String id_teste = request.getParameter("produto_id");
			Long id;
			if(id_teste == null) {
				System.out.println("eh null");
				id = null;
			}
			else {
			  id= Long.parseLong(request.getParameter("produto_id"));
			}
			
			
			
		
			DaoProduto daoProduto = new DaoProduto();
			if (acao.equalsIgnoreCase("listarTodos")) {
				request.setAttribute("produtos", daoProduto.listar());

			}
			if (acao.equalsIgnoreCase("delete")) {
			
				daoProduto.delete(id);
				request.setAttribute("produtos", daoProduto.listar());

			}
			if(acao.contentEquals("editar") && id != 0l) {
				BeanProduto produto = daoProduto.listarPeloId(id);
				request.setAttribute("prod", produto);
		
			}
			
			RequestDispatcher view = request.getRequestDispatcher("/acessoProduto.jsp");
			view.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doGet(request, response);
		try {
			String id_naoTratado = request.getParameter("id");
			Long id = request.getParameter("id").isEmpty() || request.getParameter("id") == null ? 0l
					: Long.parseLong(request.getParameter("id"));
			String nome = request.getParameter("nome");
			String preco = request.getParameter("preco");
			String qtd = request.getParameter("qtd");
			DaoProduto daoProduto = new DaoProduto();
			BeanProduto produto = new BeanProduto();
			produto.setId(id);
			produto.setNome(nome);
			produto.setPreco(Double.parseDouble(preco));
			produto.setQtd(Double.parseDouble(qtd));
			if(id_naoTratado == null || id_naoTratado.equals("")) {
				daoProduto.inserir(produto);
			}else if(id>0l) {
				daoProduto.update(produto);
			}
			

			RequestDispatcher view = request.getRequestDispatcher("/acessoProduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
