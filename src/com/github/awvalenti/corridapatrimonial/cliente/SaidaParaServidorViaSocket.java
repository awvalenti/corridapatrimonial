package com.github.awvalenti.corridapatrimonial.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SaidaParaServidorViaSocket {

	private Socket socketParaServidor;

	public SaidaParaServidorViaSocket(Socket socketParaServidor) throws IOException {
		this.socketParaServidor = socketParaServidor;
	}

	public void enviarLinhaComando(String linhaComando) throws IOException  {
		PrintWriter printWriter = new PrintWriter(socketParaServidor.getOutputStream());
		printWriter.write(linhaComando);
		printWriter.flush();
	}

}
