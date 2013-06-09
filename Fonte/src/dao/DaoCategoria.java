package dao;

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
	private Conexao conexao;
	private Statement smtm;
	private PreparedStatement pstm;
	
	public DaoCategoria(Conexao conexao) throws SQLException {
		super(conexao);
		this.conexao = conexao;
		this.smtm = this.conexao.getConexao().createStatement();
	}
	
	public void inserir(Categoria categoria) throws SQLException{
        categoria.setId(gerarSequencia("categorias_id_seq"));
		
		String sql = "INSERT INTO categorias "+
	                 "(id, nome) "+
	                 "VALUES (?, ?)";
		
		pstm = conexao.getConexao().prepareStatement(sql);
		pstm.setInt(1, categoria.getId());
		pstm.setString(2, categoria.getNome());
		pstm.execute();
	}
	
	public void atualizar(Categoria categoria) throws SQLException{
		String sql = "UPDATE categorias SET "+
				     "id="+categoria.getId()+", " +
				     "nome="+aspasSimples(categoria.getNome())+" " +
				     "WHERE id="+categoria.getId();								
		
		smtm.executeUpdate(sql);
	}
	
	public void excluir(int id) throws SQLException{
		String sql = "DELETE FROM categorias "+ 
	                 "WHERE id="+id;										
		smtm.executeUpdate(sql);				
	}
	
	public Categoria buscar(int id) throws SQLException{
		String sql = "Select * FROM Categorias "+ 
	                 "WHERE id="+id;										
		ResultSet rsUsuarios = smtm.executeQuery(sql);
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
