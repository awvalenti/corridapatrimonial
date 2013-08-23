package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Oferta;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Vitrine;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.mvc.SituacaoAtualJogo;

public interface InterfaceSaidaJogo {

	void aoEntrarJogador(Jogador jogador);

	void aoIniciarJogo();

	void aoEfetivarCompra(Jogador jogador, Oferta oferta);

	void aoFinalizarJogo(Jogador vencedor);

	void aoAbrirVitrine(Vitrine vitrine);

	void aoFecharVitrine(SituacaoAtualJogo situacao);

}
