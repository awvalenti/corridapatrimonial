package com.github.awvalenti.corridapatrimonial.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatadorDinheiro {

	private static final NumberFormat FORMATADOR = NumberFormat.getCurrencyInstance(new Locale("PT", "BR"));

	public static String formatar(BigDecimal preco) {
		return FORMATADOR.format(preco);
	}

}
