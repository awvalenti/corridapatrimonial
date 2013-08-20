package com.github.awvalenti.corridapatrimonial.estrategias;

import java.math.BigDecimal;

public enum VariacaoPrecoProduto {
	COM_DESCONTO(new BigDecimal("0.8")),
	NORMAL(BigDecimal.ONE),
	COM_AUMENTO(new BigDecimal("1.4"));

	private BigDecimal fator;

	private VariacaoPrecoProduto(BigDecimal fator) {
		this.fator = fator;
	}

	public BigDecimal getFator() {
		return fator;
	}
}