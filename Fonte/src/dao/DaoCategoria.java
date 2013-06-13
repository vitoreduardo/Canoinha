package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Conexao;
import model.Endereco;
import model.TipoUsuario;
import model.Usuario;

public class DaoCategoria extends Dao{
	private Connection connection;
	private Statement smtm;
	private PreparedStatement pstm;
	
	public DaoCategoria(Connection connection) throws SQLException {
		super(connection);
		this.connection = connection;
		this.smtm = this.connection.createStatement();
	}
	
	public void inserir(Categoria categoria) throws SQLException{
        categoria.setId(gerarSequencia("categorias_id_seq"));
		
		String sql = "INSERT INTO categorias "+
	                 "(id, nome) "+
	                 "VALUES (?, ?)";
		
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, categoria.getId());
		pstm.setString(2, categoria.getNome());
		pstm.execute();
	}
	
	public void atualizar(Categoria categoria) throws SQLException{
		String sql = "UPDATE categorias SET nome=? "+				     
				     "WHERE id=?";		
		pstm = connection.prepareStatement(sql);		
		pstm.setString(1, categoria.getNome());
		pstm.setInt(2, categoria.getId());
		pstm.execute();
	}
	
	public void excluir(int id) throws SQLException{
		String sql = "DELETE FROM categorias "+ 
	                 "WHERE id=?";
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, id);
		pstm.execute();
	}
	
	public Categoria buscar(int id) throws SQLException{
		String sql = "Select * FROM Categorias "+ 
	                 "WHERE id=?";
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, id);
		
		ResultSet rsUsuarios = pstm.executeQuery();
		Categoria categoria = new Categoria();		
		if(rsUsuarios != null){
			while(rsUsuarios.next()){				
				categoria.setId(rsUsuarios.getInt("id"));
				categoria.setNome(rsUsuarios.getString("nome"));								
			}
			rsUsuarios.close();
		}
		return categoria;
	}
	
	public List<Categoria> buscar() throws SQLException{
		String sql = "Select * FROM Categorias ";
		ResultSet rsCategoria = smtm.executeQuery(sql);
		List<Categoria> listaDeCategorias = new ArrayList<Categoria>();
		
		if(rsCategoria != null){
			while(rsCategoria.next()){
				Categoria categoria = new Categoria();
				categoria.setId(rsCategoria.getInt("id"));
				categoria.setNome(rsCategoria.getString("nome"));
				listaDeCategorias.add(categoria);
			}
			rsCategoria.close();
		}
		return listaDeCategorias;
	}

}
