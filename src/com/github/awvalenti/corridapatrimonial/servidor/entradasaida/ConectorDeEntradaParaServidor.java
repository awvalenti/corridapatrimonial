package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.MensagemResultanteExecucaoComando;
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

	public void iniciarEscutaDeClientes() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(portaServidor);
		for (;;) {
			Socket socketCliente = serverSocket.accept();

			String idCliente = ((InetSocketAddress) socketCliente.getRemoteSocketAddress()).getHostName();

			Integer chave = geradorChaves.buscarChave(idCliente);

			if (chave == null) {
				chave = 0;
			}

			MensagemResultanteExecucaoComando mensagemResultante = new EntradaParaServidorViaInputStream(chave,
					socketCliente.getInputStream(), processadorComandosCifrados).lerETratarLinhaComando();

			String mensagemParaCliente = mensagemResultante.name();

			// XXX Feio: if de enum
			if (mensagemResultante == MensagemResultanteExecucaoComando.JOGADOR_ENTROU) {
				mensagemParaCliente = "Sua chave e' " + geradorChaves.gerarChave(idCliente);
			}

			OutputStream outputStream = socketCliente.getOutputStream();
			new PrintWriter(outputStream, true).println(mensagemParaCliente);

			socketCliente.close();
		}
	}

}
