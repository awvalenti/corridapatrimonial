package com.github.awvalenti.corridapatrimonial.servidor;

import java.io.IOException;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.Servidor;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;
import com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas.FabricaServidor;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

public class ThreadServidor extends Thread {

	private Servidor conector;

	// XXX Injecao parcial de dependencias
	public ThreadServidor(InterfaceEntradaJogo entradaJogo, AlgoritmoCriptografico algoritmoCriptografico, int portaServidor) {
		conector = FabricaServidor.fabricar(portaServidor, algoritmoCriptografico, entradaJogo);
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
