package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

enum Comando {
	ENTRAR {
		@Override
		public void executar(InterfaceEntradaJogo entradaJogo, String[] args) {
			entradaJogo.criarNovoJogador(args[0]);
		}
	},

	COMPRAR {
		@Override
		public void executar(InterfaceEntradaJogo entradaJogo, String[] args) {
			entradaJogo.solicitarCompra(args[0], args[1]);
		}
	},
	;

	public static Comando fromString(String nomeComando) {
		return buscarComandoComNome(nomeComando.toUpperCase().replace('-', '_'));
	}

	public abstract void executar(InterfaceEntradaJogo entradaJogo, String[] args);

	@Override
	public String toString() {
		return name().replace('_', '-').toLowerCase();
	}

	private static Comando buscarComandoComNome(String nomeComando) {
		for (Comando comando : values()) {
			if (comando.name().equals(nomeComando)) {
				return comando;
			}
		}

		return null;
	}

}