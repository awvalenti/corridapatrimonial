package com.github.awvalenti.corridapatrimonial.main;

import java.math.BigDecimal;
import java.util.Arrays;

import com.github.awvalenti.corridapatrimonial.estrategias.EstrategiaDeTempoParaProducaoVitrines;
import com.github.awvalenti.corridapatrimonial.estrategias.EstrategiaProducaoVitrines;
import com.github.awvalenti.corridapatrimonial.interfaces.InterfaceJogo;
import com.github.awvalenti.corridapatrimonial.interfaces.OuvinteVitrine;
import com.github.awvalenti.corridapatrimonial.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.modelodedados.Patrimonio;
import com.github.awvalenti.corridapatrimonial.modelodedados.Vitrine;
import com.github.awvalenti.corridapatrimonial.mvc.JogoModel;

public class Main implements OuvinteVitrine {

	public static void main(String[] args) {
		new Main();
	}

	private InterfaceJogo jogoModel = new JogoModel(
		Arrays.asList(
			new Jogador("j1", new Patrimonio(), BigDecimal.valueOf(5000)),
			new Jogador("j2", new Patrimonio(), BigDecimal.valueOf(5000)),
			new Jogador("j3", new Patrimonio(), BigDecimal.valueOf(5000))
		),
		EstrategiaProducaoVitrines.PRODUCAO_DE_OFERTAS_ALEATORIAS,
		EstrategiaDeTempoParaProducaoVitrines.PRODUCAO_COM_PAUSAS_A_CADA_10_CICLOS
	);

	public Main() {
		jogoModel.adicionarObservadorVitrine(this);
		jogoModel.iniciar();
	}

	@Override
	public void aoAbrirVitrine(Vitrine vitrine) {
		jogoModel.solicitarCompra("j1", vitrine.getOfertas().get(0).getId());
	}

	@Override
	public void aoFecharVitrine() {
	}

}
