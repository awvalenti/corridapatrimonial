package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados;

import java.math.BigDecimal;

import com.github.awvalenti.corridapatrimonial.util.FormatadorDinheiro;

public class Jogador {

	private String id;
	private Patrimonio patrimonio;
	private BigDecimal dinheiro;
	private String codigoCartao;

	public Jogador(String id, Patrimonio patrimonio, BigDecimal dinheiro, String codigoCartao) {
		this.id = id;
		this.patrimonio = patrimonio;
		this.dinheiro = dinheiro;
		this.codigoCartao = codigoCartao;
	}

	public String getId() {
		return id;
	}

	public void patrimoniar(Produto produto) {
		patrimonio.acrescentar(produto);
	}

	public void pagar(BigDecimal valor) {
		dinheiro = dinheiro.subtract(valor);
	}

	public boolean patrimonioEstahCompleto() {
		return patrimonio.estahCompleto();
	}

	@Override
	public String toString() {
		return id;
	}

	public String getCodigoCartao() {
		return codigoCartao;
	}

	public String getDinheiroFormatado() {
		return FormatadorDinheiro.formatar(dinheiro);
	}

	public Patrimonio getPatrimonio() {
		return patrimonio;
	}

	public BigDecimal getDinheiro() {
		return dinheiro;
	}

}
