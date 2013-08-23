package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ProcessadorComandosCifrados;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.GeradorChaves;

public class ConectorDeEntradaParaServidor {

	private int portaServidor;
	private ProcessadorComandosCifrados processadorComandosCifrados;
	private GeradorChaves geradorChaves;

	public ConectorDeEntradaParaServidor(int portaServidor, ProcessadorComandosCifrados processadorComandosCifrados) {
		this.portaServidor = portaServidor;
		this.processadorComandosCifrados = processadorComandosCifrados;

		// XXX Sem injecao de dependencia
		geradorChaves = new GeradorChaves();
	}

	public void iniciar() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(portaServidor);
		for (;;) {
			Socket socketCliente = serverSocket.accept();

			String idCliente = ((InetSocketAddress) socketCliente.getRemoteSocketAddress()).getAddress().getHostAddress();

			new EntradaParaServidorViaInputStream(geradorChaves.buscarOuGerarChave(idCliente), socketCliente.getInputStream(),
					processadorComandosCifrados).lerETratarLinhaComando();
		}
	}

}
