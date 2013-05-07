package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import model.Conexao;
import model.Produto;

public class DaoCaracteristicasProduto extends Dao{
	private Conexao conexao;
	private Statement smtm;
	
	public DaoCaracteristicasProduto(Conexao conexao) {
		super(conexao);
		try {
			this.conexao = conexao;
			this.smtm = this.conexao.getConexao().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inserir(int idProduto, HashMap<String, String> caracteristicas) throws SQLException{						
	    for (String chaveHash : caracteristicas.keySet()) {
		    String valorHash = caracteristicas.get(chaveHash);
		    
		    String sql = "INSERT INTO CaracteristicasProduto "+
               	 		 "(produtos_id, chave, valor) "+
               	 		 "VALUES ("+	                 
               	 	     idProduto+","+
               	 		 aspasSimples(chaveHash)+","+                	 
               	 		 aspasSimples(valorHash)+")";
		    smtm.executeUpdate(sql);
	    }
	}
	
	public void excluir(int idProduto) throws SQLException{						
	   String sql = "DELETE FROM CaracteristicasProduto "+
               		"WHERE produtos_id='"+idProduto+"'";
	   smtm.executeUpdate(sql);	    
	}
	
	public HashMap<String, String> buscar(int idProduto) throws SQLException{
		HashMap<String, String> caracteristicas = new HashMap<String, String>();
		String sql = "SELECT * FROM CaracteristicasProduto "+
           		     "WHERE produtos_id='"+idProduto+"'";
        ResultSet rs = smtm.executeQuery(sql);
        if(rs!=null){
        	while(rs.next()){
        		caracteristicas.put(rs.getString("chave"), rs.getString("valor"));
        	}
        }
        return caracteristicas;
	}

}
