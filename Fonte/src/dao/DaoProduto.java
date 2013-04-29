package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Conexao;
import model.Produto;

public class DaoProduto {
	private Conexao conexao;
	private Statement smtm;
	private DaoCaracteristicasProduto daoCaracteristicasProduto; 
	
	public DaoProduto(Conexao conexao) {		
		try {
			this.conexao = conexao;
			this.smtm = this.conexao.getConexao().createStatement();
			this.daoCaracteristicasProduto = new DaoCaracteristicasProduto(this.conexao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public void inserir(Produto produto) throws SQLException{
		String sql = "INSERT INTO Produtos "+
	                 "(nome, precoDeCompra, precoDeVenda, valorDesconto, "+
	                 "informacoes, quantidadeDisponivel) "+
	                 "VALUES ("+	                 
	                 produto.getNome()+","+
	                 produto.getPrecoDeCompra()+","+
	                 produto.getPrecoDeVenda()+","+
	                 produto.getValorDesconto()+","+
	                 produto.getInformacoes()+","+
	                 produto.getQuantidadeDisponivel()+")RETURNING id";
		
		ResultSet rs = smtm.executeQuery(sql);
		
		this.daoCaracteristicasProduto.inserir(rs.getInt("id"),produto.getCaracteristicas());
	}
	
	public void atualizar(Produto produto) throws SQLException{
		String sql = "UPDATE Produtos SET "+
	                 "nome="+produto.getNome()+
	                 "precoDeCompra="+produto.getPrecoDeCompra()+
	                 "precoDeVenda="+produto.getPrecoDeVenda()+
	                 "valorDesconto="+produto.getValorDesconto()+
	                 "informacoes="+produto.getInformacoes()+
	                 "quantidadeDisponivel="+produto.getQuantidadeDisponivel()+" "+
	                 "WHERE id="+produto.getId();								
		
		ResultSet rs = smtm.executeQuery(sql);
		
		this.daoCaracteristicasProduto.excluir(produto.getId());
		this.daoCaracteristicasProduto.inserir(rs.getInt("id"),produto.getCaracteristicas());
	}
	
	public void excluir(int id) throws SQLException{
		String sql = "DELETE FROM Produtos "+ 
	                 "WHERE id="+id;										
		smtm.executeUpdate(sql);		
		
		this.daoCaracteristicasProduto.excluir(id);
	}
	
	public List<Produto> buscar(int id) throws SQLException{
		String sql = "Select * FROM Produtos "+ 
	                 "WHERE id="+id;										
		ResultSet rsProdutos = smtm.executeQuery(sql);
		
		List<Produto> produtos = new ArrayList<Produto>();
		if(rsProdutos != null){
			while(rsProdutos.next()){
				Produto produto = new Produto();
				produto.setId(rsProdutos.getInt("id"));
				produto.setNome(rsProdutos.getString("nome"));
				produto.setPrecoDeCompra(rsProdutos.getDouble("precoDeCompra"));
				produto.setPrecoDeVenda(rsProdutos.getDouble("precodeVenda"));
				produto.setValorDesconto(rsProdutos.getDouble("valorDesconto"));
				produto.setInformacoes(rsProdutos.getString("informacoes"));
				produto.setQuantidadeDisponivel(rsProdutos.getInt("quantidadeDisponivel"));
				produto.setFotos(rsProdutos.getString("foto"));				
				produto.setCaracteristicas(this.daoCaracteristicasProduto.buscar(produto.getId()));
				
				produtos.add(produto);
			}
			rsProdutos.close();
		}
		return produtos;
	}		
}
