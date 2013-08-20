package com.github.awvalenti.corridapatrimonial.estrategias;

import java.util.Timer;
import java.util.TimerTask;

import com.github.awvalenti.corridapatrimonial.interfaces.FabricaVitrines;
import com.github.awvalenti.corridapatrimonial.interfaces.OuvinteVitrine;



public enum EstrategiaDeTempoParaProducaoVitrines {
	PRODUCAO_A_CADA_2_SEGUNDOS {
		@Override
		public void iniciar(final FabricaVitrines fabricaVitrines, final OuvinteVitrine ouvinteVitrine) {
			final Timer timer = new Timer();

			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					ouvinteVitrine.aoAbrirVitrine(fabricaVitrines.produzirVitrine());
				}
			}, 2000);

			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					ouvinteVitrine.aoFecharVitrine();
					ouvinteVitrine.aoAbrirVitrine(fabricaVitrines.produzirVitrine());
				}
			}, 4000, 2000);
		}
	},

	PRODUCAO_COM_PAUSAS_A_CADA_10_CICLOS {
		@Override
		public void iniciar(FabricaVitrines fabricaVitrines, OuvinteVitrine ouvinteVitrine) {
			ouvinteVitrine.aoAbrirVitrine(fabricaVitrines.produzirVitrine());
			for (;;) {
				for (int i = 0; i < 10; ++i) {
					ouvinteVitrine.aoFecharVitrine();
					ouvinteVitrine.aoAbrirVitrine(fabricaVitrines.produzirVitrine());
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	};

	abstract public void iniciar(FabricaVitrines fabricaVitrines, OuvinteVitrine ouvinteVitrine);

}
