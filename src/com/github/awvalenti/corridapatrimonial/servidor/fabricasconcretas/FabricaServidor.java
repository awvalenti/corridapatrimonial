package com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.EntradaSaidaServidor;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.EstrategiaIdentificacaoCliente;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.Servidor;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ExecutorComandos;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ProcessadorComandosCifrados;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.GeradorChaves;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

public class FabricaServidor {

	public static Servidor fabricar(int portaServidor, AlgoritmoCriptografico algoritmoCriptografico, InterfaceEntradaJogo entradaJogo) {
		return new Servidor(portaServidor, EstrategiaIdentificacaoCliente.POR_HOST_NAME, new EntradaSaidaServidor(
				new ProcessadorComandosCifrados(algoritmoCriptografico, new ExecutorComandos(entradaJogo)), new GeradorChaves()));
	}

}
