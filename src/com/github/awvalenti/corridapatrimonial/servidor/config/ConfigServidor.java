package com.github.awvalenti.corridapatrimonial.servidor.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;
import com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas.FabricaJogoModel;

// XXX Sem injecao de dependencia
public enum ConfigServidor {

	// XXX Singleton... Ui!
	INSTANCIA;

	private Properties properties;

	private ConfigServidor() {
		properties = lerArquivoProperties();
	}

	// XXX Criar um getter para cada propriedade
	public String getProperty(String nomePropriedade) {
		return properties.getProperty(nomePropriedade);
	}

	private static Properties lerArquivoProperties() {
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

	public AlgoritmoCriptografico getAlgoritmoCriptografico() {
		return AlgoritmoCriptografico.valueOf(properties.getProperty("algoritmoCriptografico"));
	}

}
