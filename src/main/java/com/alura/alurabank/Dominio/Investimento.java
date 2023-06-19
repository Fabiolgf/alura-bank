package com.alura.alurabank.Dominio;

import com.alura.alurabank.Exceptions.InvestimentoSemNomeException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Getter
@Setter
@Repository
public class Investimento {

    private String nome;
    private BigDecimal valor;
    private String tipoConta;
    private BigDecimal valorRendeu;
    private double percentual;

    public BigDecimal render(BigDecimal valor, double percentual) {
        BigDecimal percen = new BigDecimal(percentual);
        BigDecimal result = valor.multiply(percen);
        BigDecimal valorRendeu = valor.add(result);
        this.percentual = percentual;
        return valorRendeu;
    }
}
