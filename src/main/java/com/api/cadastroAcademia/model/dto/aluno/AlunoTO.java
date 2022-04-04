package com.api.cadastroAcademia.model.dto.aluno;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoTO implements Serializable {

     private static final long serialVersionUID = -6028514491586385355L;

     private int id;

     @NotEmpty(message = "Campo Nome n\u00E3o pode estar vazio")
     @Size(min = 3, message = "O campo nome deve ter no m\u00EDnimo 3 caracteres")
     private String nome;

     @NotEmpty(message = "Campo CPF n\u00E3o pode estar vazio")
     @CPF(message="CPF inválido")
     private String cpf;

     private String sexo;

     private LocalDate dataNascimento;
     private @NotNull LocalDate dataCadastro;
     private @NotNull LocalDate dataPagamento;

     private List<TelefoneTO> telefones;
     private @NotNull List<AulaTO> aulas;

     private @NotNull PlanoTO plano;

}
