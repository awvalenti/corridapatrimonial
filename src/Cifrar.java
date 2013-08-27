import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia.AlgoritmoCriptografico;


public class Cifrar {

	public static void main(String[] args) throws IOException {
		int chave = Integer.parseInt(args[0]);

		String textoClaro = new BufferedReader(new InputStreamReader(System.in)).readLine();

		System.out.println(AlgoritmoCriptografico.CIFRA_DE_CESAR_ESTENDIDA.cifrar(textoClaro, chave));
	}

}
