package com.github.awvalenti.corridapatrimonial.modelodedados;

import java.math.BigDecimal;

public enum Produto {
	MESA(1000),
	COMPUTADOR(1500),
	MAQUINA_DE_CAFE(100);

	private BigDecimal precoNormal;

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
