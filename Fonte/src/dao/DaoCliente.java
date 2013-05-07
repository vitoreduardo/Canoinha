package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Conexao;
import model.Endereco;
import model.Produto;

public class DaoCliente extends Dao{
	private Conexao conexao;
	private Statement smtm;
	
	public DaoCliente(Conexao conexao) {
		super(conexao);
		this.conexao = conexao;
	}
	
	public void inserir(Cliente cliente) throws SQLException{
		cliente.setId(gerarSequencia("clientes_id_seq"));
		
		String sql = "INSERT INTO Clientes "+
	                 "(id, nome, cpf, email, senha, datanascimento, "+
	                 "rua, bairro, numero, cep, cidade, uf) "+
	                 "VALUES ("+
	                 cliente.getId()+","+
	                 aspasSimples(cliente.getNome())+","+
	                 aspasSimples(cliente.getCpf())+","+
	                 aspasSimples(cliente.getEmail())+","+
	                 aspasSimples(cliente.getSenha())+","+
	                 aspasSimples(formatarData(cliente.getDataNascimento()))+","+
               		 aspasSimples(cliente.getEndereco().getRua())+","+
               		 aspasSimples(cliente.getEndereco().getBairro())+","+
               		 aspasSimples(cliente.getEndereco().getNumero())+","+
               		 aspasSimples(cliente.getEndereco().getCep())+","+
               		 aspasSimples(cliente.getEndereco().getCidade())+","+
	                 aspasSimples(cliente.getEndereco().getUf())+")" ;
		
		smtm.executeUpdate(sql);		
	}
	
	public void atualizar(Cliente cliente) throws SQLException{
		String sql = "UPDATE Clientes SET "+
				     "id="+cliente.getId()+", " +
				     "nome="+aspasSimples(cliente.getNome())+", " +
				     "cpf="+aspasSimples(cliente.getCpf())+", " +
				     "email="+aspasSimples(cliente.getEmail())+", " +
				     "senha="+aspasSimples(cliente.getSenha())+", " +
				     "datanascimento="+aspasSimples(formatarData(cliente.getDataNascimento()))+", "+
                     "rua="+aspasSimples(cliente.getEndereco().getRua())+", " +
                     "bairro="+aspasSimples(cliente.getEndereco().getBairro())+", " +
                     "numero="+aspasSimples(cliente.getEndereco().getNumero())+", " +
                     "cep="+aspasSimples(cliente.getEndereco().getCep())+", " +
                     "cidade="+aspasSimples(cliente.getEndereco().getCidade())+", " +
                     "uf="+aspasSimples(cliente.getEndereco().getUf())+" " +
	                 "WHERE id="+cliente.getId();								
		
		smtm.executeUpdate(sql);
	}
	
	public void excluir(int id) throws SQLException{
		String sql = "DELETE FROM Clientes "+ 
	                 "WHERE id="+id;										
		smtm.executeUpdate(sql);				
	}	
	
	public Cliente buscar(int id) throws SQLException{
		String sql = "Select * FROM Clientes "+ 
	                 "WHERE id="+id;										
		ResultSet rsClientes = smtm.executeQuery(sql);
		Cliente cliente = new Cliente();
		
		if(rsClientes != null){
			while(rsClientes.next()){				
				cliente.setId(rsClientes.getInt("id"));
				cliente.setNome(rsClientes.getString("nome"));
				cliente.setCpf(rsClientes.getString("cpf"));
				cliente.setEmail(rsClientes.getString("email"));
				cliente.setSenha(rsClientes.getString("senha"));
				cliente.setDataNascimento(rsClientes.getDate("dataNascimento"));
				Endereco endereco = new Endereco();
				endereco.setRua(rsClientes.getString("rua"));
				endereco.setBairro(rsClientes.getString("bairro"));
				endereco.setNumero(rsClientes.getString("numero"));
				endereco.setCep(rsClientes.getString("cep"));
				endereco.setCidade(rsClientes.getString("cidade"));
				endereco.setUf(rsClientes.getString("uf"));
				cliente.setEndereco(endereco);								
			}
			rsClientes.close();
		}
		return cliente;
	}
	
	public List<Cliente> buscar() throws SQLException{
		String sql = "Select * FROM Clientes ";
		
		ResultSet rsClientes = smtm.executeQuery(sql);
		Cliente cliente = new Cliente();
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		if(rsClientes != null){
			while(rsClientes.next()){				
				cliente.setId(rsClientes.getInt("id"));
				cliente.setNome(rsClientes.getString("nome"));
				cliente.setCpf(rsClientes.getString("cpf"));
				cliente.setEmail(rsClientes.getString("email"));
				cliente.setSenha(rsClientes.getString("senha"));
				cliente.setDataNascimento(rsClientes.getDate("dataNascimento"));
				Endereco endereco = new Endereco();
				endereco.setRua(rsClientes.getString("rua"));
				endereco.setBairro(rsClientes.getString("bairro"));
				endereco.setNumero(rsClientes.getString("numero"));
				endereco.setCep(rsClientes.getString("cep"));
				endereco.setCidade(rsClientes.getString("cidade"));
				endereco.setUf(rsClientes.getString("uf"));
				cliente.setEndereco(endereco);
				
				clientes.add(cliente);
			}
			rsClientes.close();
		}
		return clientes;
	}	
}
