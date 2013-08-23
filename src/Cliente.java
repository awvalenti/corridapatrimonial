import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.awvalenti.corridapatrimonial.cliente.ConectorDeSaidaDoCliente;
import com.github.awvalenti.corridapatrimonial.cliente.config.ConfigCliente;

public class Cliente {

	public static void main(String[] args) throws IOException {
		ConfigCliente configCliente = ConfigCliente.INSTANCIA;

		String linhaComando = new BufferedReader(new InputStreamReader(System.in)).readLine();

		new ConectorDeSaidaDoCliente(
			configCliente.getProperty("enderecoServidor"),
			configCliente.getIntProperty("portaServidor"),
			System.out
		).enviarLinhaComando(linhaComando);
	}

}
