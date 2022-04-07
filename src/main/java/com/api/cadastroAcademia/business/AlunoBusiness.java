package com.api.cadastroAcademia.business;

import com.api.cadastroAcademia.model.Aluno;
import com.api.cadastroAcademia.model.Paged;
import com.api.cadastroAcademia.model.Pagination;
import com.api.cadastroAcademia.model.dto.aluno.AlunoTO;
import lombok.NonNull;
import org.apache.ibatis.session.RowBounds;

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
     * @param pagination A página pedida nesta requisição. Este atributo pode ser {@code null},
     *                   indicando assim que o sistema deve utilizar uma paginação padrão.
     * @return O {@link Aluno}s no formato {@link Paged}.
     */
    Paged<AlunoTO> buscaAlunos(Pagination pagination);

    /**
     * Remove um aluno do banco de dados.
     * @param id O id de identificação do aluno.
     */
    void removeAluno(@NonNull Integer id);
}
