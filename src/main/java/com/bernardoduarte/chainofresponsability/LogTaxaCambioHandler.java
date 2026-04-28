package com.bernardoduarte.chainofresponsability;

public class LogTaxaCambioHandler extends EncadeadorBaseTaxaCambio {

	@Override
	public void processar(TaxaCambio taxaCambio) {
		System.out.println("[LOG] Taxa recebida para moeda " + taxaCambio.getMoeda());
		encaminhar(taxaCambio);
	}
}