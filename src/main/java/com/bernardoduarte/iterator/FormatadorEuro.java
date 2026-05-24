package com.bernardoduarte.iterator;

public class FormatadorEuro extends FormatadorEncadeado {

	public FormatadorEuro(FormatadorValor formatadorInterno) {
		super(formatadorInterno);
	}

	@Override
	public String formatar(double valor) {
		return "EUR " + formatadorInterno.formatar(valor);
	}
}


