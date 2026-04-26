package com.bernardoduarte.observer;

public class Euro extends TaxaCambio {

    public Euro(double valorEmReais, FormatadorValor formatador) {
        super("EUR", valorEmReais, formatador);
    }
}

