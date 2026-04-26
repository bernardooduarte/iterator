package com.bernardoduarte.observer;

public class FormatadorLibraEsterlina extends FormatadorObserver {

    public FormatadorLibraEsterlina(FormatadorValor formatadorInterno) {
        super(formatadorInterno);
    }

    @Override
    public String formatar(double valor) {
        return "GBP " + formatadorInterno.formatar(valor);
    }
}

