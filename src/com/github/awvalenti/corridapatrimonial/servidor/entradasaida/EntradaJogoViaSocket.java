package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ExecutorComandos;

public class EntradaJogoViaSocket {

	private Socket socketCliente;
	private ExecutorComandos executorComandos;

	public EntradaJogoViaSocket(Socket socketCliente, ExecutorComandos executorComandos) throws IOException {
		this.socketCliente = socketCliente;
		this.executorComandos = executorComandos;
	}

	public void lerETratarComando() throws IOException {
		executorComandos.executarLinhaComando(new BufferedReader(new InputStreamReader(socketCliente.getInputStream())).readLine());
	}

}
