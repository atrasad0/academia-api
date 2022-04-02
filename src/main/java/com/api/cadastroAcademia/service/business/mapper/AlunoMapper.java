package com.api.cadastroAcademia.service.business.mapper;

import com.api.cadastroAcademia.model.Aluno;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlunoMapper {
    void insere(Aluno aluno);

    void modifica(Aluno aluno);

    Aluno buscaAluno(@Param("idAluno") Integer idAluno);

    List<Aluno> buscaAlunos();

    void remove(@Param("idAluno") Integer idAluno);

    boolean isCpfDuplicado(@Param("cpf") String cpf);

    void testeConexao();

}
