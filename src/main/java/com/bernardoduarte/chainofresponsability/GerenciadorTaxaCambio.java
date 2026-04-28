package com.bernardoduarte.chainofresponsability;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorTaxaCambio {

	private final List<TaxaCambio> taxas = new ArrayList<>();
	private final List<EncadeadorTaxaCambio> encadeadores = new ArrayList<>();

	public void adicionarTaxa(TaxaCambio taxaCambio) {
		taxas.add(taxaCambio);
		acionarCadeia(taxaCambio);
	}

	public void atualizarTaxa(String moeda, double novoValorEmReais) {
		for (TaxaCambio taxa : taxas) {
			if (taxa.getMoeda().equalsIgnoreCase(moeda)) {
				taxa.setValorEmReais(novoValorEmReais);
				acionarCadeia(taxa);
				return;
			}
		}

		System.out.println("Moeda nao encontrada no gerenciador: " + moeda);
	}

	public void adicionarEncadeador(EncadeadorTaxaCambio encadeador) {
		encadeadores.add(encadeador);
		reconstruirCadeia();
	}

	public void removerEncadeador(EncadeadorTaxaCambio encadeador) {
		encadeadores.remove(encadeador);
		reconstruirCadeia();
	}

	private void reconstruirCadeia() {
		for (int indice = 0; indice < encadeadores.size(); indice++) {
			EncadeadorTaxaCambio encadeadorAtual = encadeadores.get(indice);
			EncadeadorTaxaCambio proximoEncadeador = indice + 1 < encadeadores.size() ? encadeadores.get(indice + 1) : null;
			encadeadorAtual.definirProximo(proximoEncadeador);
		}
	}

	private void acionarCadeia(TaxaCambio taxaCambio) {
		if (!encadeadores.isEmpty()) {
			encadeadores.get(0).processar(taxaCambio);
		}
	}

	public void exibirPainel() {
		System.out.println("=== Gerenciador de Taxa de Cambio (Chain of Responsibility) ===");
		for (TaxaCambio taxa : taxas) {
			taxa.exibirInfo();
		}
		System.out.println("=============================================");
	}
}