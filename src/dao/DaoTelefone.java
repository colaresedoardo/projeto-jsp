package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanUsuario;
import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefone {
	private Connection conexao;
	public DaoTelefone() {
		conexao = SingleConnection.getConexao();
		
	}
	
	public void salvar(BeanTelefone telefone) {
		try {
			String sql = "INSERT INTO telefone (numero, tipo, fk_usuario) VALUES(?,?,?)";
			
			PreparedStatement insert = conexao.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getId_usuario());
			insert.execute();
			conexao.commit();
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("Ocorreu um erro ao fazer o rollback");
				e1.printStackTrace();
			}
		}
		
		
	}
	
	public List<BeanTelefone> listar() {
		
		String sql = "SELECT id, numero, tipo, fk_usuario FROM telefone";
		PreparedStatement statement;
		try {
			statement = conexao.prepareStatement(sql);
			ResultSet resultset =  statement.executeQuery();
			 List<BeanTelefone> lista= new ArrayList<BeanTelefone>();
			while(resultset.next()) {
				BeanTelefone telefone= new BeanTelefone();
				telefone.setId(resultset.getLong("id"));
				telefone.setNumero(resultset.getString("numero"));
				telefone.setTipo(resultset.getString("tipo"));
				telefone.setId_usuario(resultset.getLong("fk_usuario"));
				
				lista.add(telefone);
				
			}
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public List<BeanTelefone> listar(int id) {
		
		String sql = "SELECT id, numero, tipo, fk_usuario FROM telefone WHERE fk_usuario =?";
		PreparedStatement statement;
		try {
			statement = conexao.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet resultset =  statement.executeQuery();
			 List<BeanTelefone> lista= new ArrayList<BeanTelefone>();
			while(resultset.next()) {
				BeanTelefone telefone= new BeanTelefone();
				telefone.setId(resultset.getLong("id"));
				telefone.setNumero(resultset.getString("numero"));
				telefone.setTipo(resultset.getString("tipo"));
				telefone.setId_usuario(resultset.getLong("fk_usuario"));
				
				lista.add(telefone);
				
			}
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public void delete(int telefone) {
		String sql =" DELETE FROM telefone where id=?";
		PreparedStatement prepared;
		try {
			prepared = conexao.prepareStatement(sql);
			prepared.setInt(1,telefone);
			prepared.execute();
			conexao.commit();
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
	
		
	}
	
	

	public void atualizar(BeanTelefone telefone) {
		String sql = "UPDATE telefone SET numero=?, tipo=?, fk_usuario=? WHERE id=?";
		
		PreparedStatement prst;
		try {
			prst = conexao.prepareStatement(sql);
			
			prst.setString(1, telefone.getNumero());
			prst.setString(2, telefone.getTipo());
			prst.setLong(3, telefone.getId_usuario());
			prst.setLong(4, telefone.getId());
		
			prst.executeUpdate();
			conexao.commit();
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public BeanTelefone consultar(int idTelefone) {
		String sql = "SELECT id, numero, tipo, fk_usuario   FROM telefone WHERE id=?";
		
		try {
			PreparedStatement prst = conexao.prepareStatement(sql);
			prst.setInt(1, idTelefone);
			ResultSet result = prst.executeQuery();
			if(result.next()) {
				BeanTelefone telefone = new BeanTelefone();
				
				telefone.setId(result.getLong("id"));
				telefone.setNumero(result.getString("numero"));
				telefone.setTipo(result.getString("tipo"));
				telefone.setId_usuario(result.getLong("fk_usuario"));
				return telefone;
			}
			return null;
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}
}
