package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;

public class DaoLogin {
	
	private Connection conexao;
	public DaoLogin() {
		conexao = SingleConnection.getConexao();
		
	}
	
	public boolean validarLogin(String login, String senha){
		try {
			String sql = "SELECT login, senha FROM usuarios WHERE login =? AND senha= ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, login);
			ResultSet resultset = statement.executeQuery();
			if(resultset.next()) {
				return true;
			}else {
				return false;		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
		
	
		
	
		
		
	}
}
