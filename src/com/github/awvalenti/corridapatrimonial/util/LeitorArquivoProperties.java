package com.github.awvalenti.corridapatrimonial.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas.FabricaJogoModel;

public class LeitorArquivoProperties {

	public static Properties lerArquivoProperties(String caminhoNoClasspath) {
		Properties config = new Properties();
		try {
			InputStream resourceAsStream = FabricaJogoModel.class.getResourceAsStream(caminhoNoClasspath);
			if (resourceAsStream == null) {
				throw new IllegalArgumentException("Recurso nao encontrado em " + caminhoNoClasspath);
			}
			config.load(resourceAsStream);
			return config;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
