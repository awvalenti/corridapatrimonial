package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ProcessadorComandosCifrados;

public class EntradaParaServidorViaInputStream {

	private InputStream inputStream;
	private ProcessadorComandosCifrados processadorComandosCifrados;
	private int chave;

	public EntradaParaServidorViaInputStream(int chave, InputStream inputStream, ProcessadorComandosCifrados processadorComandosCifrados) {
		this.chave = chave;
		this.inputStream = inputStream;
		this.processadorComandosCifrados = processadorComandosCifrados;
	}

	public void lerETratarLinhaComando() throws IOException {
		processadorComandosCifrados.processarLinhaComandoCifrada(new BufferedReader(new InputStreamReader(inputStream)).readLine(), chave);
	}

}
