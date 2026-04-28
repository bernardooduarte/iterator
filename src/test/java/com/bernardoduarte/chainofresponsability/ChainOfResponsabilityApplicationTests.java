package com.bernardoduarte.chainofresponsability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChainOfResponsabilityApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void deveCriarTaxaComFormatadorDaFactory() {
		TaxaCambio taxa = new DolarAmericanoFactory().criarTaxa(5.17);

		assertNotNull(taxa);
		assertEquals("USD", taxa.getMoeda());
		assertEquals(5.17, taxa.getValorEmReais());
	}

	@Test
	void deveEncadearHandlersNaOrdemConfigurada() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		HandlerDeTeste primeiro = new HandlerDeTeste("primeiro");
		HandlerDeTeste segundo = new HandlerDeTeste("segundo");

		gerenciador.adicionarEncadeador(primeiro);
		gerenciador.adicionarEncadeador(segundo);
		gerenciador.adicionarTaxa(new DolarAmericanoFactory().criarTaxa(5.17));

		assertEquals(1, primeiro.quantidadeProcessamentos);
		assertEquals(1, segundo.quantidadeProcessamentos);
		assertEquals("USD", primeiro.ultimaMoedaRecebida);
		assertEquals("USD", segundo.ultimaMoedaRecebida);
	}

	@Test
	void deveRemoverHandlerDaCadeia() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		HandlerDeTeste primeiro = new HandlerDeTeste("primeiro");
		HandlerDeTeste segundo = new HandlerDeTeste("segundo");

		gerenciador.adicionarEncadeador(primeiro);
		gerenciador.adicionarEncadeador(segundo);
		gerenciador.removerEncadeador(segundo);
		gerenciador.adicionarTaxa(new EuroFactory().criarTaxa(5.62));

		assertEquals(1, primeiro.quantidadeProcessamentos);
		assertEquals(0, segundo.quantidadeProcessamentos);
	}

	@Test
	void deveAtualizarTaxaExistenteEManterExibicao() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		HandlerDeTeste handler = new HandlerDeTeste("unico");
		TaxaCambio taxa = new LibraEsterlinaFactory().criarTaxa(6.55);
		gerenciador.adicionarEncadeador(handler);
		gerenciador.adicionarTaxa(taxa);

		gerenciador.atualizarTaxa("GBP", 6.90);

		assertEquals(2, handler.quantidadeProcessamentos);
		assertEquals(6.90, taxa.getValorEmReais());
	}

	@Test
	void deveInformarQuandoMoedaNaoExiste() {
		GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		PrintStream saidaOriginal = System.out;

		try {
			System.setOut(new PrintStream(saida));
			gerenciador.atualizarTaxa("JPY", 1.0);
		} finally {
			System.setOut(saidaOriginal);
		}

		assertEquals("Moeda nao encontrada no gerenciador: JPY", saida.toString().trim());
	}

	private static final class HandlerDeTeste extends EncadeadorBaseTaxaCambio {
		private int quantidadeProcessamentos;
		private String ultimaMoedaRecebida;

		private HandlerDeTeste(String nome) {
		}

		@Override
		public void processar(TaxaCambio taxaCambio) {
			quantidadeProcessamentos++;
			ultimaMoedaRecebida = taxaCambio.getMoeda();
			encaminhar(taxaCambio);
		}
	}

}