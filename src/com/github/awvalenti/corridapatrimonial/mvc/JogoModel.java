package com.github.awvalenti.corridapatrimonial.mvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.awvalenti.corridapatrimonial.estrategias.EstrategiaDeTempoParaProducaoVitrines;
import com.github.awvalenti.corridapatrimonial.interfaces.FabricaVitrines;
import com.github.awvalenti.corridapatrimonial.interfaces.InterfaceJogo;
import com.github.awvalenti.corridapatrimonial.interfaces.OuvinteVitrine;
import com.github.awvalenti.corridapatrimonial.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.modelodedados.Oferta;
import com.github.awvalenti.corridapatrimonial.modelodedados.Vitrine;



public class JogoModel implements InterfaceJogo, OuvinteVitrine {

	private List<Jogador> jogadores;
	private FabricaVitrines fabricaVitrines;
	private EstrategiaDeTempoParaProducaoVitrines estrategiaDeTempoParaProducaoVitrines;
	private Set<OuvinteVitrine> observadoresVitrine = new HashSet<>();
	private Vitrine vitrine;

	public JogoModel(List<Jogador> jogadores, FabricaVitrines fabricaVitrines,
			EstrategiaDeTempoParaProducaoVitrines estrategiaDeTempoParaProducaoVitrines) {
		this.jogadores = jogadores;
		this.fabricaVitrines = fabricaVitrines;
		this.estrategiaDeTempoParaProducaoVitrines = estrategiaDeTempoParaProducaoVitrines;
	}

	@Override
	public synchronized void adicionarObservadorVitrine(OuvinteVitrine observador) {
		observadoresVitrine.add(observador);
	}

	@Override
	public synchronized void iniciar() {
		estrategiaDeTempoParaProducaoVitrines.iniciar(fabricaVitrines, this);
	}

	@Override
	public synchronized void aoAbrirVitrine(Vitrine vitrine) {
		this.vitrine = vitrine;
		for (OuvinteVitrine o : observadoresVitrine) {
			o.aoAbrirVitrine(vitrine);
		}
	}

	@Override
	public synchronized void aoFecharVitrine() {
		this.vitrine = Vitrine.VAZIA;
		for (OuvinteVitrine o : observadoresVitrine) {
			o.aoFecharVitrine();
		}
	}

	@Override
	public synchronized void solicitarCompra(String idJogador, String idOferta) {
		for (Jogador jogador : jogadores) {
			if (jogador.getId().equals(idJogador)) {
				for (Oferta oferta : vitrine.getOfertas()) {
					if (oferta.getId().equals(idOferta)) {
						efetivarCompra(jogador, oferta);
					}
				}
			}
		}
	}

	private void efetivarCompra(Jogador jogador, Oferta oferta) {
		System.out.println(jogador + " comprando " + oferta);

		jogador.comprar(oferta);
		verificarSeJogoAcabou();
	}

	private void verificarSeJogoAcabou() {
		Jogador vencedor = buscarVencedor();

		if (vencedor != null) {
			finalizarJogo(vencedor);
		}
	}

	private Jogador buscarVencedor() {
		for (Jogador jogador : jogadores) {
			if (jogador.cumpriuObjetivo()) {
				return jogador;
			}
		}

		return null;
	}

	private void finalizarJogo(Jogador vencedor) {
		System.out.println("Aopa, venceu!");
		this.observadoresVitrine.clear();
	}

}
