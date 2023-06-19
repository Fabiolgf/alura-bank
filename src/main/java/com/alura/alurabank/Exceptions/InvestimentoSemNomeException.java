package com.alura.alurabank.Exceptions;

public class InvestimentoSemNomeException extends RuntimeException {
    public InvestimentoSemNomeException() {
        super("Investimento informado não existe");
    }
}
