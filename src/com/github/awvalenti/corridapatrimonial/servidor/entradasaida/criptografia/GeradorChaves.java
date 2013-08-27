package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia;

public interface GeradorChaves {

	Integer buscarChave(String idCliente);

	Integer gerarChave(String idCliente);

}
