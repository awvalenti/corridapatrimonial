package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private final int portaServidor;
	private final EntradaSaidaServidor entradaSaidaServidor;
	private final EstrategiaIdentificacaoCliente estrategiaIdentificacaoCliente;

	public Servidor(int portaServidor, EstrategiaIdentificacaoCliente estrategiaIdentificacaoCliente, EntradaSaidaServidor entradaSaidaServidor) {
		this.portaServidor = portaServidor;
		this.entradaSaidaServidor = entradaSaidaServidor;
		this.estrategiaIdentificacaoCliente = estrategiaIdentificacaoCliente;
	}

	public void iniciarEscutaDeClientes() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(portaServidor);
		for (;;) {
			Socket socketCliente = serverSocket.accept();

			entradaSaidaServidor.realizar(estrategiaIdentificacaoCliente.identificar(socketCliente), socketCliente.getInputStream(),
					socketCliente.getOutputStream());

			socketCliente.close();
		}
	}

}
