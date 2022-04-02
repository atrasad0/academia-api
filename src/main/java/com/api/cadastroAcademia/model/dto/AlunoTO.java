package com.api.cadastroAcademia.model.dto;

import com.api.cadastroAcademia.model.Aluno;
import com.api.cadastroAcademia.model.Aula;
import com.api.cadastroAcademia.model.Plano;
import com.api.cadastroAcademia.model.Telefone;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AlunoTO  {
     String nome;
     String cpf;
     String sexo;

     LocalDate dataNascimento;
     LocalDate dataCadastro;
     LocalDate dataPagamento;

     List<Telefone> telefones;
     List<Aula> aulas;

     Plano plano;

     public AlunoTO(Aluno aluno) {
          this.nome = aluno.getNome();
          this.cpf = aluno.getCpf();
          this.sexo = aluno.getSexo();

          this.dataNascimento = aluno.getDataNascimento();
          this.dataCadastro = aluno.getDataCadastro();
          this.dataPagamento = aluno.getDataPagamento();

          this.telefones = aluno.getTelefones();
          this.aulas = aluno.getAulas();

          this.plano = aluno.getPlano();
     }
}
