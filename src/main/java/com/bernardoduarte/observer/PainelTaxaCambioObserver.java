package com.bernardoduarte.observer;

public class PainelTaxaCambioObserver implements ObservadorTaxaCambio {

    @Override
    public void atualizar(TaxaCambio taxaCambio) {
        System.out.println("[PAINEL] Taxa atualizada -> ");
        taxaCambio.exibirInfo();
    }
}
