package com.github.awvalenti.corridapatrimonial.servidor.main;

import java.io.IOException;

import com.github.awvalenti.corridapatrimonial.servidor.ThreadServidor;
import com.github.awvalenti.corridapatrimonial.servidor.config.ConfigServidor;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;
import com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas.FabricaJogoModel;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;
import com.github.awvalenti.corridapatrimonial.util.LeitorEscritor;

public class MainServidor {

	public static void main(String[] args) throws IOException {
		final InterfaceEntradaJogo entradaJogo = FabricaJogoModel.criarJogoModel();

		ConfigServidor configServidor = ConfigServidor.INSTANCIA;
		final AlgoritmoCriptografico algoritmoCriptografico = configServidor.getAlgoritmoCriptografico();
		final int portaServidor = configServidor.getIntProperty("portaServidor");

		new ThreadServidor(entradaJogo, algoritmoCriptografico, portaServidor).start();

		System.out.println("Servidor iniciado. Aguardando jogadores. Pressione ENTER para iniciar o jogo.");

		LeitorEscritor.lerLinhaConsole();

		entradaJogo.iniciarJogo();
	}
}
