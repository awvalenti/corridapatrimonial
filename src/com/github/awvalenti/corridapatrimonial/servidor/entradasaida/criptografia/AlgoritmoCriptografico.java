package com.github.awvalenti.corridapatrimonial.servidor.entradasaida.criptografia;

public enum AlgoritmoCriptografico {
	SEM_CRIPTOGRAFIA {
		@Override
		public String cifrar(String textoClaro, int chave) {
			return textoClaro;
		}

		@Override
		public String decifrar(String textoCifrado, int chave) {
			return textoCifrado;
		}
	},

	CIFRA_DE_CESAR_ESTENDIDA {
		private static final int MODULO_ARITMETICA_CHAR = Character.MAX_VALUE + 1;

		@Override
		public String cifrar(String textoClaro, int chave) {
			StringBuilder textoCifrado = new StringBuilder();

			for (int i = 0; i < textoClaro.length(); ++i) {
				textoCifrado.append((char) ((textoClaro.charAt(i) + chave) % MODULO_ARITMETICA_CHAR));
			}

			return textoCifrado.toString();
		}

		@Override
		public String decifrar(String textoCifrado, int chave) {
			StringBuilder textoDecifrado = new StringBuilder();

			for (int i = 0; i < textoCifrado.length(); ++i) {
				textoDecifrado.append((char) ((textoCifrado.charAt(i) - chave + MODULO_ARITMETICA_CHAR) % MODULO_ARITMETICA_CHAR));
			}

			return textoDecifrado.toString();
		}
	},

	;

	public abstract String cifrar(String textoClaro, int chave);
	public abstract String decifrar(String textoCifrado, int chave);

}

