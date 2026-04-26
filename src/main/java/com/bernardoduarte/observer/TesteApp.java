package com.bernardoduarte.observer;

public class TesteApp {

    public static void main(String[] args) {
        GerenciadorTaxaCambio gerenciador = new GerenciadorTaxaCambio();
        PainelTaxaCambioObserver painelObserver = new PainelTaxaCambioObserver();
        LogTaxaCambioObserver logObserver = new LogTaxaCambioObserver();

        gerenciador.adicionarObservador(painelObserver);
        gerenciador.adicionarObservador(logObserver);

        TaxaCambioFactory dolarFactory = new DolarAmericanoFactory();
        TaxaCambioFactory euroFactory = new EuroFactory();
        TaxaCambioFactory libraFactory = new LibraEsterlinaFactory();

        TaxaCambio dolar = dolarFactory.criarTaxa(5.17);
        TaxaCambio euro = euroFactory.criarTaxa(5.62);
        TaxaCambio libra = libraFactory.criarTaxa(6.55);

        gerenciador.adicionarTaxa(dolar);
        gerenciador.adicionarTaxa(euro);
        gerenciador.adicionarTaxa(libra);

        gerenciador.exibirPainel();

        System.out.println("\nAtualizando taxa em tempo de execucao e notificando observers...");
        gerenciador.atualizarTaxa("USD", 5.35);
        dolar.setFormatador(new FormatadorRealBrasileiro(new FormatadorDolarAmericano(new FormatadorBase())));
        gerenciador.exibirPainel();
    }

}

