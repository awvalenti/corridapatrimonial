package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.mvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.MensagemResultanteExecucaoComando;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.FabricaJogador;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.FabricaVitrines;
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
	private FabricaVitrines fabricaVitrines;
	private GestorFabricaVitrines gestorFabricaVitrines;
	private Set<OuvinteOfertas> ouvintesOfertas = new HashSet<OuvinteOfertas>();
	private Vitrine vitrine = Vitrine.VAZIA;
	private InterfaceSaidaJogo saidaJogo;
	private FabricaJogador fabricaJogador;
	private EstadoJogoModel estado;

	public JogoModel(FabricaVitrines fabricaVitrines, GestorFabricaVitrines gestorFabricaVitrines, FabricaJogador fabricaJogador,
			InterfaceSaidaJogo saidaJogo) {
		estado = EstadoJogoModel.AGUARDANDO_INICIO;

		this.fabricaVitrines = fabricaVitrines;
		this.gestorFabricaVitrines = gestorFabricaVitrines;
		this.fabricaJogador = fabricaJogador;
		this.saidaJogo = saidaJogo;
	}

	@Override
	public synchronized MensagemResultanteExecucaoComando criarNovoJogador(String idJogador) {
		if (estado.aceitaCriarJogador() && buscarJogadorPorId(idJogador) == null) {
			Jogador jogador = fabricaJogador.fabricar(idJogador);
			jogadores.add(jogador);
			saidaJogo.aoEntrarJogador(jogador);
			return MensagemResultanteExecucaoComando.JOGADOR_ENTROU;
		} else {
			return MensagemResultanteExecucaoComando.COMANDO_REJEITADO;
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
			gestorFabricaVitrines.iniciarExecucao(fabricaVitrines, this);
			return MensagemResultanteExecucaoComando.OK;
		} else {
			return MensagemResultanteExecucaoComando.COMANDO_REJEITADO;
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
		saidaJogo.aoFecharVitrine();
	}

	@Override
	public synchronized MensagemResultanteExecucaoComando solicitarCompra(String idJogador, String idOferta) {
		if (estado.aceitaSolicitacaoCompra()) {
			Jogador jogador = buscarJogadorPorId(idJogador);
			Oferta oferta = buscarOfertaPorId(idOferta);

			if (jogador != null && oferta != null) {
				efetivarCompra(jogador, oferta);
			}
			return MensagemResultanteExecucaoComando.OK;
		} else {
			return MensagemResultanteExecucaoComando.COMANDO_REJEITADO;
		}
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

	private void efetivarCompra(Jogador jogador, Oferta oferta) {
		jogador.comprar(oferta);
		vitrine.remover(oferta);
		saidaJogo.aoEfetivarCompra(jogador, oferta);
		verificarSeJogoAcabou();
	}

	private void verificarSeJogoAcabou() {
		Jogador vencedor = buscarVencedor();

		if (vencedor != null) {
			finalizarJogo(vencedor);
		}
	}

	private Jogador buscarVencedor() {
		for (Jogador jogador : jogadores) {
			if (jogador.cumpriuObjetivo()) {
				return jogador;
			}
		}

		return null;
	}

}
