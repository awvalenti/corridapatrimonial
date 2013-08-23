package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.mvc;

public enum EstadoJogoModel {
	AGUARDANDO_INICIO(true, true, false, false),
	RODANDO(false, false, true, true),
	FINALIZADO(false, false, false, false),

	;

	private final boolean aceitaCriarJogador;
	private final boolean aceitaIniciarJogo;
	private final boolean aceitaFinalizarJogo;
	private final boolean aceitaSolicitacaoCompra;

	private EstadoJogoModel(boolean aceitaCriarJogador, boolean aceitaIniciarJogo, boolean aceitaFinalizarJogo,
			boolean aceitaSolicitacaoCompra) {
		this.aceitaCriarJogador = aceitaCriarJogador;
		this.aceitaIniciarJogo = aceitaIniciarJogo;
		this.aceitaFinalizarJogo = aceitaFinalizarJogo;
		this.aceitaSolicitacaoCompra = aceitaSolicitacaoCompra;
	}

	public boolean aceitaCriarJogador() {
		return aceitaCriarJogador;
	}

	public boolean aceitaIniciarJogo() {
		return aceitaIniciarJogo;
	}

	public boolean aceitaFinalizarJogo() {
		return aceitaFinalizarJogo;
	}

	public boolean aceitaSolicitacaoCompra() {
		return aceitaSolicitacaoCompra;
	}
}
