package com.github.awvalenti.corridapatrimonial.servidor.config;

import java.util.Properties;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;
import com.github.awvalenti.corridapatrimonial.util.LeitorArquivoProperties;

// XXX Sem injecao de dependencia
public enum ConfigServidor {

	// XXX Singleton... Ui!
	INSTANCIA;

	private Properties properties;

	private ConfigServidor() {
		properties = LeitorArquivoProperties.lerArquivoProperties(
				"/com/github/awvalenti/corridapatrimonial/servidor/config/ConfigServidor.properties");
	}

	// XXX Criar um getter para cada propriedade
	public String getProperty(String nomePropriedade) {
		return properties.getProperty(nomePropriedade);
	}

	// XXX Codigo replicado
	public int getIntProperty(String nomePropriedade) {
		return Integer.parseInt(getProperty(nomePropriedade));
	}

	public AlgoritmoCriptografico getAlgoritmoCriptografico() {
		return AlgoritmoCriptografico.valueOf(properties.getProperty("algoritmoCriptografico"));
	}

}
