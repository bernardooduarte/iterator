package com.bernardoduarte.iterator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IteratorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void deveRegistrarTaxasNoGerenciador() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();

		TaxaCambio taxa = gerenciador.registrarTaxa("USD", 5.17);

		assertNotNull(taxa);
		assertEquals("USD", taxa.getMoeda());
		assertEquals(1, gerenciador.listarTaxas().size());
	}

	@Test
	void deveContarTaxasPorMoedaComIterator() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		gerenciador.registrarTaxa("USD", 5.17);
		gerenciador.registrarTaxa("EUR", 5.62);
		gerenciador.registrarTaxa("USD", 5.21);

		assertEquals(2, CensoTaxaCambio.contarTaxasPorMoeda(gerenciador, "USD"));
	}

	@Test
	void deveExibirTaxasUsandoIterator() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		gerenciador.registrarTaxa("USD", 5.17);
		gerenciador.registrarTaxa("EUR", 5.62);

		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		PrintStream saidaOriginal = System.out;

		try {
			System.setOut(new PrintStream(saida, true));
			assertDoesNotThrow(() -> System.out.println(gerenciador.exibirTaxas()));
		} finally {
			System.setOut(saidaOriginal);
		}

		String painel = saida.toString();
		assertTrue(painel.contains("Moeda: USD | Taxa: US$ 5.17"));
		assertTrue(painel.contains("Moeda: EUR | Taxa: EUR 5.62"));
	}

	@Test
	void deveLancarExcecaoParaMoedaNaoSuportada() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();

		assertThrows(IllegalArgumentException.class, () -> gerenciador.registrarTaxa("JPY", 1.0));
	}
}
