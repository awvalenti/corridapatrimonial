package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia;

public enum AlgoritmoCriptografico {
	SEM_CRIPTOGRAFIA {
		@Override
		public String decifrar(String textoClaro, int chave) {
			return textoClaro;
		}

		@Override
		public String cifrar(String textoClaro, int chave) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	CIFRA_DE_CESAR {
		@Override
		public String cifrar(String textoClaro, int chave) {
			StringBuilder textoCifrado = new StringBuilder();

			for (int i = 0; i < textoClaro.length(); ++i) {
				char caractereCifrado = textoClaro.charAt(i);

				char caractereFinal;

				if (!Character.isLetter(caractereCifrado)) {
					caractereFinal = caractereCifrado;

				} else {
					char extremo = Character.isUpperCase(caractereCifrado) ? 'Z' : 'z';
					int caractereDecifrado = caractereCifrado + chave;
					if (caractereDecifrado > extremo) caractereDecifrado -= 26;
					caractereFinal = (char) caractereDecifrado;
				}

				textoCifrado.append(caractereFinal);
			}

			return textoCifrado.toString();
		}

		@Override
		public String decifrar(String textoCifrado, int chave) {
			StringBuilder textoDecifrado = new StringBuilder();

			for (int i = 0; i < textoCifrado.length(); ++i) {
				char caractereCifrado = textoCifrado.charAt(i);

				char caractereFinal;

				if (!Character.isLetter(caractereCifrado)) {
					caractereFinal = caractereCifrado;

				} else {
					char extremo = Character.isUpperCase(caractereCifrado) ? 'A' : 'a';
					int caractereDecifrado = caractereCifrado - chave;
					if (caractereDecifrado < extremo) caractereDecifrado += 26;
					caractereFinal = (char) caractereDecifrado;
				}

				textoDecifrado.append(caractereFinal);
			}

			return textoDecifrado.toString();
		}
	},
	;

	public abstract String cifrar(String textoClaro, int chave);
	public abstract String decifrar(String textoCifrado, int chave);

	public static void main(String[] args) {
		String cifrado = CIFRA_DE_CESAR.cifrar("aAzZ comprar 123 $%%", 2);
		System.out.println(cifrado);
		System.out.println(CIFRA_DE_CESAR.decifrar(cifrado, 2));
		System.out.println(-1 % 26);
	}

}

