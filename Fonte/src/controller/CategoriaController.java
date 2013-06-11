package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.Categoria;
import model.Conexao;
import dao.DaoCategoria;

public class CategoriaController {
	private DaoCategoria daoCategoria;
	private Connection connection;
	
	public CategoriaController() throws SQLException {
		this.connection = Conexao.getConexao();
		this.daoCategoria = new DaoCategoria(this.connection);
	}
	
	public void inserir(Categoria categoria) throws SQLException{
		this.daoCategoria.inserir(categoria);
	}
	
	public void atualizar(Categoria categoria) throws SQLException{
		this.daoCategoria.atualizar(categoria);
	}
	
	public void excluir(int id) throws SQLException{
		this.daoCategoria.excluir(id);
	}
	
	public Categoria buscar(int id) throws SQLException{
		return this.daoCategoria.buscar(id);
	}
	
	public List<Categoria> buscar() throws SQLException{
		return this.daoCategoria.buscar();
	}
}
