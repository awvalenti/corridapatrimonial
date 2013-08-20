package com.github.awvalenti.corridapatrimonial.modelodedados;

import java.util.Map;
import java.util.TreeMap;

public class Patrimonio {

	Map<Produto, Integer> itens = new TreeMap<>();

	public void acrescentar(Produto produto) {
		Integer quantidade = itens.get(produto);

		if (quantidade == null) quantidade = 0;

		itens.put(produto, quantidade + 1);
	}

	public boolean estahCompleto() {
		return itens.size() == Produto.values().length;
	}

}
