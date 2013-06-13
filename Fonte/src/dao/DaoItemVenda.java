package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Conexao;
import model.ItemVenda;

public class DaoItemVenda extends Dao{
	private Connection connection;
	private Statement smtm;
	private DaoProduto daoProduto;
	private PreparedStatement pstm;
	
	public DaoItemVenda(Connection connection) throws SQLException {
		super(connection);
		this.connection = connection;
		this.smtm = this.connection.createStatement();
		this.daoProduto = new DaoProduto(connection);		
	}
	
	public void inserir(int idVenda, ItemVenda itemVenda) throws SQLException{
		itemVenda.setId(gerarSequencia("itensvendas_id_seq"));
		
		String sql = "INSERT INTO ItensVendas "+
	                 "(id, produtos_id, vendas_id, valor, quantidade) "+
	                 "VALUES (?, ?, ?, ?, ?)";
	    pstm = connection.prepareStatement(sql);
	    pstm.setInt(1, itemVenda.getId());
	    pstm.setInt(2, itemVenda.getProduto().getId());
	    pstm.setInt(3, idVenda);
	    pstm.setDouble(4, itemVenda.getValor());
	    pstm.setInt(5, itemVenda.getQuantidade());
		       		 
	    pstm.execute();				
	}
	
	public ItemVenda buscar(int id) throws SQLException{
		String sql = "Select * FROM ItensVendas "+ 
	                 "WHERE id=?";
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rsItensVendas = pstm.executeQuery();
		ItemVenda itensVendas = new ItemVenda();
		
		if(rsItensVendas != null){
			while(rsItensVendas.next()){				
				itensVendas.setId(id);				
				itensVendas.setProduto(daoProduto.buscar(rsItensVendas.getInt("produtos_id")));
				itensVendas.setValor(rsItensVendas.getDouble("valor"));
				itensVendas.setQuantidade(rsItensVendas.getInt("quantidade"));			
			}
			rsItensVendas.close();
		}
		return itensVendas;
	}
	
	public List<ItemVenda> buscarPelaVenda(int idVenda) throws SQLException{
		String sql = "Select * FROM ItensVendas "+ 
	                 "WHERE vendas_id=?";
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, idVenda);
		ResultSet rsItensVendas = pstm.executeQuery();
		List<ItemVenda> itensDeVendas = new ArrayList<ItemVenda>();
		
		if(rsItensVendas != null){
			while(rsItensVendas.next()){
				ItemVenda itemVenda = new ItemVenda();
				itemVenda.setId(rsItensVendas.getInt("id"));				
				itemVenda.setProduto(daoProduto.buscar(rsItensVendas.getInt("produtos_id")));
				itemVenda.setValor(rsItensVendas.getDouble("valor"));
				itemVenda.setQuantidade(rsItensVendas.getInt("quantidade"));
				itensDeVendas.add(itemVenda);
			}
			rsItensVendas.close();
		}
		return itensDeVendas;
	}

}
