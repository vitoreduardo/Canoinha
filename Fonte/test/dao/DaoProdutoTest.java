package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import model.Categoria;
import model.Conexao;
import model.Produto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DaoProdutoTest {
	private DaoProduto daoProduto;
	private DaoCategoria daoCategoria;
	private Connection conexao;
	
	@Before
	public void setUp() throws Exception {
		this.conexao = Conexao.getConexao("jdbc:postgresql://localhost:5432/CanoinhaTestDB");
		this.daoProduto = new DaoProduto(this.conexao);
		this.daoCategoria = new DaoCategoria(this.conexao);
	}

	@After
	public void tearDown() throws Exception {
		this.conexao = null;
		this.daoProduto = null;
	}
	
	private Categoria categoriaPadrao(){
		Categoria categoria = new Categoria();
		categoria.setNome("Categoria de Teste");
		return categoria;
	}
	
	private Produto produtoPadrao() throws SQLException{
		daoCategoria.inserir(categoriaPadrao());
		Produto produtoEsperado = new Produto();			
		produtoEsperado.setNome("Produto teste");
		produtoEsperado.setFotos("foto.jpg");
		produtoEsperado.setInformacoes("Informações gerais do produto");
		produtoEsperado.setPrecoDeCompra(2);
		produtoEsperado.setPrecoDeVenda(3);
		produtoEsperado.setQuantidadeDisponivel(1000);
		produtoEsperado.setValorDesconto(0);
		HashMap<String, String> caracteristicas = new HashMap<String, String>();
		caracteristicas.put("Voltagem", "220V");
		caracteristicas.put("Cor", "Azul");
		caracteristicas.put("Garantia", "1 Ano");
		caracteristicas.put("Sistema Operacional", "Windows 8");
		produtoEsperado.setCaracteristicas(caracteristicas);
		
		List<Categoria> categorias = daoCategoria.buscar();
		
		produtoEsperado.setCategoria(categorias.get(0));
		
		return produtoEsperado;
	}

	/**
	 * Quando for utilizado o metodo inserir, passando como parâmetro umm produto
	 * o mesmo deve ser pesistido no banco de dados.
	 * @throws SQLException 
	 */
	@Test
	public void inserir_produto() throws SQLException {
		
		Produto produtoEsperado = produtoPadrao();		
		daoProduto.inserir(produtoEsperado);
		
		Produto produtoAtual = daoProduto.buscar(produtoEsperado.getId());
		
		Assert.assertEquals(produtoEsperado.getId(), produtoAtual.getId());
		Assert.assertEquals(produtoEsperado.getInformacoes(), produtoAtual.getInformacoes());
		Assert.assertEquals(produtoEsperado.getPrecoDeCompra(), produtoAtual.getPrecoDeCompra());
		Assert.assertEquals(produtoEsperado.getCaracteristicas().size(), produtoAtual.getCaracteristicas().size());
			
		daoProduto.excluir(produtoEsperado.getId());
		daoCategoria.excluir(produtoEsperado.getCategoria().getId());
	}
	
	/**
	 * quando for utilizado o metodo inserir, passando como parâmetro um produto sem caracteristicas
	 * deve retornar o mesmo produto sem caracteristicas.
	 */
	@Test
	public void inserir_produto_sem_caracteristicas() throws SQLException{
		daoCategoria.inserir(categoriaPadrao());
		Produto produtoEsperado = new Produto();			
		produtoEsperado.setNome("Produto teste");
		produtoEsperado.setFotos("foto.jpg");
		produtoEsperado.setInformacoes("Informações gerais do produto");
		produtoEsperado.setPrecoDeCompra(2);
		produtoEsperado.setPrecoDeVenda(3);
		produtoEsperado.setQuantidadeDisponivel(1000);
		produtoEsperado.setValorDesconto(0);
        List<Categoria> categorias = daoCategoria.buscar();
		produtoEsperado.setCategoria(categorias.get(0));
		
		daoProduto.inserir(produtoEsperado);
		
		Produto produtoAtual = daoProduto.buscar(produtoEsperado.getId());
		
		Assert.assertEquals(produtoEsperado.getId(), produtoAtual.getId());
		Assert.assertEquals(0, produtoAtual.getCaracteristicas().size());
		
		daoProduto.excluir(produtoEsperado.getId());
		daoCategoria.excluir(produtoEsperado.getCategoria().getId());
	}
	
	/**
	 * quando utilizar o método atualizar passando um produto com os campos 
	 * nome, foto, informacoes e preco de compra alterados, deve atualizar no banco de dados
	 *  o produto com os dados do objeto.
	 */
	@Test
	public void atualizar_produto() throws SQLException{
		Produto produtoEsperado = produtoPadrao();
		
		daoProduto.inserir(produtoEsperado);
		
		produtoEsperado.setNome("Nome Do Produto Alterado");
		produtoEsperado.setFotos("fotoAlterada.jpg");
		produtoEsperado.setInformacoes("informações Alterada");
		produtoEsperado.setPrecoDeCompra(999);
		
		daoProduto.atualizar(produtoEsperado);
		
		Produto produtoAtual = daoProduto.buscar(produtoEsperado.getId());
		
		Assert.assertEquals(produtoEsperado.getNome(), produtoAtual.getNome());
		Assert.assertEquals(produtoEsperado.getFotos(), produtoAtual.getFotos());
		Assert.assertEquals(produtoEsperado.getInformacoes(), produtoAtual.getInformacoes());
		Assert.assertEquals(produtoEsperado.getPrecoDeCompra(), produtoAtual.getPrecoDeCompra());
		
		daoProduto.excluir(produtoEsperado.getId());
	}
	
	/**
	 * quando utilizar o método atualizar passando um produto com a caracteristicas alteradas
	 * deve atualizar no banco de dados o produto com os dados do objeto.
	 */
	@Test
	public void atualizar__caracteristicas_do_produto() throws SQLException{
		Produto produtoEsperado = produtoPadrao();
		
		daoProduto.inserir(produtoEsperado);
		
		produtoEsperado.addCaracteristica("Outra Caracteristica", "00");
		
		daoProduto.atualizar(produtoEsperado);
		
		Produto produtoAtual = daoProduto.buscar(produtoEsperado.getId());
		
		Assert.assertEquals(5, produtoAtual.getCaracteristicas().size());

		daoProduto.excluir(produtoEsperado.getId());
	}
	

}
