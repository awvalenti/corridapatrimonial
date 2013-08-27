package com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas;

import java.math.BigDecimal;

import com.github.awvalenti.corridapatrimonial.servidor.config.ConfigServidor;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.SaidaJogoNoConsole;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.estrategias.EstrategiaGeracaoOfertas;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.estrategias.EstrategiaProducaoPeriodica;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.GestorFabricaVitrines;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.mvc.JogoModel;

public class FabricaJogoModel {

	public static InterfaceEntradaJogo criarJogoModel() {
		ConfigServidor config = ConfigServidor.INSTANCIA;

		return fabricarJogoModel(
			new BigDecimal(config.getProperty("dinheiroInicial")),
			Long.parseLong(config.getProperty("duracaoVitrineAberta")),
			Long.parseLong(config.getProperty("duracaoVitrineFechada")),
			config.getIntProperty("quantidadeMaximaDePatrimoniosCompletos")
		);
	}

	private static InterfaceEntradaJogo fabricarJogoModel(BigDecimal dinheiroInicial, long duracaoVitrineAberta,
			long duracaoVitrineFechada, int quantidadeMaximaDePatrimoniosCompletos) {

		GestorFabricaVitrines gestorFabricaVitrines = new EstrategiaProducaoPeriodica(
				EstrategiaGeracaoOfertas.PRODUCAO_DE_OFERTAS_ALEATORIAS, duracaoVitrineAberta, duracaoVitrineFechada);

		JogoModel jogoModel = new JogoModel(
			gestorFabricaVitrines,
			new FabricaJogadorPadrao(dinheiroInicial),
			SaidaJogoNoConsole.INSTANCIA,
			new GeradorCartoesPorSorteio(),
			quantidadeMaximaDePatrimoniosCompletos
		);

		gestorFabricaVitrines.setOuvinteVitrine(jogoModel);

		return jogoModel;
	}

}
