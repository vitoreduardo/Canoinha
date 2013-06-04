package model;

import java.util.Date;
import java.util.List;

public class Venda {
	private int Id;
	private Date data;
	private double valor;
	private double valorFrete;
	private String tipoPagamento;
	private int quantidadeParcelas;
	private List<ItemVenda> itensVendas;
	private String numeroCartaoDeCredito;
	
	public String getNumeroCartaoDeCredito() {
		return numeroCartaoDeCredito;
	}

	public void setNumeroCartaoDeCredito(String numeroCartaoDeCredito) {
		this.numeroCartaoDeCredito = numeroCartaoDeCredito;
	}

	public void addItensVendas(ItemVenda itemVenda){
		this.itensVendas.add(itemVenda);
	}

	public List<ItemVenda> getItensVendas() {
		return itensVendas;
	}
	public void setItensVendas(List<ItemVenda> itensVendas) {
		this.itensVendas = itensVendas;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	
	
}
