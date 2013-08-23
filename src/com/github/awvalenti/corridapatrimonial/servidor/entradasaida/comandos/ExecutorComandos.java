package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

public class ExecutorComandos {

	private InterfaceEntradaJogo entradaJogo;

	public ExecutorComandos(InterfaceEntradaJogo entradaJogo) {
		this.entradaJogo = entradaJogo;
	}

	public MensagemResultanteExecucaoComando executarLinhaComando(String linhaComando) {
		String[] args = linhaComando.split(" ");
		String nomeComando = args[0];

		return Comando.fromString(nomeComando).executar(entradaJogo, args);
	}

}