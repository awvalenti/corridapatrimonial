package com.github.awvalenti.corridapatrimonial.cliente;

import java.io.IOException;
import java.net.Socket;

public class Cliente {

	private final String enderecoIpServidor;
	private final int portaServidor;
	private EntradaSaidaCliente entradaSaidaCliente;

	public Cliente(String enderecoIpServidor, int portaServidor, EntradaSaidaCliente entradaSaidaCliente) {
		this.enderecoIpServidor = enderecoIpServidor;
		this.portaServidor = portaServidor;
		this.entradaSaidaCliente = entradaSaidaCliente;
	}

	public void enviarLinhaComando(String linhaComando) throws IOException {
		Socket socket = new Socket(enderecoIpServidor, portaServidor);
		entradaSaidaCliente.realizar(linhaComando, socket.getOutputStream(), socket.getInputStream());
		socket.close();
	}

}
