package com.github.awvalenti.corridapatrimonial.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.awvalenti.corridapatrimonial.cliente.config.ConfigCliente;

public class MainCliente {

	public static void main(String[] args) throws IOException {
		ConfigCliente configCliente = ConfigCliente.INSTANCIA;

		System.out.println("Comandos disponiveis:");
		System.out.println("\tentrar  nome-da-equipe");
		System.out.println("\tcomprar  nome-da-equipe  numero-da-oferta  codigo-do-cartao-de-credito");
		System.out.println();
		System.out.print("Digite um comando: ");

		String linhaComando = new BufferedReader(new InputStreamReader(System.in)).readLine();

		new ConectorDeSaidaDoCliente(
			configCliente.getProperty("enderecoServidor"),
			configCliente.getIntProperty("portaServidor"),
			System.out
		).enviarLinhaComando(linhaComando);
	}

}
