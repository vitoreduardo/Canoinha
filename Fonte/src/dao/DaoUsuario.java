package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.TipoUsuario;
import model.Usuario;
import model.Conexao;
import model.Endereco;
import model.Produto;

public class DaoUsuario extends Dao{
	private Conexao conexao;
	private Statement smtm;
	
	public DaoUsuario(Conexao conexao) throws SQLException {
		super(conexao);
		this.conexao = conexao;
		this.smtm = this.conexao.getConexao().createStatement();
	}
	
	public void inserir(Usuario usuario) throws SQLException{
		usuario.setId(gerarSequencia("clientes_id_seq"));
		
		String sql = "INSERT INTO Usuarios "+
	                 "(id, nome, cpf, email, senha, datanascimento, "+
	                 "rua, bairro, numero, cep, cidade, uf, tipo) "+
	                 "VALUES ("+
	                 usuario.getId()+","+
	                 aspasSimples(usuario.getNome())+","+
	                 aspasSimples(usuario.getCpf())+","+
	                 aspasSimples(usuario.getEmail())+","+
	                 aspasSimples(usuario.getSenha())+","+
	                 aspasSimples(formatarData(usuario.getDataNascimento()))+","+
               		 aspasSimples(usuario.getEndereco().getRua())+","+
               		 aspasSimples(usuario.getEndereco().getBairro())+","+
               		 aspasSimples(usuario.getEndereco().getNumero())+","+
               		 aspasSimples(usuario.getEndereco().getCep())+","+
               		 aspasSimples(usuario.getEndereco().getCidade())+","+
               		 aspasSimples(usuario.getEndereco().getUf())+","+
	                 aspasSimples(usuario.getTipo().toString())+")" ;
		
		smtm.executeUpdate(sql);		
	}
	
	public void atualizar(Usuario usuario) throws SQLException{
		String sql = "UPDATE Usuarios SET "+
				     "id="+usuario.getId()+", " +
				     "nome="+aspasSimples(usuario.getNome())+", " +
				     "cpf="+aspasSimples(usuario.getCpf())+", " +
				     "email="+aspasSimples(usuario.getEmail())+", " +
				     "senha="+aspasSimples(usuario.getSenha())+", " +
				     "datanascimento="+aspasSimples(formatarData(usuario.getDataNascimento()))+", "+
                     "rua="+aspasSimples(usuario.getEndereco().getRua())+", " +
                     "bairro="+aspasSimples(usuario.getEndereco().getBairro())+", " +
                     "numero="+aspasSimples(usuario.getEndereco().getNumero())+", " +
                     "cep="+aspasSimples(usuario.getEndereco().getCep())+", " +
                     "cidade="+aspasSimples(usuario.getEndereco().getCidade())+", " +
                     "uf="+aspasSimples(usuario.getEndereco().getUf())+", " +
                     "tipo="+aspasSimples(usuario.getTipo().toString())+" " +
	                 "WHERE id="+usuario.getId();								
		
		smtm.executeUpdate(sql);
	}
	
	public void excluir(int id) throws SQLException{
		String sql = "DELETE FROM Usuarios "+ 
	                 "WHERE id="+id;										
		smtm.executeUpdate(sql);				
	}	
	
	public Usuario buscar(int id) throws SQLException{
		String sql = "Select * FROM Usuarios "+ 
	                 "WHERE id="+id;										
		ResultSet rsUsuarios = smtm.executeQuery(sql);
		Usuario usuario = new Usuario();
		
		if(rsUsuarios != null){
			while(rsUsuarios.next()){				
				usuario.setId(rsUsuarios.getInt("id"));
				usuario.setNome(rsUsuarios.getString("nome"));
				usuario.setCpf(rsUsuarios.getString("cpf"));
				usuario.setEmail(rsUsuarios.getString("email"));
				usuario.setSenha(rsUsuarios.getString("senha"));
				usuario.setDataNascimento(rsUsuarios.getDate("dataNascimento"));
				if (rsUsuarios.getString("tipo").equalsIgnoreCase("Administrador")){
					usuario.setTipo(TipoUsuario.ADMINISTRADOR);
				}
				else{
					usuario.setTipo(TipoUsuario.USUARIO);
				}
				Endereco endereco = new Endereco();
				endereco.setRua(rsUsuarios.getString("rua"));
				endereco.setBairro(rsUsuarios.getString("bairro"));
				endereco.setNumero(rsUsuarios.getString("numero"));
				endereco.setCep(rsUsuarios.getString("cep"));
				endereco.setCidade(rsUsuarios.getString("cidade"));
				endereco.setUf(rsUsuarios.getString("uf"));
				usuario.setEndereco(endereco);								
			}
			rsUsuarios.close();
		}
		return usuario;
	}
	
	public Usuario buscar(String email, String senha) throws SQLException{
		String sql = "Select * FROM Usuarios "+ 
	                 "WHERE email="+aspasSimples(email)+" AND "+
				     "      senha="+aspasSimples(senha);
		ResultSet rsUsuarios = smtm.executeQuery(sql);
		Usuario usuario = null;
		
		if(rsUsuarios != null){
			while(rsUsuarios.next()){
				usuario = new Usuario();
				usuario.setId(rsUsuarios.getInt("id"));
				usuario.setNome(rsUsuarios.getString("nome"));
				usuario.setCpf(rsUsuarios.getString("cpf"));
				usuario.setEmail(rsUsuarios.getString("email"));
				usuario.setSenha(rsUsuarios.getString("senha"));
				usuario.setDataNascimento(rsUsuarios.getDate("dataNascimento"));
				if (rsUsuarios.getString("tipo").equalsIgnoreCase("Administrador")){
					usuario.setTipo(TipoUsuario.ADMINISTRADOR);
				}
				else{
					usuario.setTipo(TipoUsuario.USUARIO);
				}
				Endereco endereco = new Endereco();
				endereco.setRua(rsUsuarios.getString("rua"));
				endereco.setBairro(rsUsuarios.getString("bairro"));
				endereco.setNumero(rsUsuarios.getString("numero"));
				endereco.setCep(rsUsuarios.getString("cep"));
				endereco.setCidade(rsUsuarios.getString("cidade"));
				endereco.setUf(rsUsuarios.getString("uf"));
				usuario.setEndereco(endereco);								
			}
			rsUsuarios.close();
		}
		return usuario;
	}
	
	public List<Usuario> buscar() throws SQLException{
		String sql = "Select * FROM Usuarios ";
		
		ResultSet rsUsuarios = smtm.executeQuery(sql);
		Usuario usuario = new Usuario();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		if(rsUsuarios != null){
			while(rsUsuarios.next()){				
				usuario.setId(rsUsuarios.getInt("id"));
				usuario.setNome(rsUsuarios.getString("nome"));
				usuario.setCpf(rsUsuarios.getString("cpf"));
				usuario.setEmail(rsUsuarios.getString("email"));
				usuario.setSenha(rsUsuarios.getString("senha"));
				usuario.setDataNascimento(rsUsuarios.getDate("dataNascimento"));
				if (rsUsuarios.getString("tipo").equalsIgnoreCase("Administrador")){
					usuario.setTipo(TipoUsuario.ADMINISTRADOR);
				}
				else{
					usuario.setTipo(TipoUsuario.USUARIO);
				}
				Endereco endereco = new Endereco();
				endereco.setRua(rsUsuarios.getString("rua"));
				endereco.setBairro(rsUsuarios.getString("bairro"));
				endereco.setNumero(rsUsuarios.getString("numero"));
				endereco.setCep(rsUsuarios.getString("cep"));
				endereco.setCidade(rsUsuarios.getString("cidade"));
				endereco.setUf(rsUsuarios.getString("uf"));
				usuario.setEndereco(endereco);
				
				usuarios.add(usuario);
			}
			rsUsuarios.close();
		}
		return usuarios;
	}	
}
