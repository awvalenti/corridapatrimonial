package com.github.awvalenti.corridapatrimonial.modelodedados;

import java.math.BigDecimal;

import com.github.awvalenti.corridapatrimonial.util.FormatadorDinheiro;

public class Oferta {

	private String id;
	private Produto produto;
	private BigDecimal preco;

	private static volatile int ultimoId = 1;

	public Oferta(Produto produto, BigDecimal preco) {
		synchronized (Oferta.class) {
			this.id = produto.toString() + "-" + ultimoId++;
		}

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
		return produto.toString() + " a " + FormatadorDinheiro.formatar(preco);
	}

}
