package com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.SaidaJogoNoConsole;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.estrategias.EstrategiaGeracaoOfertas;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.estrategias.EstrategiaProducaoPeriodica;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.mvc.JogoModel;

public class FabricaJogoModel {

	public static InterfaceEntradaJogo criarJogoModel() {
		Properties config = recuperarConfig();

		return fabricarJogoModel(
			new BigDecimal(config.getProperty("dinheiroInicial")),
			Long.parseLong(config.getProperty("duracaoVitrineAberta")),
			Long.parseLong(config.getProperty("duracaoVitrineFechada"))
		);
	}

	private static Properties recuperarConfig() {
		Properties config = new Properties();
		try {
			final String caminho = "/com/github/awvalenti/corridapatrimonial/servidor/ConfigServidor.properties";
			InputStream resourceAsStream = FabricaJogoModel.class.getResourceAsStream(caminho);
			if (resourceAsStream == null) {
				throw new IllegalArgumentException("Recurso nao encontrado em " + caminho);
			}
			config.load(resourceAsStream);
			return config;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static InterfaceEntradaJogo fabricarJogoModel(BigDecimal dinheiroInicial, long duracaoVitrineAberta,
			long duracaoVitrineFechada) {

		JogoModel jogoModel = new JogoModel(
			EstrategiaGeracaoOfertas.PRODUCAO_DE_OFERTAS_ALEATORIAS,
			new EstrategiaProducaoPeriodica(duracaoVitrineAberta, duracaoVitrineFechada),
			new FabricaJogadorPadrao(dinheiroInicial),
			SaidaJogoNoConsole.INSTANCIA
		);

		jogoModel.criarNovoJogador("j1");
		jogoModel.criarNovoJogador("j2");
		jogoModel.criarNovoJogador("j3");

		return jogoModel;
	}

}
