package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces;


public interface InterfaceEntradaJogo {

	void solicitarCompra(String idJogador, String idOferta);

	void iniciarJogo();

	void adicionarOuvinteOfertas(OuvinteOfertas ouvinteOfertas);

}
