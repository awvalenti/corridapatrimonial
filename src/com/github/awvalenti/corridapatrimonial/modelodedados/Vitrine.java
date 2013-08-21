package com.github.awvalenti.corridapatrimonial.modelodedados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vitrine {

	public static final Vitrine VAZIA = new Vitrine();

	private List<Oferta> ofertasOriginais;
	private List<Oferta> ofertasAtuais;

	private Vitrine() {
		this.ofertasOriginais = this.ofertasAtuais = Collections.emptyList();
	}

	public Vitrine(List<Oferta> ofertasOriginais) {
		this.ofertasOriginais = Collections.unmodifiableList(ofertasOriginais);
		this.ofertasAtuais = new ArrayList<>(ofertasOriginais);
	}

	public List<Oferta> getOfertasOriginais() {
		return ofertasOriginais;
	}

	public List<Oferta> getOfertasAtuais() {
		return Collections.unmodifiableList(ofertasAtuais);
	}

	public void remover(Oferta oferta) {
		ofertasAtuais.remove(oferta);
	}

	@Override
	public String toString() {
		return ofertasAtuais.toString();
	}

}
