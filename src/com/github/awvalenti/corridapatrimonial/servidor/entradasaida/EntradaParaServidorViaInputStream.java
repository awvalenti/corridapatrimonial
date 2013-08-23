package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ExecutorComandos;

public class EntradaParaServidorViaInputStream {

	private InputStream inputStream;
	private ExecutorComandos executorComandos;

	public EntradaParaServidorViaInputStream(InputStream inputStream, ExecutorComandos executorComandos) {
		this.inputStream = inputStream;
		this.executorComandos = executorComandos;
	}

	public void lerETratarLinhaComando() throws IOException {
		executorComandos.executarLinhaComando(new BufferedReader(new InputStreamReader(inputStream)).readLine());
	}

}
