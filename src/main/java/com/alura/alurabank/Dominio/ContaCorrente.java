package com.alura.alurabank.Dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.googlecode.jmapper.annotations.JMap;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@EqualsAndHashCode(
        of = {"banco", "agencia", "numero"}
)
public class ContaCorrente {

    @Getter
    @Setter
    @JMap
    @JsonProperty
    private String banco;

    @Getter
    @Setter
    @JMap
    @JsonProperty
    private String agencia;

    @Getter
    @Setter
    @JMap
    @JsonProperty
    private String numero;

    @JsonProperty
    private BigDecimal saldo;

    @JsonProperty
    private Correntista correntista;

    public ContaCorrente (String banco, String agencia, String numero, Correntista correntista){
        // aqui esta chamando o construtor sem argumentos para obter o saldo zerado
        this();
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
        this.correntista = correntista;
    }

    public ContaCorrente(String banco, String agencia, String numero) {
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
    }

    public BigDecimal lerSaldo() {
        return saldo;
    }

    public ContaCorrente() {
        this.saldo = BigDecimal.ZERO;
    }

    public int obterNumeroConta() {
        return Integer.parseInt(numero);
    }

    public boolean identificadaPor(String banco, String agencia, String numero){
        return this.banco.equals(banco)
                && this.agencia.equals(agencia)
                && this.numero.equals(numero);
    }

    public void executar(Operacao operacao, BigDecimal valor){
        saldo = operacao.executar(saldo, valor);
    }

}
