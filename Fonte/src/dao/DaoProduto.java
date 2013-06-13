package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Conexao;
import model.Produto;

public class DaoProduto extends Dao {
	private Connection connection;
	private Statement smtm;
	private PreparedStatement pstm;
	private DaoCaracteristicasProduto daoCaracteristicasProduto; 
	private DaoCategoria daoCategoria;
	
	public DaoProduto(Connection connection) {
		super(connection);
		try {
			this.connection = connection;
			this.smtm = this.connection.createStatement();
			this.daoCaracteristicasProduto = new DaoCaracteristicasProduto(this.connection);
			this.daoCategoria = new DaoCategoria(this.connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public List<Produto> buscarPorCategoria(int idCategoria) throws SQLException{
		String sql = "Select * FROM Produtos where categorias_id=?";
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, idCategoria);
		ResultSet rsProdutos = pstm.executeQuery();
		
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
				Categoria categoria = this.daoCategoria.buscar(idCategoria); 
				produto.setCategoria(categoria);
				produtos.add(produto);
			}
			rsProdutos.close();
		}
		return produtos;
	}
	
	public void inserir(Produto produto) throws SQLException{
		produto.setId(gerarSequencia("produtos_id_seq"));
		
		String sql = "INSERT INTO Produtos "+
	                 "(id, nome, precoDeCompra, precoDeVenda, valorDesconto, "+
	                 "informacoes, quantidadeDisponivel, foto, categorias_id) "+
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, produto.getId());
		pstm.setString(2, produto.getNome());
		pstm.setDouble(3, produto.getPrecoDeCompra());
		pstm.setDouble(4, produto.getPrecoDeVenda());
		pstm.setDouble(5, produto.getValorDesconto());
		pstm.setString(6, produto.getInformacoes());
		pstm.setInt(7, produto.getQuantidadeDisponivel());
		pstm.setString(8, produto.getFotos());
		pstm.setInt(9, produto.getCategoria().getId());
		
		pstm.execute();
		
		if(produto.getCaracteristicas().size() > 0){
			this.daoCaracteristicasProduto.inserir(produto.getId(),produto.getCaracteristicas());
		}
	}
	
	public void alterarsaldo(int idProduto, int saldo) throws SQLException{
		String sql = "UPDATE Produtos SET "+
                	 "quantidadeDisponivel="+saldo+" "+
                     "WHERE id="+idProduto;								
	 
    	smtm.executeUpdate(sql);
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
