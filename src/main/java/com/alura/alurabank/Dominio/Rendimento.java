package com.alura.alurabank.Dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rendimento {

    @JsonProperty
    private String nome;

    @JsonProperty
    private double valor;

    public Rendimento(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }
}
