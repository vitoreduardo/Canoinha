package model;

import java.sql.*;

public class Conexao {
	
	public static Connection getConexao() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver" );
			return DriverManager.getConnection("jdbc:mysql:lllocalhost/livra ria","edson","integrator");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}				
	}
}
