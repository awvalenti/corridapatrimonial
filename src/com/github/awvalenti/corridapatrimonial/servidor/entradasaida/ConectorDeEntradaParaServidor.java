package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ProcessadorComandosCifrados;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.GeradorChaves;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens.MensagemJogadorEntrou;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens.MensagemResultanteExecucaoComando;

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

	public void iniciarEscutaDeClientes() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(portaServidor);
		for (;;) {
			Socket socketCliente = serverSocket.accept();

			String idCliente = ((InetSocketAddress) socketCliente.getRemoteSocketAddress()).getHostName();

			Integer chaveBuscada = geradorChaves.buscarChave(idCliente);

			if (chaveBuscada == null) {
				chaveBuscada = 0;
			}

			MensagemResultanteExecucaoComando mensagemResultante = new EntradaParaServidorViaInputStream(chaveBuscada,
					socketCliente.getInputStream(), processadorComandosCifrados).lerETratarLinhaComando();

			// XXX Feio
			if (mensagemResultante.indicaQueJogadorEntrou()) {
				Integer chaveGeradaAgora = geradorChaves.gerarChave(idCliente);
				((MensagemJogadorEntrou) mensagemResultante).setChaveCriptografica(chaveGeradaAgora);
			}

			OutputStream outputStream = socketCliente.getOutputStream();
			new PrintWriter(outputStream, true).println(mensagemResultante.getTexto());

			socketCliente.close();
		}
	}

}
