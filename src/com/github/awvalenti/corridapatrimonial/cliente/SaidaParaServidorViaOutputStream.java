package com.github.awvalenti.corridapatrimonial.cliente;

import java.io.OutputStream;
import java.io.PrintWriter;

public class SaidaParaServidorViaOutputStream {

	private OutputStream outputStream;

	public SaidaParaServidorViaOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void enviarLinhaComando(String linhaComando) {
		new PrintWriter(outputStream, true).println(linhaComando);
	}

}
