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

	public EstrategiaProducaoPeriodica(FabricaVitrines fabricaVitrines, long duracaoAberta, long duracaoFechada) {
		this.fabricaVitrines = fabricaVitrines;
		this.duracaoAberta = duracaoAberta;
		this.duracaoFechada = duracaoFechada;
	}

	@Override
	public void setOuvinteVitrine(OuvinteVitrine ouvinteVitrine) {
		this.ouvinteVitrine = ouvinteVitrine;
	}

	@Override
	public void iniciarExecucao() {
		agendarAbertura();
	}

	@Override
	public synchronized void finalizarExecucao() {
		continuarExecutando = false;
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

}