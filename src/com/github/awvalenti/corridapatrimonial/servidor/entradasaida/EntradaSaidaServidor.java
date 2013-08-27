package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ProcessadorComandosCifrados;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.GeradorChaves;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens.MensagemJogadorEntrou;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens.MensagemResultanteExecucaoComando;
import com.github.awvalenti.corridapatrimonial.util.LeitorEscritor;

public class EntradaSaidaServidor {

	private final ProcessadorComandosCifrados processadorComandosCifrados;
	private final GeradorChaves geradorChaves;

	public EntradaSaidaServidor(ProcessadorComandosCifrados processadorComandosCifrados, GeradorChaves geradorChaves) {
		this.processadorComandosCifrados = processadorComandosCifrados;
		this.geradorChaves = geradorChaves;
	}

	public void realizar(String idCliente, InputStream doCliente, OutputStream paraCliente) throws IOException {
		Integer chaveBuscada = geradorChaves.buscarChave(idCliente);

		if (chaveBuscada == null) {
			chaveBuscada = 0;
		}

		MensagemResultanteExecucaoComando mensagemResultante = processadorComandosCifrados.processarLinhaComandoCifrada(
				LeitorEscritor.lerLinha(doCliente), chaveBuscada);

		// XXX Feio
		if (mensagemResultante.indicaQueJogadorEntrou()) {
			Integer chaveGeradaAgora = geradorChaves.gerarChave(idCliente);
			((MensagemJogadorEntrou) mensagemResultante).setChaveCriptografica(chaveGeradaAgora);
		}

		LeitorEscritor.escreverLinha(paraCliente, mensagemResultante.getTexto());
	}
}
