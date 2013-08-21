package com.github.awvalenti.corridapatrimonial.modelodedados;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Patrimonio {

	private Map<Produto, Integer> itens = new TreeMap<>();

	private static final Collection<Produto> CACHE_TODOS_PRODUTOS = Arrays.asList(Produto.TODOS);

	public void acrescentar(Produto produto) {
		Integer quantidade = itens.get(produto);

		if (quantidade == null) quantidade = 0;

		itens.put(produto, quantidade + 1);
	}

	public boolean estahCompleto() {
		return itens.keySet().containsAll(CACHE_TODOS_PRODUTOS);
	}

}
