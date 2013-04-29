package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Produto {
	private int id;
	private String nome;
	private double precoDeCompra;
	private double precoDeVenda;
	private double valorDesconto;
	private String fotos;
	private String informacoes;
	private HashMap<String, String> caracteristicas;
	private int quantidadeDisponivel;
	
	public Produto() {
		this.caracteristicas = new HashMap<String, String>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrecoDeCompra() {
		return precoDeCompra;
	}

	public void setPrecoDeCompra(double precoDeCompra) {
		this.precoDeCompra = precoDeCompra;
	}

	public double getPrecoDeVenda() {
		return precoDeVenda;
	}

	public void setPrecoDeVenda(double precoDeVenda) {
		this.precoDeVenda = precoDeVenda;
	}

	public double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}
	
	public void addFoto(String foto){
		this.fotos= foto;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(int quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}
	
	public HashMap<String, String> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(HashMap<String, String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public void addCaracteristica(String chave, String valor){
		this.caracteristicas.put(chave, valor);
	}
}
