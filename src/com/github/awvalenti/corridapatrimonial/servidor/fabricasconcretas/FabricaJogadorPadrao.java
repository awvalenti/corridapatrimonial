package com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas;

import java.math.BigDecimal;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.FabricaJogador;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Patrimonio;

public class FabricaJogadorPadrao implements FabricaJogador {

	private BigDecimal dinheiroInicial;

	public FabricaJogadorPadrao(BigDecimal dinheiroInicial) {
		this.dinheiroInicial = dinheiroInicial;
	}

	@Override
	public Jogador fabricar(String id, String codigoCartao) {
		return new Jogador(id, new Patrimonio(), dinheiroInicial, codigoCartao);
	}

}
