package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados;

import java.math.BigDecimal;

public enum Produto {
	MAQUINA_DE_CAFE(100),
	PO_DE_CAFE(10),
	SEIS_CADEIRAS(500),
	MESA(900),
	COMPUTADOR_PESSOAL(1500),
	FOGAO(1000),
	GELADEIRA(2000),
	SERVIDOR(5000),
	;

	private BigDecimal precoNormal;

	public static final Produto[] TODOS = values();
	public static final Produto[] MAIS_CAROS = new Produto[] { FOGAO, GELADEIRA, SERVIDOR };

	Produto(int precoNormal) {
		this(BigDecimal.valueOf(precoNormal));
	}

	Produto(BigDecimal precoNormal) {
		this.precoNormal = precoNormal;
	}

	public BigDecimal getPrecoNormal() {
		return precoNormal;
	}

}
