package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens.MensagemFixa;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens.MensagemJogadorEntrou;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.mensagens.MensagemResultanteExecucaoComando;
import com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas.GeradorCartoes;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.FabricaJogador;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.GestorFabricaVitrines;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceSaidaJogo;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.OuvinteOfertas;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.OuvinteVitrine;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Jogador;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Oferta;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Vitrine;

public class JogoModel implements InterfaceEntradaJogo, OuvinteVitrine {

	private List<Jogador> jogadores = new ArrayList<Jogador>();
	private GestorFabricaVitrines gestorFabricaVitrines;
	private Set<OuvinteOfertas> ouvintesOfertas = new HashSet<OuvinteOfertas>();
	private Vitrine vitrine = Vitrine.VAZIA;
	private InterfaceSaidaJogo saidaJogo;
	private FabricaJogador fabricaJogador;
	private GeradorCartoes fabricaCartoes;
	private EstadoJogoModel estado = EstadoJogoModel.AGUARDANDO_INICIO;
	private int quantidadeMaximaDePatrimoniosCompletos;

	public JogoModel(GestorFabricaVitrines gestorFabricaVitrines, FabricaJogador fabricaJogador, InterfaceSaidaJogo saidaJogo,
			GeradorCartoes fabricaCartoes, int quantidadeMaximaDePatrimoniosCompletos) {
		this.gestorFabricaVitrines = gestorFabricaVitrines;
		this.fabricaJogador = fabricaJogador;
		this.fabricaCartoes = fabricaCartoes;
		this.saidaJogo = saidaJogo;
		this.quantidadeMaximaDePatrimoniosCompletos = quantidadeMaximaDePatrimoniosCompletos;
	}

	@Override
	public synchronized MensagemResultanteExecucaoComando criarNovoJogador(String idJogador) {
		if (estado.aceitaCriarJogador() && buscarJogadorPorId(idJogador) == null) {
			String codigoCartao = fabricaCartoes.gerarCodigoCartao();
			Jogador jogador = fabricaJogador.fabricar(idJogador, codigoCartao);
			jogadores.add(jogador);
			saidaJogo.aoEntrarJogador(jogador);
			return new MensagemJogadorEntrou(codigoCartao);
		} else {
			return MensagemFixa.COMANDO_REJEITADO;
		}
	}

	@Override
	public synchronized void adicionarOuvinteOfertas(OuvinteOfertas ouvinteOfertas) {
		ouvintesOfertas.add(ouvinteOfertas);
	}

	@Override
	public synchronized MensagemResultanteExecucaoComando iniciarJogo() {
		if (estado.aceitaIniciarJogo()) {
			estado = EstadoJogoModel.RODANDO;
			saidaJogo.aoIniciarJogo();
			gestorFabricaVitrines.iniciarExecucao();
			return MensagemFixa.OK;
		} else {
			return MensagemFixa.COMANDO_REJEITADO;
		}
	}

	private void finalizarJogo(Jogador vencedor) {
		if (estado.aceitaFinalizarJogo()) {
			estado = EstadoJogoModel.FINALIZADO;
			gestorFabricaVitrines.finalizarExecucao();
			ouvintesOfertas.clear();
			aoFecharVitrine();
			saidaJogo.aoFinalizarJogo(vencedor);
		}
	}

	@Override
	@SuppressWarnings("hiding")
	public synchronized void aoAbrirVitrine(Vitrine vitrine) {
		this.vitrine = vitrine;
		saidaJogo.aoAbrirVitrine(vitrine);
		for (OuvinteOfertas o : ouvintesOfertas) {
			o.aoPublicarOfertas(vitrine.getOfertas());
		}
	}

	@Override
	public synchronized void aoFecharVitrine() {
		this.vitrine = Vitrine.VAZIA;
		saidaJogo.aoFecharVitrine(new SituacaoAtualJogo(jogadores));
	}

	@Override
	public synchronized MensagemResultanteExecucaoComando solicitarCompra(String idJogador, String idOferta, String codigoCartao) {
		if (estado.aceitaSolicitacaoCompra()) {
			Jogador comprador = buscarJogadorPorId(idJogador);
			Oferta oferta = buscarOfertaPorId(idOferta);
			Jogador pagante = buscarJogadorPorCodigoCartao(codigoCartao);

			if (comprador != null && oferta != null && pagante != null) {
				efetivarCompra(comprador, pagante, oferta);
				return MensagemFixa.OK;
			}
		}

		return MensagemFixa.COMANDO_REJEITADO;
	}

	private Jogador buscarJogadorPorCodigoCartao(String codigoCartao) {
		for (Jogador jogador : jogadores) {
			if (jogador.getCodigoCartao().equals(codigoCartao)) {
				return jogador;
			}
		}
		return null;
	}

	private Jogador buscarJogadorPorId(String idJogador) {
		for (Jogador jogador : jogadores) {
			if (jogador.getId().equals(idJogador)) {
				return jogador;
			}
		}
		return null;
	}

	private Oferta buscarOfertaPorId(String idOferta) {
		for (Oferta oferta : vitrine.getOfertas()) {
			if (oferta.getId().equals(idOferta)) {
				return oferta;
			}
		}
		return null;
	}

	private void efetivarCompra(Jogador comprador, Jogador pagante, Oferta oferta) {
		comprador.patrimoniar(oferta.getProduto());
		pagante.pagar(oferta.getPreco());

		vitrine.remover(oferta);
		saidaJogo.aoEfetivarCompra(comprador, oferta);
		verificarSeJogoAcabou();
	}

	private void verificarSeJogoAcabou() {
		if (contarQuantidadeDePatrimoniosCompletos() >= quantidadeMaximaDePatrimoniosCompletos) {
			finalizarJogo(buscarJogadorComMaisDinheiroEPatrimonioCompleto());
		}
	}

	private Jogador buscarJogadorComMaisDinheiroEPatrimonioCompleto() {
		List<Jogador> jogadoresPorOrdemDecrescenteDeDinheiro = new ArrayList<Jogador>(jogadores);

		Collections.sort(jogadoresPorOrdemDecrescenteDeDinheiro, new Comparator<Jogador>() {
			@Override
			public int compare(Jogador o1, Jogador o2) {
				return o2.getDinheiro().compareTo(o1.getDinheiro());
			}
		});

		for (Jogador jogador : jogadoresPorOrdemDecrescenteDeDinheiro) {
			if (jogador.patrimonioEstahCompleto()) {
				return jogador;
			}
		}

		throw new IllegalStateException("Nao deve chegar aqui");
	}

	private int contarQuantidadeDePatrimoniosCompletos() {
		int cont = 0;

		for (Jogador jogador : jogadores) {
			if (jogador.patrimonioEstahCompleto()) {
				++cont;
			}
		}

		return cont;
	}

}
