package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados;

import java.util.Collections;
import java.util.List;

public class Vitrine {

	private List<Oferta> ofertas;

	public static final Vitrine VAZIA = new Vitrine(Collections.<Oferta>emptyList());

	public Vitrine(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void remover(Oferta oferta) {
		ofertas.remove(oferta);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{\n");

		for (Oferta oferta : ofertas) {
			sb.append('\t').append(oferta).append('\n');
		}



		return sb.append('}').toString();
	}

}
