package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.estrategias;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.FabricaVitrines;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Oferta;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Produto;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Vitrine;

public enum EstrategiaGeracaoOfertas implements FabricaVitrines {
	PRODUCAO_DE_OFERTAS_ALEATORIAS {
		private Random random = new Random();

		private <T> T umAleatorioDentre(T[] vetorOpcoes) {
			return vetorOpcoes[random.nextInt(vetorOpcoes.length)];
		}

		private BigDecimal fatorVariacaoAleatorio() {
			return umAleatorioDentre(VariacaoPrecoProduto.VARIACOES_NORMAIS).getFator();
		}

		private Oferta ofertaAleatoria() {
			Produto produto = umAleatorioDentre(Produto.TODOS);
			return new Oferta(produto, produto.getPrecoNormal().multiply(fatorVariacaoAleatorio()));
		}

		private Oferta ofertaAbsurda() {
			Produto produto = umAleatorioDentre(Produto.MAIS_CAROS);
			return new Oferta(produto, produto.getPrecoNormal().multiply(VariacaoPrecoProduto.AUMENTO_ABSURDO.getFator()));
		}

		@Override
		public Vitrine produzirVitrine() {
			ArrayList<Oferta> ofertas = new ArrayList<>();
			ofertas.add(ofertaAleatoria());
			ofertas.add(ofertaAleatoria());
			ofertas.add(ofertaAleatoria());
			ofertas.add(ofertaAbsurda());
			ofertas.add(ofertaAbsurda());
			return new Vitrine(ofertas);
		}
	}
}
