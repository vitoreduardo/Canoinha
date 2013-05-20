package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;


public class Conexao {
	private String stringConnection;
	
	public Conexao() {      
		stringConnection = "jdbc:postgresql://localhost:5432/Canoinha";
	}
	
	public Conexao(String stringConnection){
		this.stringConnection = stringConnection;
	}
		
	public Connection getConexao() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver" );
			return DriverManager.getConnection(stringConnection,"postgres","postgres");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}				
	}	
}
