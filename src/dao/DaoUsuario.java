package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanUsuario;
import connection.SingleConnection;

public class DaoUsuario {
	
	private Connection conexao;
	public DaoUsuario() {
		conexao = SingleConnection.getConexao();
		
	}
	
	public void salvar(BeanUsuario usuario) {
		try {
			String sql = "INSERT INTO usuarios (login, senha, nome, cep, rua, bairro, cidade,estado, fotoBase64, contentType, contenttypecurriculo,curriculo, ativo,sexo,perfil ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement insert = conexao.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getCep());
			insert.setString(5, usuario.getRua());
			insert.setString(6, usuario.getBairro());
			insert.setString(7, usuario.getCidade());
			insert.setString(8, usuario.getEstado());
			insert.setString(9,usuario.getFotoBase64());
			insert.setString(10,usuario.getContentType());
			insert.setString(11, usuario.getContentTypeCurriculo());
			insert.setString(12, usuario.getCurriculo());
			insert.setBoolean(13,usuario.isAtivo());
			insert.setString(14, usuario.getSexo());
			insert.setString(15, usuario.getPerfil());
			
			insert.execute();
			conexao.commit();
		} catch (SQLException e) {
			try {
				conexao.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.println("Ocorreu um erro ao fazer o rollback");
				e1.printStackTrace();
			}
		}
		
		
	}
	
	public List<BeanUsuario> listar() {
		
		String sql = "SELECT cod, login, senha, nome, cep, rua, bairro, cidade, estado,  fotoBase64, contentType, "
				+ "contenttypecurriculo, curriculo, ativo, sexo,perfil  FROM usuarios WHERE login <> 'admin'";
		PreparedStatement statement;
		try {
			statement = conexao.prepareStatement(sql);
			ResultSet resultset =  statement.executeQuery();
			 List<BeanUsuario> lista= new ArrayList<BeanUsuario>();
			while(resultset.next()) {
				BeanUsuario login = new BeanUsuario();
				login.setId(resultset.getLong("cod"));
				login.setLogin(resultset.getString("login"));
				login.setNome(resultset.getString("nome"));
				login.setSenha(resultset.getString("senha"));
				login.setCep(resultset.getString("cep"));
				login.setRua(resultset.getString("rua"));
				login.setBairro(resultset.getString("bairro"));
				login.setCidade(resultset.getString("cidade"));
				login.setEstado(resultset.getString("estado"));
				login.setFotoBase64(resultset.getString("fotoBase64"));
				login.setContentType(resultset.getString("contentType"));
				login.setContentTypeCurriculo("contenttypecurriculo");
				login.setCurriculo("curriculo");
				login.setSexo(resultset.getString("sexo"));
				lista.add(login);
				
			}
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void delete(int login) {
		String sql =" DELETE FROM usuarios where cod=?";
		PreparedStatement prepared;
		try {
			prepared = conexao.prepareStatement(sql);
			prepared.setInt(1,login);
			prepared.execute();
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
	
		
	}
	
	public BeanUsuario consultar(int idUsuario) {
		String sql = "SELECT cod, login, senha, nome, cep, rua, bairro, cidade, estado, fotoBase64, contentType, curriculo, contenttypecurriculo, ativo, sexo,perfil  FROM usuarios WHERE cod=?";
		
		try {
			PreparedStatement prst = conexao.prepareStatement(sql);
			prst.setInt(1, idUsuario);
			ResultSet result = prst.executeQuery();
			if(result.next()) {
				BeanUsuario login = new BeanUsuario();
				
				login.setId(result.getLong("cod"));
				login.setLogin(result.getString("login"));
				login.setNome(result.getString("nome"));
				login.setSenha(result.getString("login"));
				login.setFotoBase64(result.getString("fotoBase64"));
				login.setContentType(result.getString("contentType"));
				login.setContentTypeCurriculo(result.getString("contenttypecurriculo"));
				login.setAtivo(result.getBoolean("ativo"));
				login.setCurriculo(result.getString("curriculo"));
				login.setSexo(result.getString("sexo"));
				login.setRua(result.getString("rua"));
				login.setBairro(result.getString("bairro"));
				login.setCep(result.getString("cep"));
				login.setCidade(result.getString("cidade"));
				login.setEstado(result.getString("estado"));
				login.setPerfil(result.getString("perfil"));
				return login;
			}
			return null;
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}

	public void atualizar(BeanUsuario usuario) {
		String sql = "UPDATE usuarios SET login=?, senha=?, nome=?, cep=?, rua=?, bairro=?, cidade=?, estado=?, "
				+ "fotoBase64 =? , contentType=?, curriculo=?, contenttypecurriculo=? , ativo=?, sexo=?, perfil=? WHERE cod=? and  login <> 'admin'";
		
		PreparedStatement prst;
		try {
			prst = conexao.prepareStatement(sql);
			
			prst.setString(1, usuario.getLogin());
			prst.setString(2, usuario.getSenha());
			prst.setString(3, usuario.getNome());
			prst.setString(4, usuario.getCep());
			prst.setString(5, usuario.getRua());
			prst.setString(6, usuario.getBairro());
			prst.setString(7, usuario.getCidade());
			prst.setString(8, usuario.getEstado());
			prst.setString(9, usuario.getFotoBase64());
			prst.setString(10, usuario.getContentType());
			prst.setString(11, usuario.getCurriculo());
			prst.setString(12 , usuario.getContentTypeCurriculo());
			prst.setBoolean(13, usuario.isAtivo());
			prst.setString(14, usuario.getSexo());
			prst.setString(15, usuario.getPerfil());
			prst.setLong(16, usuario.getId());
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
	
	
	public boolean validarLogin(String login) {
		String sql = "SELECT login FROM usuarios WHERE login=?";
		
		try {
			PreparedStatement prstm = conexao.prepareStatement(sql);
			prstm.setString(1, login.toLowerCase());
			ResultSet resultado =  prstm.executeQuery();
			
			
			if(resultado.next()) {
				return false;
				
			}else {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean validarLoginUpdate(String login, long id) {
		String sql = "SELECT login FROM usuarios WHERE login=? AND cod=?";
		
		try {
			PreparedStatement prstm = conexao.prepareStatement(sql);
			prstm.setString(1, login.toLowerCase());
			prstm.setLong(2,id);
			ResultSet resultado =  prstm.executeQuery();
			
			
			if(resultado.next()) {
				return true;
				
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
}
