package com.bernardoduarte.chainofresponsability;

public class FormatadorLibraEsterlina extends FormatadorEncadeado {

	public FormatadorLibraEsterlina(FormatadorValor formatadorInterno) {
		super(formatadorInterno);
	}

	@Override
	public String formatar(double valor) {
		return "GBP " + formatadorInterno.formatar(valor);
	}
}