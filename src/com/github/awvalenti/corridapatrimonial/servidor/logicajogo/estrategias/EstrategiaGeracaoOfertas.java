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

		private Oferta ofertaAleatoria(int idOferta) {
			Produto produto = umAleatorioDentre(Produto.TODOS);
			return new Oferta(String.valueOf(idOferta), produto, produto.getPrecoNormal().multiply(fatorVariacaoAleatorio()));
		}

		@Override
		public Vitrine produzirVitrine() {
			ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
			int id = 1;
			ofertas.add(ofertaAleatoria(id++));
			ofertas.add(ofertaAleatoria(id++));
			ofertas.add(ofertaAleatoria(id++));
			ofertas.add(ofertaAleatoria(id++));
			return new Vitrine(ofertas);
		}
	}
}
