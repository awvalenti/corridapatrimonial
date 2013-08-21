package com.github.awvalenti.corridapatrimonial.interfaces;

import com.github.awvalenti.corridapatrimonial.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.modelodedados.Oferta;

public interface InterfaceSaidaJogo extends OuvinteVitrine {

	void aoFinalizarJogo(Jogador vencedor);

	void aoEfetivarCompra(Jogador jogador, Oferta oferta);

}
