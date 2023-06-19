package com.alura.alurabank.Controller.Form;

import com.alura.alurabank.Dominio.Correntista;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class CorrentistaForm {

    @JsonProperty
    @CPF
    @NotNull(message = "CPF é obrigatório")
    private String cpf;

    @JsonProperty
    @NotBlank(message = "Nome do correntista é obrigatório e não pode estar em branco")
    private String nome;

    public Correntista toCorrestista() {
        return new Correntista(cpf, nome);
    }
}
