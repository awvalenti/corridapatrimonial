package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceSaidaJogo;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Oferta;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Vitrine;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.mvc.SituacaoAtualJogo;

public enum SaidaJogoNoConsole implements InterfaceSaidaJogo {
	INSTANCIA {
		@Override
		public void aoEntrarJogador(Jogador jogador) {
			System.out.println("Novo jogador: " + jogador);
		}

		@Override
		public void aoIniciarJogo() {
			System.out.println("Jogo comecou!");
		}

		@Override
		public void aoAbrirVitrine(Vitrine vitrine) {
			System.out.println("Vitrine aberta: " + vitrine);
		}

		@Override
		public void aoFecharVitrine(SituacaoAtualJogo situacao) {
			System.out.println("Vitrine fechada. Situacao atual do jogo:\n" + situacao);
		}

		@Override
		public void aoEfetivarCompra(Jogador jogador, Oferta oferta) {
			System.out.println(jogador + " comprou " + oferta);
		}

		@Override
		public void aoFinalizarJogo(Jogador vencedor) {
			System.out.println("Fim de jogo! Vencedor: " + vencedor);
		}

	}

}
