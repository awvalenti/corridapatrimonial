package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos;

import java.util.Arrays;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens.MensagemFixa;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens.MensagemResultanteExecucaoComando;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

enum Comando {
	ENTRAR(2) {
		@Override
		MensagemResultanteExecucaoComando executarEfetivamente(InterfaceEntradaJogo entradaJogo, String[] args) {
			return entradaJogo.criarNovoJogador(args[1]);
		}
	},

	COMPRAR(4) {
		@Override
		MensagemResultanteExecucaoComando executarEfetivamente(InterfaceEntradaJogo entradaJogo, String[] args) {
			return entradaJogo.solicitarCompra(args[1], args[2], args[3]);
		}
	},

	COMANDO_NAO_RECONHECIDO(0) {
		@Override
		MensagemResultanteExecucaoComando executarEfetivamente(InterfaceEntradaJogo entradaJogo, String[] args) {
			// XXX Feio
			System.err.println("Comando nao reconhecido: " + args[0]);

			return MensagemFixa.COMANDO_NAO_RECONHECIDO;
		}
	},
	;

	private final int numeroCorretoArgumentos;

	Comando(int numeroCorretoArgumentos) {
		this.numeroCorretoArgumentos = numeroCorretoArgumentos;
	}

	public static Comando fromString(String nomeComando) {
		return buscarComandoComNome(nomeComando.toUpperCase().replace('-', '_'));
	}

	public MensagemResultanteExecucaoComando executar(InterfaceEntradaJogo entradaJogo, String[] args) {
		if (args.length == numeroCorretoArgumentos) {
			return executarEfetivamente(entradaJogo, args);
		} else {
			// XXX Feio
			System.err.println("COMANDO_REJEITADO: " + Arrays.toString(args));

			return MensagemFixa.COMANDO_REJEITADO;
		}
	}

	abstract MensagemResultanteExecucaoComando executarEfetivamente(InterfaceEntradaJogo entradaJogo, String[] args);

	@Override
	public String toString() {
		return name().replace('_', '-').toLowerCase();
	}

	private static Comando buscarComandoComNome(String nomeComando) {
		// TODO Em vez de values(), algo que exclua COMANDO_NAO_RECONHECIDO
		for (Comando comando : values()) {
			if (comando.name().equals(nomeComando)) {
				return comando;
			}
		}

		return COMANDO_NAO_RECONHECIDO;
	}

}