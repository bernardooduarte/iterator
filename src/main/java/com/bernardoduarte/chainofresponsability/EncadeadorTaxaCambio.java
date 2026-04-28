package com.bernardoduarte.chainofresponsability;

public interface EncadeadorTaxaCambio {
	void definirProximo(EncadeadorTaxaCambio proximo);

	void processar(TaxaCambio taxaCambio);
}