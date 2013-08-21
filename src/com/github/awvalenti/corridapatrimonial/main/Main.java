package com.github.awvalenti.corridapatrimonial.main;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.github.awvalenti.corridapatrimonial.estrategias.EstrategiaProducaoPeriodica;
import com.github.awvalenti.corridapatrimonial.estrategias.EstrategiaGeracaoOfertas;
import com.github.awvalenti.corridapatrimonial.estrategias.EstrategiaSaidaJogo;
import com.github.awvalenti.corridapatrimonial.interfaces.InterfaceEntradaJogo;
import com.github.awvalenti.corridapatrimonial.interfaces.OuvinteOfertas;
import com.github.awvalenti.corridapatrimonial.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.modelodedados.Oferta;
import com.github.awvalenti.corridapatrimonial.modelodedados.Patrimonio;
import com.github.awvalenti.corridapatrimonial.mvc.JogoModel;

public class Main implements OuvinteOfertas {

	public static void main(String[] args) {
		new Main();
	}

	private InterfaceEntradaJogo jogoModel = new JogoModel(
		Arrays.asList(
			new Jogador("j1", new Patrimonio(), BigDecimal.valueOf(500000)),
			new Jogador("j2", new Patrimonio(), BigDecimal.valueOf(500000)),
			new Jogador("j3", new Patrimonio(), BigDecimal.valueOf(500000))
		),
		EstrategiaGeracaoOfertas.PRODUCAO_DE_OFERTAS_ALEATORIAS,
		new EstrategiaProducaoPeriodica(1, 1),
		EstrategiaSaidaJogo.SAIDA_NO_CONSOLE
	);

	public Main() {
		jogoModel.adicionarOuvinteOfertas(this);
		jogoModel.iniciarJogo();
	}

	@Override
	public void aoPublicarOfertas(List<Oferta> ofertas) {
		jogoModel.solicitarCompra("j1", ofertas.get(0).getId());
		jogoModel.solicitarCompra("j2", ofertas.get(0).getId());
	}

}
