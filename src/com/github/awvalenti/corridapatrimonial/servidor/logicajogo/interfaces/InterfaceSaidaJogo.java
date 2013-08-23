package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Oferta;

public interface InterfaceSaidaJogo extends OuvinteVitrine {

	void aoEntrarJogador(Jogador jogador);

	void aoIniciarJogo();

	void aoEfetivarCompra(Jogador jogador, Oferta oferta);

	void aoFinalizarJogo(Jogador vencedor);

}
