package com.bernardoduarte.chainofresponsability;

public class PainelTaxaCambioHandler extends EncadeadorBaseTaxaCambio {

	@Override
	public void processar(TaxaCambio taxaCambio) {
		System.out.println("[PAINEL] Taxa atualizada -> ");
		taxaCambio.exibirInfo();
		encaminhar(taxaCambio);
	}
}