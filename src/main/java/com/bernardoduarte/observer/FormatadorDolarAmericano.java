package com.bernardoduarte.observer;

public class FormatadorDolarAmericano extends FormatadorObserver {

    public FormatadorDolarAmericano(FormatadorValor formatadorInterno) {
        super(formatadorInterno);
    }

    @Override
    public String formatar(double valor) {
        return "US$ " + formatadorInterno.formatar(valor);
    }
}

