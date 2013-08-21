package com.github.awvalenti.corridapatrimonial.servidor.logicajogo.main;

import java.util.List;

import com.github.awvalenti.corridapatrimonial.servidor.fabricajogo.FabricaJogoModel;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.InterfaceEntradaJogo;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.interfaces.OuvinteOfertas;
import com.github.awvalenti.corridapatrimonial.servidor.logicajogo.modelodedados.Oferta;

public class Main implements OuvinteOfertas {

	public static void main(String[] args) {
		new Main(FabricaJogoModel.criarJogoModel());
	}

	private InterfaceEntradaJogo interfaceEntradaJogo;

	public Main(InterfaceEntradaJogo interfaceEntradaJogo) {
		this.interfaceEntradaJogo = interfaceEntradaJogo;
		interfaceEntradaJogo.adicionarOuvinteOfertas(this);
		interfaceEntradaJogo.iniciarJogo();
	}

	@Override
	public void aoPublicarOfertas(List<Oferta> ofertas) {
		interfaceEntradaJogo.solicitarCompra("j1", ofertas.get(0).getId());
		interfaceEntradaJogo.solicitarCompra("j2", ofertas.get(0).getId());
	}

}
