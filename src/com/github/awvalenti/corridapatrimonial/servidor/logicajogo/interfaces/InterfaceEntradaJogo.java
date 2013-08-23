package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces;

public interface InterfaceEntradaJogo {

	void adicionarOuvinteOfertas(OuvinteOfertas ouvinteOfertas);

	void criarNovoJogador(String idJogador);

	void iniciarJogo();

	void solicitarCompra(String idJogador, String idOferta);

}
