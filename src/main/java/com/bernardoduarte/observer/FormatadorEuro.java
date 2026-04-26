package com.bernardoduarte.observer;

public class FormatadorEuro extends FormatadorObserver {

    public FormatadorEuro(FormatadorValor formatadorInterno) {
        super(formatadorInterno);
    }

    @Override
    public String formatar(double valor) {
        return "EUR " + formatadorInterno.formatar(valor);
    }
}

