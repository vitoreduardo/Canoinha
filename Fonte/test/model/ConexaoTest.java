package model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConexaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Quando for chamado o método getConexao, deve retornar uma instancia de uma conexão
	 * com o baco de dados de teste
	 */
	@Test
	public void testGetConexaoDeveRetornarUmObjetoDeConexaoComOBancoDeDados() {
		try {
			Conexao conexao = new Conexao("jdbc:postgresql://localhost:5432/CanoinhaTestDB");
			Connection connection = conexao.getConexao(); 
			Assert.assertNotNull(conexao);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testName() throws Exception {
		
	}

}
