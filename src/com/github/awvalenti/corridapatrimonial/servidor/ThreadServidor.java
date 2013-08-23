package com.github.awvalenti.corridapatrimonial.servidor;

import java.io.IOException;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.ConectorDeEntradaParaServidor;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ExecutorComandos;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ProcessadorComandosCifrados;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

public class ThreadServidor extends Thread {

	private ConectorDeEntradaParaServidor conector;

	// XXX Injecao parcial de dependencias
	public ThreadServidor(InterfaceEntradaJogo entradaJogo, AlgoritmoCriptografico algoritmoCriptografico, int portaServidor) {
		conector = new ConectorDeEntradaParaServidor(portaServidor, new ProcessadorComandosCifrados(algoritmoCriptografico,
				new ExecutorComandos(entradaJogo)));
	}


	@Override
	public void run() {
		try {
			conector.iniciarEscutaDeClientes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
