package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.io.IOException;
import java.net.ServerSocket;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ExecutorComandos;

public class ConectorDeEntradaParaServidor {

	private int portaServidor;
	private ExecutorComandos executorComandos;

	public ConectorDeEntradaParaServidor(int portaServidor, ExecutorComandos executorComandos) {
		this.portaServidor = portaServidor;
		this.executorComandos = executorComandos;
	}

	public void iniciar() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(portaServidor);
		for (;;) {
			new EntradaParaServidorViaInputStream(serverSocket.accept().getInputStream(), executorComandos).lerETratarLinhaComando();
		}
	}
}
