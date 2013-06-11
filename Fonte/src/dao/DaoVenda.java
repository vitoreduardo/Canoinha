package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Conexao;
import model.ItemVenda;
import model.Venda;


public class DaoVenda extends Dao{
	private Connection connection;
	private Statement smtm;
	private DaoItemVenda daoItensVendas;
	
	public DaoVenda(Connection connection) throws SQLException {
		super(connection);
		this.connection = connection;
		this.smtm = this.connection.createStatement();
		this.daoItensVendas = new DaoItemVenda(connection);
	}
	
	public void inserir(Venda venda) throws SQLException{
		venda.setId(gerarSequencia("vendas_id_seq"));
		
		String sql = "INSERT INTO Vendas "+
	                 "(id, dataHora, valor, valorFrete, tipoPagamento, quantidadeParcelas, Usuarios_id) "+
	                 "VALUES ("+
	                 venda.getId()+","+
	                 aspasSimples(formatarData(venda.getData()))+","+
               		 venda.getValor()+","+
               		 venda.getValorFrete()+","+
               		 aspasSimples(venda.getTipoPagamento())+","+
               		 venda.getQuantidadeParcelas()+"," +
					 venda.getUsuario().getId()+")" ;
		
		smtm.executeUpdate(sql);
		
		for (ItemVenda itemVenda : venda.getItensVendas()) {
			daoItensVendas.inserir(venda.getId(), itemVenda);
		}
		
	}
	
	public Venda buscar(int id) throws SQLException{
		String sql = "Select * FROM Vendas "+ 
	                 "WHERE id="+id;										
		ResultSet rsVenda = smtm.executeQuery(sql);
		Venda venda = new Venda();
		
		if(rsVenda != null){
			while(rsVenda.next()){				
				venda.setId(id);
				venda.setData(rsVenda.getDate("data"));
				venda.setValor(rsVenda.getDouble("valor"));
				venda.setValorFrete(rsVenda.getDouble("valorFrete"));
				venda.setTipoPagamento(rsVenda.getString("TipoPagamento"));
				venda.setQuantidadeParcelas(rsVenda.getInt("quantidadeParcelas"));
				venda.setItensVendas(daoItensVendas.buscarPelaVenda(venda.getId()));
			}
			rsVenda.close();
		}
		return venda;
	}

}
