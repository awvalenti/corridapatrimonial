package com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GeradorCartoesPorSorteio implements GeradorCartoes {

	private static final String[] CODIGOS = {
		"mario",
		"prince",
		"turing",
		"vernam",
		"mickey",
		"cesar",
		"koji",
		"pacman",
		"bond",
		"jack",
		"john",
		"lennon",
		"allstars",
		"special",
		"allan",
		"sonic",
		"rita",
		"raj",
		"ghouls",
		"road",
		"yoshi",
		"tails",
		"shyguy",
	};

	private List<String> codigosEmOrdemAleatoria;
	private int indiceAtual = 0;

	public GeradorCartoesPorSorteio() {
		codigosEmOrdemAleatoria = new ArrayList<String>(Arrays.asList(CODIGOS));
		Collections.shuffle(codigosEmOrdemAleatoria);
	}

	@Override
	public synchronized String gerarCodigoCartao() {
		return codigosEmOrdemAleatoria.get(indiceAtual++ % codigosEmOrdemAleatoria.size());
	}

}
