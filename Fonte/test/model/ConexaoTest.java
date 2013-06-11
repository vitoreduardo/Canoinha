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
			Connection connection = Conexao.getConexao("jdbc:postgresql://localhost:5432/CanoinhaTestDB"); 
			Assert.assertNotNull(connection);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}	

}
