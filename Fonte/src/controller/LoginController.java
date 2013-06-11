package controller;

import java.sql.Connection;
import java.sql.SQLException;

import model.Conexao;
import model.TipoUsuario;
import model.Usuario;
import dao.DaoUsuario;

public class LoginController {
	private DaoUsuario daoUsuario;
	private Connection connection;
	
	public LoginController() throws SQLException {
		this.connection = Conexao.getConexao();
		this.daoUsuario = new DaoUsuario(connection);
	}
	
	public Usuario logar(String email, String senha) throws SQLException{
		return daoUsuario.buscar(email, senha);
	}
	
	public boolean usuarioEhDoTipoAdministrador(Usuario usuario){
		if(usuario.getTipo().equals(TipoUsuario.ADMINISTRADOR)){
			return true;
		}
		else{
			return false;
		}
	}
}
