package com.github.awvalenti.corridapatrimonial.cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SaidaParaServidorViaOutputStream {

	private OutputStream outputStream;

	public SaidaParaServidorViaOutputStream(OutputStream outputStream) throws IOException {
		this.outputStream = outputStream;
	}

	public void enviarLinhaComando(String linhaComando) throws IOException  {
		PrintWriter printWriter = new PrintWriter(outputStream);
		printWriter.write(linhaComando);
		printWriter.flush();
	}

}
