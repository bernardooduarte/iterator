package com.bernardoduarte.observer;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorTaxaCambio implements SujeitoTaxaCambio {

    private final List<TaxaCambio> taxas = new ArrayList<>();
    private final List<ObservadorTaxaCambio> observadores = new ArrayList<>();

    public void adicionarTaxa(TaxaCambio taxaCambio) {
        taxas.add(taxaCambio);
        notificarObservadores(taxaCambio);
    }

    public void atualizarTaxa(String moeda, double novoValorEmReais) {
        for (TaxaCambio taxa : taxas) {
            if (taxa.getMoeda().equalsIgnoreCase(moeda)) {
                taxa.setValorEmReais(novoValorEmReais);
                notificarObservadores(taxa);
                return;
            }
        }

        System.out.println("Moeda nao encontrada no gerenciador: " + moeda);
    }

    @Override
    public void adicionarObservador(ObservadorTaxaCambio observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(ObservadorTaxaCambio observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(TaxaCambio taxaCambio) {
        for (ObservadorTaxaCambio observador : observadores) {
            observador.atualizar(taxaCambio);
        }
    }

    public void exibirPainel() {
        System.out.println("=== Gerenciador de Taxa de Cambio (Observer) ===");
        for (TaxaCambio taxa : taxas) {
            taxa.exibirInfo();
        }
        System.out.println("=============================================");
    }
}
