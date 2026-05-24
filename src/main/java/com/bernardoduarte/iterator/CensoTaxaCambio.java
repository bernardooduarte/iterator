package com.bernardoduarte.iterator;

public class CensoTaxaCambio {

	public static int contarTaxasPorMoeda(GerenciadorTaxaCambio gerenciador, String moeda) {
		int quantidade = 0;
		for (TaxaCambio taxa : gerenciador) {
			if (taxa.getMoeda().equalsIgnoreCase(moeda)) {
				quantidade++;
			}
		}
		return quantidade;
	}
}
