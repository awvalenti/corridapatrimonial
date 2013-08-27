package com.github.awvalenti.corridapatrimonial.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.github.awvalenti.corridapatrimonial.util.LeitorEscritor;

public class EntradaSaidaCliente {

	private final OutputStream escritorRespostaServidor;

	public EntradaSaidaCliente(OutputStream escritorRespostaServidor) {
		this.escritorRespostaServidor = escritorRespostaServidor;
	}

	public void realizar(String linhaComando, OutputStream paraServidor, InputStream doServidor) throws IOException {
		LeitorEscritor.escreverLinha(paraServidor, linhaComando);
		String respostaServidor = LeitorEscritor.lerLinha(doServidor);
		LeitorEscritor.escreverLinha(escritorRespostaServidor, "Servidor disse: " + respostaServidor);
	}

}
