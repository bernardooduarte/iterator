package com.bernardoduarte.chainofresponsability;

public class Euro extends TaxaCambio {

	public Euro(double valorEmReais, FormatadorValor formatador) {
		super("EUR", valorEmReais, formatador);
	}
}