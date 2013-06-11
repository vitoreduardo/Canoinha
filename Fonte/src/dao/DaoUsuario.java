package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private Connection connection;
	private Statement smtm;
	private PreparedStatement pstm;
	
	public DaoUsuario(Connection connection) throws SQLException {
		super(connection);
		this.connection = connection;
		this.smtm = this.connection.createStatement();
	}
	
	public void inserir(Usuario usuario) throws SQLException{
		usuario.setId(gerarSequencia("usuarios_id_seq"));
		String sql = "INSERT INTO Usuarios "+
	                 "(id, nome, cpf, email, senha, data, "+
	                 "rua, bairro, numero, cep, cidade, uf, tipo) "+
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, usuario.getId());
		pstm.setString(2, usuario.getNome());
		pstm.setString(3, usuario.getCpf());
		pstm.setString(4, usuario.getEmail());
		pstm.setString(5, usuario.getSenha());
		//pstm.setString(6, "22071996");
		pstm.setDate(6, new java.sql.Date(usuario.getDataNascimento().getTime()));
		pstm.setString(7, usuario.getEndereco().getRua());
		pstm.setString(8, usuario.getEndereco().getBairro());
		pstm.setString(9, usuario.getEndereco().getNumero());
		pstm.setString(10, usuario.getEndereco().getCep());
		pstm.setString(11, usuario.getEndereco().getCidade());
		pstm.setString(12, usuario.getEndereco().getUf());
		pstm.setString(13, usuario.getTipo().name());
		
		pstm.execute();
	}
	
	public void atualizar(Usuario usuario) throws SQLException{
		String sql = "UPDATE Usuarios SET "+
				     "id="+usuario.getId()+", " +
				     "nome="+aspasSimples(usuario.getNome())+", " +
				     "cpf="+aspasSimples(usuario.getCpf())+", " +
				     "email="+aspasSimples(usuario.getEmail())+", " +
				     "senha="+aspasSimples(usuario.getSenha())+", " +
				     "datanascimento="+aspasSimples("22071996")+", "+
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
//				usuario.setDataNascimento(rsUsuarios.getDate("dataNascimento"));
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
				//usuario.setDataNascimento("22071996");
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

		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		if(rsUsuarios != null){
			while(rsUsuarios.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rsUsuarios.getInt("id"));
				usuario.setNome(rsUsuarios.getString("nome"));
				usuario.setCpf(rsUsuarios.getString("cpf"));
				usuario.setEmail(rsUsuarios.getString("email"));
				usuario.setSenha(rsUsuarios.getString("senha"));
				//usuario.setDataNascimento( java.util.Date.parse(rsUsuarios.getString("dataNascimento")));
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
