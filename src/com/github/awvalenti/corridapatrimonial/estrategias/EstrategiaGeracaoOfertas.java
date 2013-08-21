package com.github.awvalenti.corridapatrimonial.estrategias;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.github.awvalenti.corridapatrimonial.interfaces.FabricaVitrines;
import com.github.awvalenti.corridapatrimonial.modelodedados.Oferta;
import com.github.awvalenti.corridapatrimonial.modelodedados.Produto;
import com.github.awvalenti.corridapatrimonial.modelodedados.Vitrine;

public enum EstrategiaGeracaoOfertas implements FabricaVitrines {
	PRODUCAO_DE_4_OFERTAS_ALEATORIAS {
		private Random random = new Random();

		private Produto produtoAleatorio() {
			return Produto.values()[random.nextInt(Produto.values().length)];
		}

		private BigDecimal fatorVariacaoAleatorio() {
			return VariacaoPrecoProduto.values()[random.nextInt(VariacaoPrecoProduto.values().length)].getFator();
		}

		private Oferta ofertaAleatoria() {
			Produto produto = produtoAleatorio();
			return new Oferta(produto, produto.getPrecoNormal().multiply(fatorVariacaoAleatorio()));
		}

		@Override
		public Vitrine produzirVitrine() {
			ArrayList<Oferta> ofertas = new ArrayList<>();
			ofertas.add(ofertaAleatoria());
			ofertas.add(ofertaAleatoria());
			ofertas.add(ofertaAleatoria());
			ofertas.add(ofertaAleatoria());
			return new Vitrine(ofertas);
		}
	}
}
