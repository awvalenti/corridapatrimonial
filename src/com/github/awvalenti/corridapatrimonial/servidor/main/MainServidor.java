package com.github.awvalenti.corridapatrimonial.servidor.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.awvalenti.corridapatrimonial.servidor.ThreadServidor;
import com.github.awvalenti.corridapatrimonial.servidor.config.ConfigServidor;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;
import com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas.FabricaJogoModel;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

public class MainServidor {

	public static void main(String[] args) throws IOException {
		final InterfaceEntradaJogo entradaJogo = FabricaJogoModel.criarJogoModel();

		ConfigServidor configServidor = ConfigServidor.INSTANCIA;
		final AlgoritmoCriptografico algoritmoCriptografico = configServidor.getAlgoritmoCriptografico();
		final int portaServidor = configServidor.getIntProperty("portaServidor");

		new ThreadServidor(entradaJogo, algoritmoCriptografico, portaServidor).start();

		System.out.println("Servidor iniciado. Aguardando jogadores. Pressione ENTER para iniciar o jogo.");

		new BufferedReader(new InputStreamReader(System.in)).readLine();

		entradaJogo.iniciarJogo();
	}
}
