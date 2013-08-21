package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.estrategias;

import java.math.BigDecimal;

public enum VariacaoPrecoProduto {
	DESCONTO(new BigDecimal("0.8")),
	SEM_ALTERACAO(BigDecimal.ONE),
	AUMENTO_NORMAL(new BigDecimal("1.4")),

	AUMENTO_ABSURDO(new BigDecimal("2.5")),
	;

	private BigDecimal fator;

	public static final VariacaoPrecoProduto[] VARIACOES_NORMAIS = new VariacaoPrecoProduto[] {
		DESCONTO, SEM_ALTERACAO, AUMENTO_NORMAL
	};

	private VariacaoPrecoProduto(BigDecimal fator) {
		this.fator = fator;
	}

	public BigDecimal getFator() {
		return fator;
	}
}