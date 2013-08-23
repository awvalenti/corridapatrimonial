package com.github.awvalenti.corridapatrimonial;

import java.util.List;

import com.github.awvalenti.corridapatrimonial.servidor.entradasaida.comandos.ExecutorComandos;
import com.github.awvalenti.corridapatrimonial.servidor.fabricasconcretas.FabricaJogoModel;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.OuvinteOfertas;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Oferta;

public class MainTesteServidor implements OuvinteOfertas {

	public static void main(String[] args) {
		InterfaceEntradaJogo entradaJogo = FabricaJogoModel.criarJogoModel();
		entradaJogo.criarNovoJogador("j1");
		entradaJogo.criarNovoJogador("j2");
		entradaJogo.criarNovoJogador("j3");

		new MainTesteServidor(entradaJogo);
	}

	private ExecutorComandos executorComandos;

	public MainTesteServidor(InterfaceEntradaJogo interfaceEntradaJogo) {
		interfaceEntradaJogo.adicionarOuvinteOfertas(this);
		interfaceEntradaJogo.iniciarJogo();
		executorComandos = new ExecutorComandos(interfaceEntradaJogo);
	}

	@Override
	public void aoPublicarOfertas(List<Oferta> ofertas) {
		String idOferta0 = ofertas.get(0).getId();
		String idOferta1 = ofertas.get(1).getId();
		String idOferta2 = ofertas.get(2).getId();
		String idOferta3 = ofertas.get(3).getId();
		executorComandos.executarLinhaComando("comprar j2 " + idOferta1);
		executorComandos.executarLinhaComando("comprar j2 " + idOferta1);
		executorComandos.executarLinhaComando("comprar j2 " + idOferta1);
		executorComandos.executarLinhaComando("comprar j1 " + idOferta3);
		executorComandos.executarLinhaComando("comprar j2 " + idOferta3);
		executorComandos.executarLinhaComando("comprar j2 " + idOferta2);
	}

}
