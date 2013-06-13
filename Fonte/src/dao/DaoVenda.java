package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	private PreparedStatement pstm;
	
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
	                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, venda.getId());
		pstm.setDate(2, (Date)venda.getData());
		pstm.setDouble(3, venda.getValor());
		pstm.setDouble(4, venda.getValorFrete());
		pstm.setString(5, venda.getTipoPagamento());
		pstm.setInt(6, venda.getQuantidadeParcelas());
		pstm.setInt(7, venda.getUsuario().getId());
	                 
		pstm.execute();
		
		for (ItemVenda itemVenda : venda.getItensVendas()) {
			daoItensVendas.inserir(venda.getId(), itemVenda);
		}
		
	}
	
	public Venda buscar(int id) throws SQLException{
		String sql = "Select * FROM Vendas "+ 
	                 "WHERE id=?";
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, id);
		
		ResultSet rsVenda = pstm.executeQuery();
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
