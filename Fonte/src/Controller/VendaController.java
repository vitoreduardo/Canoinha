package Controller;

import java.sql.SQLException;

import model.Conexao;
import model.Venda;
import dao.DaoVenda;

public class VendaController {
	private DaoVenda daoVenda;
	private Conexao conexao;
	
	public VendaController() throws SQLException {
		this.conexao = new Conexao();
		this.daoVenda = new DaoVenda(this.conexao);
	}
	
	public void inserir(Venda venda) throws SQLException{
		this.daoVenda.inserir(venda);
	}
	
	public Venda buscar(int id) throws SQLException{
		return this.daoVenda.buscar(id);
	}

}
