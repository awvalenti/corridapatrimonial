package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.mvc;

import java.util.List;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Jogador;

public class SituacaoAtualJogo {

	private List<Jogador> jogadores;

	public SituacaoAtualJogo(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("=============================================================\n");
		for (Jogador jogador : jogadores) {
			sb.append(jogador.getId()).append("\t").append(jogador.getDinheiroFormatado()).append('\n');
			sb.append("------------------------\n");
			sb.append(jogador.getPatrimonio()).append('\n');
		}

		sb.append("=============================================================\n");

		return sb.toString();
	}
}
