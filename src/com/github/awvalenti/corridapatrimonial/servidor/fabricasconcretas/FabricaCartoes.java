package com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FabricaCartoes {

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

	public FabricaCartoes() {
		codigosEmOrdemAleatoria = new ArrayList<String>(Arrays.asList(CODIGOS));
//		Collections.shuffle(codigosEmOrdemAleatoria);
	}

	public synchronized String fabricarCodigoCartao() {
		return codigosEmOrdemAleatoria.get(indiceAtual++ % codigosEmOrdemAleatoria.size());
	}

}
