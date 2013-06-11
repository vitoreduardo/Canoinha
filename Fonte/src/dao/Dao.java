package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Conexao;

public class Dao {
	private Connection connection;
	private Statement smtm;
	
	public Dao(Connection conexao) {
		this.connection = conexao;
		try {
			this.smtm = conexao.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected int gerarSequencia(String nomeSequence) throws SQLException{
		String sql = "Select nextval('"+nomeSequence+"') As id";
		ResultSet rs = smtm.executeQuery(sql);
		rs.next();		
		return rs.getInt("id");
	}
	
	protected String aspasSimples(String texto){
		return "'"+texto+"'";
	}
	
	protected String formatarData(Date data){		
		SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");  
		return spf.format(data); 
	}
}
