package com.github.awvalenti.corridapatrimonial.cliente.main;
import java.io.IOException;

import com.github.awvalenti.corridapatrimonial.cliente.Cliente;
import com.github.awvalenti.corridapatrimonial.cliente.EntradaSaidaCliente;
import com.github.awvalenti.corridapatrimonial.cliente.config.ConfigCliente;
import com.github.awvalenti.corridapatrimonial.util.LeitorEscritor;

public class MainCliente {

	public static void main(String[] args) throws IOException {
		ConfigCliente configCliente = ConfigCliente.INSTANCIA;

		String linhaComando = LeitorEscritor.lerLinhaConsole();

		new Cliente(configCliente.getProperty("enderecoServidor"), configCliente.getIntProperty("portaServidor"),
				new EntradaSaidaCliente(System.out)).enviarLinhaComando(linhaComando);
	}

}
