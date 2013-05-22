package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Categoria;
import model.Conexao;
import model.Produto;

public class DaoProduto extends Dao {
	private Conexao conexao;
	private Statement smtm;
	private DaoCaracteristicasProduto daoCaracteristicasProduto; 
	private DaoCategoria daoCategoria;
	
	public DaoProduto(Conexao conexao) {
		super(conexao);
		try {
			this.conexao = conexao;
			this.smtm = this.conexao.getConexao().createStatement();
			this.daoCaracteristicasProduto = new DaoCaracteristicasProduto(this.conexao);
			this.daoCategoria = new DaoCategoria(this.conexao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public void inserir(Produto produto) throws SQLException{
		produto.setId(gerarSequencia("produtos_id_seq"));
		
		String sql = "INSERT INTO Produtos "+
	                 "(id, nome, precoDeCompra, precoDeVenda, valorDesconto, "+
	                 "informacoes, quantidadeDisponivel, foto, categorias_id) "+
	                 "VALUES ("+
	                 produto.getId()+","+
	                 aspasSimples(produto.getNome())+","+
	                 produto.getPrecoDeCompra()+","+
	                 produto.getPrecoDeVenda()+","+
	                 produto.getValorDesconto()+","+
	                 aspasSimples(produto.getInformacoes())+","+
	                 produto.getQuantidadeDisponivel()+","+
	                 aspasSimples(produto.getFotos())+","+
	                 produto.getCategoria().getId()+")" ;
		
		smtm.executeUpdate(sql);
		
		if(produto.getCaracteristicas().size() > 0){
			this.daoCaracteristicasProduto.inserir(produto.getId(),produto.getCaracteristicas());
		}
	}
	
	public void atualizar(Produto produto) throws SQLException{
		String sql = "UPDATE Produtos SET "+
	                 "nome="+aspasSimples(produto.getNome())+", "+
	                 "precoDeCompra="+produto.getPrecoDeCompra()+", "+
	                 "precoDeVenda="+produto.getPrecoDeVenda()+", "+
	                 "valorDesconto="+produto.getValorDesconto()+", "+
	                 "informacoes="+aspasSimples(produto.getInformacoes())+", "+
	                 "foto="+aspasSimples(produto.getFotos())+", "+
	                 "quantidadeDisponivel="+produto.getQuantidadeDisponivel()+", "+
	                 "categorias_id="+produto.getCategoria().getId()+" "+
	                 "WHERE id="+produto.getId();								
		
		smtm.executeUpdate(sql);
		
		this.daoCaracteristicasProduto.excluir(produto.getId());
		this.daoCaracteristicasProduto.inserir(produto.getId(),produto.getCaracteristicas());
	}
	
	public void excluir(int id) throws SQLException{
		this.daoCaracteristicasProduto.excluir(id);
		String sql = "DELETE FROM Produtos "+ 
	                 "WHERE id="+id;										
		smtm.executeUpdate(sql);				
	}
	
	public Produto buscar(int id) throws SQLException{
		String sql = "Select * FROM Produtos "+ 
	                 "WHERE id="+id;										
		ResultSet rsProdutos = smtm.executeQuery(sql);
		Produto produto = new Produto();
		
		if(rsProdutos != null){
			while(rsProdutos.next()){				
				produto.setId(rsProdutos.getInt("id"));
				produto.setNome(rsProdutos.getString("nome"));
				produto.setPrecoDeCompra(rsProdutos.getDouble("precoDeCompra"));
				produto.setPrecoDeVenda(rsProdutos.getDouble("precodeVenda"));
				produto.setValorDesconto(rsProdutos.getDouble("valorDesconto"));
				produto.setInformacoes(rsProdutos.getString("informacoes"));
				produto.setQuantidadeDisponivel(rsProdutos.getInt("quantidadeDisponivel"));
				produto.setFotos(rsProdutos.getString("foto"));				
				produto.setCaracteristicas(this.daoCaracteristicasProduto.buscar(produto.getId()));				
				produto.setCategoria(this.daoCategoria.buscar(rsProdutos.getInt("categorias_id")));
			}
			rsProdutos.close();
		}
		return produto;
	}
	
	public List<Produto> buscar() throws SQLException{
		String sql = "Select * FROM Produtos ";
		
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
				int idCategoria = rsProdutos.getInt("categorias_id");
				Categoria categoria = this.daoCategoria.buscar(idCategoria); 
				produto.setCategoria(categoria);
				produtos.add(produto);
			}
			rsProdutos.close();
		}
		return produtos;
	}
}
