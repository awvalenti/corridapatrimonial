package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens;

public enum MensagemFixa implements MensagemResultanteExecucaoComando {
	OK,

	COMANDO_REJEITADO,

	COMANDO_NAO_RECONHECIDO,

	;

	@Override
	public String getTexto() {
		return name();
	}

	@Override
	public boolean indicaQueJogadorEntrou() {
		return false;
	}

}