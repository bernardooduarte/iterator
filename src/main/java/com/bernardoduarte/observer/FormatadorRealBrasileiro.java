package com.bernardoduarte.observer;

public class FormatadorRealBrasileiro extends FormatadorObserver {

    public FormatadorRealBrasileiro(FormatadorValor formatadorInterno) {
        super(formatadorInterno);
    }

    @Override
    public String formatar(double valor) {
        return "R$ " + formatadorInterno.formatar(valor);
    }
}
