package controller;

import java.sql.Connection;
import java.sql.SQLException;

import dao.DaoUsuario;
import model.Conexao;
import model.Usuario;

public class UsuarioController {
	private Connection connection;
	private DaoUsuario daoUsuario;
	
	public UsuarioController() throws SQLException {
		this.connection = Conexao.getConexao();
		this.daoUsuario = new DaoUsuario(this.connection);
	}
	
	public void inserir(Usuario usuario) throws SQLException{
		this.daoUsuario.inserir(usuario);
	}
	
	public void atualizar(Usuario usuario) throws SQLException{
		this.daoUsuario.atualizar(usuario);
	}
	
	public void excluir(int id) throws SQLException{
		this.daoUsuario.excluir(id);
	}
	
	public Usuario buscar(int id) throws SQLException{		
		return this.daoUsuario.buscar(id);
	}
	

}
