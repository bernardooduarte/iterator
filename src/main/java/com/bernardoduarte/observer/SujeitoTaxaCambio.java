package com.bernardoduarte.observer;

public interface SujeitoTaxaCambio {
    void adicionarObservador(ObservadorTaxaCambio observador);

    void removerObservador(ObservadorTaxaCambio observador);

    void notificarObservadores(TaxaCambio taxaCambio);
}
