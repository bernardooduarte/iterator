package com.bernardoduarte.iterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GerenciadorTaxaCambio implements Iterable<TaxaCambio> {

	private final List<TaxaCambio> taxas = new ArrayList<>();
	private final Map<String, TaxaCambioFactory> fabricas = new HashMap<>();

	public GerenciadorTaxaCambio() {
		fabricas.put("USD", new DolarAmericanoFactory());
		fabricas.put("EUR", new EuroFactory());
		fabricas.put("GBP", new LibraEsterlinaFactory());
	}

	public TaxaCambio registrarTaxa(String moeda, double valorEmReais) {
		TaxaCambio taxa = criarTaxa(moeda, valorEmReais);
		taxas.add(taxa);
		return taxa;
	}

	public List<TaxaCambio> listarTaxas() {
		return List.copyOf(taxas);
	}

	@Override
	public Iterator<TaxaCambio> iterator() {
		return taxas.iterator();
	}

	public String exibirTaxas() {
		StringBuilder saida = new StringBuilder();
		for (TaxaCambio taxa : this) {
			saida.append("Moeda: ")
				.append(taxa.getMoeda())
				.append(" | Taxa: ")
				.append(taxa.getValorFormatado())
				.append(System.lineSeparator());
		}
		return saida.toString().trim();
	}

	private TaxaCambio criarTaxa(String moeda, double valorEmReais) {
		TaxaCambioFactory fabrica = fabricas.get(moeda.toUpperCase());
		if (fabrica == null) {
			throw new IllegalArgumentException("Moeda nao suportada: " + moeda);
		}
		return fabrica.criarTaxa(valorEmReais);
	}
}
