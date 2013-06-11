package controller;

import java.sql.Connection;
import java.sql.SQLException;

import model.Conexao;
import model.Venda;
import dao.DaoVenda;

public class VendaController {
	private DaoVenda daoVenda;
	private Connection connection;
	
	public VendaController() throws SQLException {
		this.connection = Conexao.getConexao();
		this.daoVenda = new DaoVenda(this.connection);
	}
	
	public void inserir(Venda venda) throws SQLException{
		this.daoVenda.inserir(venda);
	}
	
	public Venda buscar(int id) throws SQLException{
		return this.daoVenda.buscar(id);
	}

}
