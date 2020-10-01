package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import connection.SingleConnection;
//Este filter é responsável por interceptar todas as conexões. Aqui podem ser feitas validações e entre outras coisas.
@WebFilter(urlPatterns = {"/*"})
public class Filter implements  javax.servlet.Filter{
	private static Connection conexao;
	
	
	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
			conexao.commit();
		
		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		conexao = SingleConnection.getConexao();
		
	}

}
