package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia;

import java.util.HashMap;
import java.util.Map;

public class GeradorChaves {

	private static final int[] CHAVES_POSSIVEIS = { 17, 4, 19, 10, 7, 12, 15, 21, 18, 6, 9, 11, 14 };

	private int indiceUltimaChaveGerada = 0;

	private Map<String, Integer> chavesPorIdCliente = new HashMap<String, Integer>();


	public synchronized int buscarOuGerarChave(String idCliente) {
		Integer chave = chavesPorIdCliente.get(idCliente);

		return chave != null ? chave : gerarNovaChave(idCliente);
	}


	private Integer gerarNovaChave(String idCliente) {
		int chaveGerada = CHAVES_POSSIVEIS[indiceUltimaChaveGerada++ % CHAVES_POSSIVEIS.length];

		chavesPorIdCliente.put(idCliente, chaveGerada);

		return chaveGerada;
	}

}
