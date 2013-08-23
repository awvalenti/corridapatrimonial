package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

enum Comando {
	ENTRAR(2) {
		@Override
		MensagemResultanteExecucaoComando executarEfetivamente(InterfaceEntradaJogo entradaJogo, String[] args) {
			return entradaJogo.criarNovoJogador(args[1]);
		}
	},

	COMPRAR(3) {
		@Override
		MensagemResultanteExecucaoComando executarEfetivamente(InterfaceEntradaJogo entradaJogo, String[] args) {
			return entradaJogo.solicitarCompra(args[1], args[2]);
		}
	},

	COMANDO_NAO_RECONHECIDO(0) {
		@Override
		MensagemResultanteExecucaoComando executarEfetivamente(InterfaceEntradaJogo entradaJogo, String[] args) {
			// XXX Feio
			System.err.println("Comando nao reconhecido: " + args[0]);

			return MensagemResultanteExecucaoComando.COMANDO_NAO_RECONHECIDO;
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
		return args.length == numeroCorretoArgumentos ? executarEfetivamente(entradaJogo, args) :
				MensagemResultanteExecucaoComando.COMANDO_REJEITADO;
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