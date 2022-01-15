package com.api.cadastroAcademia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Aluno implements Serializable {

    private static final long serialVersionUID = 8839707290978152965L;

    private Integer id;
    private @NotNull String nome;
    private @NotNull String cpf;
    private String sexo;
    private LocalDate dataNascimento;
    private LocalDate dataCadastro;
    private @NotNull LocalDate dataPagamento;
    private List<Telefone> telefones;
    private @NotNull List<Aula> aulas;
    private @NotNull Plano plano;

    public Aluno(Integer id) {
        this.id = id;
    }


    public Long getIdade() {
        if (this.dataNascimento == null)
            return null;

        return this.dataNascimento.until(LocalDate.now(), ChronoUnit.YEARS);
    }

}
