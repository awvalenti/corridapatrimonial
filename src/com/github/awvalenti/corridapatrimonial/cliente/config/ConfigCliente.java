package com.github.awvalenti.corridapatrimonial.cliente.config;

import java.util.Properties;

import com.github.awvalenti.corridapatrimonial.util.LeitorArquivoProperties;

// XXX Sem injecao de dependencia
public enum ConfigCliente {

	// XXX Singleton... Ui!
	INSTANCIA;

	private Properties properties;

	private ConfigCliente() {
		properties = LeitorArquivoProperties.lerArquivoProperties(
				"/com/github/awvalenti/corridapatrimonial/cliente/config/ConfigCliente.properties");
	}

	// XXX Criar um getter para cada propriedade
	public String getProperty(String nomePropriedade) {
		return properties.getProperty(nomePropriedade);
	}

	public int getIntProperty(String nomePropriedade) {
		return Integer.parseInt(getProperty(nomePropriedade));
	}

}
