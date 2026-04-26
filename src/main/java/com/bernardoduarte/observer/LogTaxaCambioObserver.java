package com.bernardoduarte.observer;

public class LogTaxaCambioObserver implements ObservadorTaxaCambio {

    @Override
    public void atualizar(TaxaCambio taxaCambio) {
        System.out.println("[LOG] Notificacao recebida para moeda " + taxaCambio.getMoeda());
    }
}
