package com.github.awvalenti.corridapatrimonial.estrategias;

import java.util.Timer;
import java.util.TimerTask;

import com.github.awvalenti.corridapatrimonial.interfaces.GestorFabricaVitrines;
import com.github.awvalenti.corridapatrimonial.interfaces.FabricaVitrines;
import com.github.awvalenti.corridapatrimonial.interfaces.OuvinteVitrine;

public class EstrategiaProducaoPeriodica implements GestorFabricaVitrines {
	private final Timer timer = new Timer();
	private boolean continuarExecutando = true;
	private long periodo;

	public EstrategiaProducaoPeriodica(long periodo) {
		this.periodo = periodo;
	}

	@Override
	public void iniciarExecucao(final FabricaVitrines fabricaVitrines, final OuvinteVitrine ouvinteVitrine) {
		ouvinteVitrine.aoAbrirVitrine(fabricaVitrines.produzirVitrine());

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (continuarExecutando) {
					ouvinteVitrine.aoFecharVitrine();
					ouvinteVitrine.aoAbrirVitrine(fabricaVitrines.produzirVitrine());
				} else {
					timer.cancel();
				}
			}
		}, periodo, periodo);
	}

	@Override
	public void finalizarExecucao() {
		continuarExecutando = false;
	}

}