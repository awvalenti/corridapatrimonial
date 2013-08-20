package com.github.awvalenti.corridapatrimonial.interfaces;

public interface InterfaceJogo {

	void solicitarCompra(String idJogador, String idOferta);

	void iniciar();

	void adicionarObservadorVitrine(OuvinteVitrine observador);

}
