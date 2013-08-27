package com.github.awvalenti.corridapatrimonial.servidor.entradasaida;

import java.net.InetSocketAddress;
import java.net.Socket;

public enum EstrategiaIdentificacaoCliente {

	POR_HOST_NAME {
		@Override
		public String identificar(Socket socketCliente) {
			return ((InetSocketAddress) socketCliente.getRemoteSocketAddress()).getHostName();
		}
	},

	;

	public abstract String identificar(Socket socketCliente);
}
