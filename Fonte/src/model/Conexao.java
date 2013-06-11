package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;


public class Conexao {
	private static Connection connection;
	
	public static Connection getConexao() throws SQLException{
		return getConexao("jdbc:postgresql://localhost:5432/Canoinha");
	}
		
	public static Connection getConexao(String stringConnection) throws SQLException{
		try {			
			if(connection==null){				
				Class.forName("org.postgresql.Driver" );
				connection = DriverManager.getConnection(stringConnection,"postgres","master");				
			}
			return connection;
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}				
	}	
}
