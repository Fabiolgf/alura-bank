package com.alura.alurabank.Dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Correntista {

    @JsonProperty
    private String cpf;
    @JsonProperty
    private String nome;

    private LocalDate dataDeEntrada = LocalDate.now();

    public Correntista(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }
}