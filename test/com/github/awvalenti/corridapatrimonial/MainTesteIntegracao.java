package com.github.awvalenti.corridapatrimonial;

import java.io.IOException;

import com.github.awvalenti.corridapatrimonial.cliente.ConectorDeSaidaDoCliente;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.ConectorDeEntradaParaServidor;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ExecutorComandos;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ProcessadorComandosCifrados;
import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;
import com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas.FabricaJogoModel;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;

public class MainTesteIntegracao {

	public static void main(String[] args) throws InterruptedException, IOException {
		final InterfaceEntradaJogo entradaJogo = FabricaJogoModel.criarJogoModel();

		final AlgoritmoCriptografico algoritmoCriptografico = AlgoritmoCriptografico.SEM_CRIPTOGRAFIA;

		new Thread() {
			@Override
			public void run() {
				try {
					new ConectorDeEntradaParaServidor(8080, new ProcessadorComandosCifrados(
							algoritmoCriptografico, new ExecutorComandos(entradaJogo))).iniciarEscutaDeClientes();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}.start();

		Thread.sleep(50);

		enviarLinhaComando("entrar andre");
		enviarLinhaComando("entrar jose");

		Thread.sleep(40);

		entradaJogo.iniciarJogo();
		Thread.sleep(4000);
		enviarLinhaComando("comprar andre 1 mario");
		enviarLinhaComando("comprar jose 2 prince");
		enviarLinhaComando("comprar jose 3 mario");

	}

	private static void enviarLinhaComando(String linhaComando) throws IOException {
		new ConectorDeSaidaDoCliente("localhost", 8080, System.out).enviarLinhaComando(linhaComando);
	}

}
