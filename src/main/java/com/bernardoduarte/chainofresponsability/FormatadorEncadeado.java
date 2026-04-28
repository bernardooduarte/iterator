package com.bernardoduarte.chainofresponsability;

public abstract class FormatadorEncadeado extends FormatadorValor {

	protected final FormatadorValor formatadorInterno;

	protected FormatadorEncadeado(FormatadorValor formatadorInterno) {
		this.formatadorInterno = formatadorInterno;
	}
}