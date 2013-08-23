package com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas;

import java.math.BigDecimal;

import com.github.awvalenti.corridapatrimonial.servidor.config.ConfigServidor;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.SaidaJogoNoConsole;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.estrategias.EstrategiaGeracaoOfertas;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.estrategias.EstrategiaProducaoPeriodica;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.mvc.JogoModel;

public class FabricaJogoModel {

	public static InterfaceEntradaJogo criarJogoModel() {
		ConfigServidor config = ConfigServidor.INSTANCIA;

		return fabricarJogoModel(
			new BigDecimal(config.getProperty("dinheiroInicial")),
			Long.parseLong(config.getProperty("duracaoVitrineAberta")),
			Long.parseLong(config.getProperty("duracaoVitrineFechada"))
		);
	}

	private static InterfaceEntradaJogo fabricarJogoModel(BigDecimal dinheiroInicial, long duracaoVitrineAberta,
			long duracaoVitrineFechada) {

		return new JogoModel(
			EstrategiaGeracaoOfertas.PRODUCAO_DE_OFERTAS_ALEATORIAS,
			new EstrategiaProducaoPeriodica(duracaoVitrineAberta, duracaoVitrineFechada),
			new FabricaJogadorPadrao(dinheiroInicial),
			SaidaJogoNoConsole.INSTANCIA,
			new FabricaCartoes()
		);
	}

}
