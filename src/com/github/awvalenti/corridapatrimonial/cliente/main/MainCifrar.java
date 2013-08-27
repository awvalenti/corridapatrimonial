package com.github.awvalenti.corridapatrimonial.cliente.main;
import java.io.IOException;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;
import com.github.awvalenti.corridapatrimonial.util.LeitorEscritor;


public class MainCifrar {

	public static void main(String[] args) throws IOException {
		int chave = Integer.parseInt(args[0]);

		String textoClaro = LeitorEscritor.lerLinhaConsole();

		System.out.println(AlgoritmoCriptografico.CIFRA_DE_CESAR_ESTENDIDA.cifrar(textoClaro, chave));
	}

}
