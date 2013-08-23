package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Oferta;

public interface InterfaceSaidaJogo extends OuvinteVitrine {

	void aoEntrarJogador(Jogador jogador);

	void aoFinalizarJogo(Jogador vencedor);

	void aoEfetivarCompra(Jogador jogador, Oferta oferta);

}
