package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {
	private Connection conexao;

	public DaoProduto() {
		// TODO Auto-generated constructor stub

		conexao = SingleConnection.getConexao();
	}

	public void inserir(BeanProduto produto) {
		try {
			String sql = "INSERT INTO produto (nome, qtd, preco) VALUES (?,?,?)";
			PreparedStatement prstm = conexao.prepareStatement(sql);
			prstm.setString(1, produto.getNome());
			prstm.setDouble(2, produto.getQtd());
			prstm.setDouble(3, produto.getPreco());
			prstm.execute();
			conexao.commit();

		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// TODO: handle exception
		}

	}

	public List<BeanProduto> listar() {
		String sql = "SELECT id, nome, preco, qtd FROM produto";
		try {
			List<BeanProduto> produtos = new ArrayList<BeanProduto>();
			PreparedStatement prpstm = conexao.prepareStatement(sql);
			ResultSet resultado = prpstm.executeQuery();
			while (resultado.next()) {
				BeanProduto produto = new BeanProduto();
				produto.setId(resultado.getLong("id"));
				produto.setNome(resultado.getString("nome"));
				produto.setPreco(resultado.getDouble("preco"));
				produto.setQtd(resultado.getDouble("qtd"));
				produtos.add(produto);
			}
			return produtos;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void update(BeanProduto produto) {
		String sql = "UPDATE produto SET nome=?,preco=?,qtd=? WHERE id=?";
		try {

			PreparedStatement prstm = conexao.prepareStatement(sql);
			prstm.setString(1, produto.getNome());
			prstm.setDouble(2, produto.getPreco());
			prstm.setDouble(3, produto.getQtd());
			prstm.setLong(4, produto.getId());
			prstm.executeUpdate();
			conexao.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void delete(Long id) {
		String sql = " DELETE FROM produto where id=?";
		try {
			PreparedStatement prepared= conexao.prepareStatement(sql);;

			
			prepared.setLong(1, id);
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
	public BeanProduto listarPeloId(Long id) {
		String sql = "SELECT id, nome, preco, qtd FROM produto WHERE id = ?";
		try {
			PreparedStatement prpstm = conexao.prepareStatement(sql);
			prpstm.setLong(1, id);
			BeanProduto produto = new BeanProduto();
			ResultSet resultado = prpstm.executeQuery();
			while (resultado.next()) {
			
				produto.setId(resultado.getLong("id"));
				produto.setNome(resultado.getString("nome"));
				produto.setPreco(resultado.getDouble("preco"));
				produto.setQtd(resultado.getDouble("qtd"));
				
			}
			return produto;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
