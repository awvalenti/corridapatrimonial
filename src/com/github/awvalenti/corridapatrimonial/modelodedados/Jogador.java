package com.github.awvalenti.corridapatrimonial.modelodedados;

import java.math.BigDecimal;

public class Jogador {

	private String id;
	private Patrimonio patrimonio;
	private BigDecimal dinheiro;

	public Jogador(String id, Patrimonio patrimonio, BigDecimal dinheiro) {
		this.id = id;
		this.patrimonio = patrimonio;
		this.dinheiro = dinheiro;
	}

	public String getId() {
		return id;
	}

	public void comprar(Oferta oferta) {
		patrimonio.acrescentar(oferta.getProduto());
		dinheiro = dinheiro.subtract(oferta.getPreco());
	}

	public boolean cumpriuObjetivo() {
		return dinheiro.compareTo(BigDecimal.ZERO) > 0 && patrimonio.estahCompleto();
	}

	@Override
	public String toString() {
		return id;
	}

}
