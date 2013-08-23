package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;

public class ProcessadorComandosCifrados {

	private AlgoritmoCriptografico algoritmoCriptografico;
	private ExecutorComandos executorComandos;

	public ProcessadorComandosCifrados(AlgoritmoCriptografico algoritmoCriptografico, ExecutorComandos executorComandos) {
		this.algoritmoCriptografico = algoritmoCriptografico;
		this.executorComandos = executorComandos;
	}

	public MensagemResultanteExecucaoComando processarLinhaComandoCifrada(String linhaComandoCifrada, int chave) {
		return executorComandos.executarLinhaComando(algoritmoCriptografico.decifrar(linhaComandoCifrada, chave));
	}

}