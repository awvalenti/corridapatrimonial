package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ExecutorComandos;

public class EntradaJogoViaSocket {

	private Socket socketVindoDoCliente;
	private ExecutorComandos executorComandos;

	public EntradaJogoViaSocket(Socket socketVindoDoCliente, ExecutorComandos executorComandos) throws IOException {
		this.socketVindoDoCliente = socketVindoDoCliente;
		this.executorComandos = executorComandos;
	}

	public void lerETratarLinhaComando() throws IOException {
		executorComandos.executarLinhaComando(new BufferedReader(new InputStreamReader(socketVindoDoCliente.getInputStream())).readLine());
	}

}
