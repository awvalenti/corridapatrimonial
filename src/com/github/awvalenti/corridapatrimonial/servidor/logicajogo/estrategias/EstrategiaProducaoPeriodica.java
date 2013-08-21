package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.estrategias;

import java.util.Timer;
import java.util.TimerTask;

import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.FabricaVitrines;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.GestorFabricaVitrines;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.OuvinteVitrine;

public class EstrategiaProducaoPeriodica implements GestorFabricaVitrines {
	private final Timer timer = new Timer();
	private volatile boolean continuarExecutando = true;
	private long duracaoAberta;
	private long duracaoFechada;
	private FabricaVitrines fabricaVitrines;
	private OuvinteVitrine ouvinteVitrine;

	public EstrategiaProducaoPeriodica(long duracaoAberta, long duracaoFechada) {
		this.duracaoAberta = duracaoAberta;
		this.duracaoFechada = duracaoFechada;
	}

	@Override
	@SuppressWarnings("hiding")
	public void iniciarExecucao(final FabricaVitrines fabricaVitrines, final OuvinteVitrine ouvinteVitrine) {
		this.fabricaVitrines = fabricaVitrines;
		this.ouvinteVitrine = ouvinteVitrine;

		agendarAbertura();
	}

	private void agendarAbertura() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (verificarAtividadeTimer()) {
					ouvinteVitrine.aoAbrirVitrine(fabricaVitrines.produzirVitrine());
					agendarFechamento();
				}
			}
		}, duracaoFechada);
	}

	private void agendarFechamento() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (verificarAtividadeTimer()) {
					ouvinteVitrine.aoFecharVitrine();
					agendarAbertura();
				}
			}
		}, duracaoAberta);
	}

	private synchronized boolean verificarAtividadeTimer() {
		if (!continuarExecutando) {
			timer.cancel();
		}

		return continuarExecutando;
	}

	@Override
	public synchronized void finalizarExecucao() {
		continuarExecutando = false;
	}

}