package com.alura.alurabank.Controller.Form;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.googlecode.jmapper.annotations.JMap;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaCorrenteForm {

    @JsonProperty
    @JMap
    @NotBlank(message = "Banco é obrigatório e nao pode estar em branco")
    private String banco;

    @JMap
    @JsonProperty
    @NotBlank(message = "Agencia é obrigatória e nao pode estar em branco")
    private String agencia;

    @JMap
    @JsonProperty
    @NotBlank(message = "Numero da conta é obrigatório e nao pode estar em branco")
    private String numero;

}
