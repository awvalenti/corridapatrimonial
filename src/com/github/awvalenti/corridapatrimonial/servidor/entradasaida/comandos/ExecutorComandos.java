package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos;

import java.util.Arrays;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

public class ExecutorComandos {

	private InterfaceEntradaJogo entradaJogo;

	public ExecutorComandos(InterfaceEntradaJogo entradaJogo) {
		this.entradaJogo = entradaJogo;
	}

	public void executarLinhaComando(String linhaComando) {
		String[] palavras = linhaComando.split(" ");
		String nomeComando = palavras[0];
		String[] args = Arrays.copyOfRange(palavras, 1, palavras.length);

		Comando comando = Comando.fromString(nomeComando);
		if (comando != null) {
			comando.executar(entradaJogo, args);
		} else {
			System.err.println("Comando nao reconhecido: " + nomeComando);
		}
	}

}