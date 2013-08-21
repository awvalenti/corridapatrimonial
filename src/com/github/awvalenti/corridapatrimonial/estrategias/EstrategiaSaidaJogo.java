package com.github.awvalenti.corridapatrimonial.estrategias;

import com.github.awvalenti.corridapatrimonial.interfaces.InterfaceSaidaJogo;
import com.github.awvalenti.corridapatrimonial.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.modelodedados.Oferta;

public enum EstrategiaSaidaJogo implements InterfaceSaidaJogo {
	SAIDA_NO_CONSOLE {
		@Override
		public void aoFinalizarJogo(Jogador vencedor) {
			System.out.println("Fim de jogo! Vencedor: " + vencedor);
		}

		@Override
		public void aoEfetivarCompra(Jogador jogador, Oferta oferta) {
			System.out.println(jogador + " comprou " + oferta);
		}

	}

}
