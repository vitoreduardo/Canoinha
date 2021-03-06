package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import model.Conexao;
import model.Produto;

public class DaoCaracteristicasProduto extends Dao{
	private Connection connection;
	private Statement smtm;
	private PreparedStatement pstm;
	
	public DaoCaracteristicasProduto(Connection connection) {
		super(connection);
		try {
			this.connection = connection;
			this.smtm = this.connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inserir(int idProduto, HashMap<String, String> caracteristicas) throws SQLException{						
	    for (String chaveHash : caracteristicas.keySet()) {
		    String valorHash = caracteristicas.get(chaveHash);
		    
		    String sql = "INSERT INTO CaracteristicasProduto "+
               	 		 "(produtos_id, chave, valor) "+
               	 		 "VALUES (?, ?, ?)";
		    pstm = connection.prepareStatement(sql);
		    pstm.setInt(1, idProduto);
            pstm.setString(2, chaveHash);
            pstm.setString(3, valorHash);

            pstm.execute();
	    }
	}
	
	public void excluir(int idProduto) throws SQLException{						
	   String sql = "DELETE FROM CaracteristicasProduto "+
               		"WHERE produtos_id=?";
	   pstm = connection.prepareStatement(sql);
	   pstm.setInt(1, idProduto);
	   pstm.execute();
	}
	
	public HashMap<String, String> buscar(int idProduto) throws SQLException{
		HashMap<String, String> caracteristicas = new HashMap<String, String>();
		String sql = "SELECT * FROM CaracteristicasProduto "+
           		     "WHERE produtos_id=?";
		pstm = connection.prepareStatement(sql);
		pstm.setInt(1, idProduto);
        ResultSet rs = pstm.executeQuery();
        if(rs!=null){
        	while(rs.next()){
        		caracteristicas.put(rs.getString("chave"), rs.getString("valor"));
        	}
        }
        return caracteristicas;
	}

}
