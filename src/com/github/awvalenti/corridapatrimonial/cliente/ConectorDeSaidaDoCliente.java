package com.github.awvalenti.corridapatrimonial.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ConectorDeSaidaDoCliente {

	private String enderecoIpServidor;
	private int portaServidor;
	private PrintStream escritorParaMensagemVindaDoServidor;

	public ConectorDeSaidaDoCliente(String enderecoIpServidor, int portaServidor, PrintStream escritorParaMensagemVindaDoServidor) {
		this.enderecoIpServidor = enderecoIpServidor;
		this.portaServidor = portaServidor;
		this.escritorParaMensagemVindaDoServidor = escritorParaMensagemVindaDoServidor;
	}

	public void enviarLinhaComando(String linhaComando) throws IOException {
		Socket socket = new Socket(enderecoIpServidor, portaServidor);
		OutputStream outputStream = socket.getOutputStream();
		new SaidaParaServidorViaOutputStream(outputStream).enviarLinhaComando(linhaComando);
		escritorParaMensagemVindaDoServidor.println("Servidor disse: " + new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine());
		socket.close();
	}
}
