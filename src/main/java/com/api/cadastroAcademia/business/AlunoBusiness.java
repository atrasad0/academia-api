package com.api.cadastroAcademia.business;

import com.api.cadastroAcademia.model.Aluno;
import com.api.cadastroAcademia.model.dto.aluno.AlunoTO;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface AlunoBusiness {

    /**
     * Salva um aluno no banco de dados, caso ja exista, o mesmo será modificado.
     * @param aluno O aluno a ser salvo.
     * @return O aluno salvo no banco de dados no formato {@link AlunoTO}.
     */
    AlunoTO salvaAluno(@NonNull AlunoTO aluno);

    /**
     * Tenta encontrar um aluno cadastrado no banco de dados.
     * @param id O id de identificação do aluno.
     * @return O {@link Aluno} se for encontrado ou {@code Optional.empty()} se não encontrado.
     */
    Optional<AlunoTO> buscaAluno(@NonNull Integer id);

    /**
     * Tenta buscar todos os alunos no banco de dados.
     * @return O {@link Aluno}s se forem encontrados.{@code Optional.empty()} se não encontrados.
     */
    List<AlunoTO> buscaAlunos();

    /**
     * Remove um aluno do banco de dados.
     * @param id O id de identificação do aluno.
     */
    void removeAluno(@NonNull Integer id);
}
