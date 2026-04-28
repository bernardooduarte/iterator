package com.bernardoduarte.chainofresponsability;

public abstract class EncadeadorBaseTaxaCambio implements EncadeadorTaxaCambio {

	private EncadeadorTaxaCambio proximo;

	@Override
	public void definirProximo(EncadeadorTaxaCambio proximo) {
		this.proximo = proximo;
	}

	protected void encaminhar(TaxaCambio taxaCambio) {
		if (proximo != null) {
			proximo.processar(taxaCambio);
		}
	}
}