package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Conexao;

public class Dao {
	private Conexao conexao;
	private Statement smtm;
	
	public Dao(Conexao conexao) {
		this.conexao = conexao;
		try {
			this.smtm = conexao.getConexao().createStatement();
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
