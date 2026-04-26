package com.bernardoduarte.observer;

public abstract class FormatadorObserver extends FormatadorValor {

    protected final FormatadorValor formatadorInterno;

    protected FormatadorObserver(FormatadorValor formatadorInterno) {
        this.formatadorInterno = formatadorInterno;
    }
}
