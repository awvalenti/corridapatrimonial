package com.github.awvalenti.corridapatrimonial.modelodedados;

import java.util.Collections;
import java.util.List;

public class Vitrine {

	public static final Vitrine VAZIA = new Vitrine();

	private List<Oferta> ofertas;

	private Vitrine() {
		this.ofertas = Collections.emptyList();
	}

	public Vitrine(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public List<Oferta> getOfertas() {
		return Collections.unmodifiableList(ofertas);
	}

	@Override
	public String toString() {
		return ofertas.toString();
	}

}
