package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Jogador;


public interface InterfaceEntradaJogo {

	void adicionarOuvinteOfertas(OuvinteOfertas ouvinteOfertas);

	void adicionarJogador(Jogador jogador);

	void adicionarJogadores(Jogador... jogador);

	void iniciarJogo();

	void solicitarCompra(String idJogador, String idOferta);

}
