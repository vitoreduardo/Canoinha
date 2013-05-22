package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import junit.framework.Assert;

import model.Categoria;
import model.Conexao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DaoCategoria;


public class DaoCategoriaTest {
	private Conexao conexao;
	private DaoCategoria daoCategoria;

	private Categoria categoriaPadrao(){
		Categoria categoria = new Categoria();
		categoria.setNome("Categoria de Teste");
		return categoria;
	}
	
	@Before
	public void setUp() throws Exception {
		this.conexao = new Conexao("jdbc:postgresql://localhost:5432/CanoinhaTestDB");
		this.daoCategoria = new DaoCategoria(this.conexao);
	}

	@After
	public void tearDown() throws Exception {
		this.conexao = null;
		this.daoCategoria = null;
	}
	

	@Test
	public void inserir_categoria() throws SQLException {
		Categoria categoriaEsperada = categoriaPadrao();
		daoCategoria.inserir(categoriaEsperada);
		Categoria categoriaAtual = daoCategoria.buscar(categoriaEsperada.getId());
		
		Assert.assertEquals(categoriaEsperada.getId(), categoriaAtual.getId());
		Assert.assertEquals(categoriaEsperada.getNome(), categoriaAtual.getNome());
		
		daoCategoria.excluir(categoriaEsperada.getId());
	}

}
