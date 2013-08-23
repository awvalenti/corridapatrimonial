package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

enum Comando {
	ENTRAR {
		@Override
		public MensagemResultanteExecucaoComando executar(InterfaceEntradaJogo entradaJogo, String[] args) {
			entradaJogo.criarNovoJogador(args[1]);
			return MensagemResultanteExecucaoComando.JOGADOR_ENTROU;
		}
	},

	COMPRAR {
		@Override
		public MensagemResultanteExecucaoComando executar(InterfaceEntradaJogo entradaJogo, String[] args) {
			entradaJogo.solicitarCompra(args[1], args[2]);
			return MensagemResultanteExecucaoComando.NENHUMA_MENSAGEM;
		}
	},

	COMANDO_NAO_RECONHECIDO {
		@Override
		public MensagemResultanteExecucaoComando executar(InterfaceEntradaJogo entradaJogo, String[] args) {
			// XXX Feio
			System.err.println("Comando nao reconhecido: " + args[0]);

			return MensagemResultanteExecucaoComando.COMANDO_NAO_RECONHECIDO;
		}
	},
	;

	public static Comando fromString(String nomeComando) {
		return buscarComandoComNome(nomeComando.toUpperCase().replace('-', '_'));
	}

	public abstract MensagemResultanteExecucaoComando executar(InterfaceEntradaJogo entradaJogo, String[] args);

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