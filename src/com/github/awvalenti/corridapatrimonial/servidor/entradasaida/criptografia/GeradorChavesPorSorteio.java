package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeradorChavesPorSorteio implements GeradorChaves {

	private static final List<Integer> CHAVES_POSSIVEIS = Arrays.asList(
			17, 4, 19, 10, 7, 12, 15, 21, 18, 6, 9, 11, 14);

	private int indiceUltimaChaveGerada = 0;

	private Map<String, Integer> chavesPorIdCliente = new HashMap<String, Integer>();

	private List<Integer> chavesEmOrdemAleatoria;

	public GeradorChavesPorSorteio() {
		chavesEmOrdemAleatoria = new ArrayList<Integer>(CHAVES_POSSIVEIS);
		Collections.shuffle(chavesEmOrdemAleatoria);
	}

	@Override
	public synchronized Integer buscarChave(String idCliente) {
		return chavesPorIdCliente.get(idCliente);
	}

	@Override
	public synchronized Integer gerarChave(String idCliente) {
		int chaveGerada = chavesEmOrdemAleatoria.get(indiceUltimaChaveGerada++ % chavesEmOrdemAleatoria.size());

		chavesPorIdCliente.put(idCliente, chaveGerada);

		return chaveGerada;
	}

}
