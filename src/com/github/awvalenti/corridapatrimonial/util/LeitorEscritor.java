package com.github.awvalenti.corridapatrimonial.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class LeitorEscritor {

	public static String lerLinha(InputStream inputStream) throws IOException {
		return new BufferedReader(new InputStreamReader(inputStream)).readLine();
	}

	public static String lerLinhaConsole() throws IOException {
		return lerLinha(System.in);
	}

	public static void escreverLinha(OutputStream outputStream, String linha) {
		new PrintWriter(outputStream, true).println(linha);
	}

}
