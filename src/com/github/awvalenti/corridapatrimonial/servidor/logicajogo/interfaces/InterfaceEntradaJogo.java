package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.MensagemResultanteExecucaoComando;

public interface InterfaceEntradaJogo {

	void adicionarOuvinteOfertas(OuvinteOfertas ouvinteOfertas);

	MensagemResultanteExecucaoComando criarNovoJogador(String idJogador);

	MensagemResultanteExecucaoComando iniciarJogo();

	MensagemResultanteExecucaoComando solicitarCompra(String idJogador, String idOferta);

}
