package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados;

import java.math.BigDecimal;

import com.github.awvalenti.corridapatrimonial.util.FormatadorDinheiro;

public class Oferta {

	private String id;
	private Produto produto;
	private BigDecimal preco;

	public Oferta(String id, Produto produto, BigDecimal preco) {
		this.id = id;
		this.produto = produto;
		this.preco = preco;
	}

	public String getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	@Override
	public String toString() {
		return id + ") " + produto.toString() + " a " + FormatadorDinheiro.formatar(preco);
	}

}
