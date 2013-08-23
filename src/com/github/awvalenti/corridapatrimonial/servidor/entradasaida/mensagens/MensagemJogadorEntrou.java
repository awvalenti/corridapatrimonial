package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens;

public class MensagemJogadorEntrou implements MensagemResultanteExecucaoComando {

	private int chaveCriptografica;
	private String codigoCartao;

	public MensagemJogadorEntrou(String codigoCartao) {
		this.codigoCartao = codigoCartao;
	}

	public void setChaveCriptografica(int chaveCriptografica) {
		this.chaveCriptografica = chaveCriptografica;
	}

	@Override
	public String getTexto() {
		return "Voce entrou no jogo. Sua chave criptografica e' " + chaveCriptografica + " e seu codigo de cartao de credito e' " + codigoCartao;
	}

	@Override
	public boolean indicaQueJogadorEntrou() {
		return true;
	}

}
