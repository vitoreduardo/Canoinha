package controller;

import java.sql.SQLException;

import model.Conexao;
import model.TipoUsuario;
import model.Usuario;
import dao.DaoUsuario;

public class LoginController {
	private DaoUsuario daoUsuario;
	private Conexao conexao;
	
	public LoginController() throws SQLException {
		this.conexao = new Conexao();
		this.daoUsuario = new DaoUsuario(conexao);
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
