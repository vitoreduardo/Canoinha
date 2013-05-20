package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conexao;
import model.Produto;
import dao.DaoProduto;

public class ProdutoController {
	private Conexao conexao;
	private DaoProduto daoProduto;
	
	public ProdutoController() {
		this.conexao = new Conexao();
		this.daoProduto = new DaoProduto(this.conexao);
	}
	
	public void inserir(Produto produto) throws SQLException{
		this.daoProduto.inserir(produto);
	}
	
	public void atualizar(Produto produto) throws SQLException{
		this.daoProduto.atualizar(produto);
	}
	
	public void excluir(int id) throws SQLException{
		this.daoProduto.excluir(id);
	}
	
	public Produto buscar(int id) throws SQLException{		
		return this.daoProduto.buscar(id);
	}
}
