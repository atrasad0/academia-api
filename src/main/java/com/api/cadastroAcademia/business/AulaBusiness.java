package com.api.cadastroAcademia.business;

import com.api.cadastroAcademia.model.Aula;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AulaBusiness {

    /**
     * Busca todos as aulas disponíveis na academia
     * @return As {@link Aula}s da academia, {@code Optional.empty()} se não encontrado.
     */
    Optional<List<Aula>> buscaAulas();
}
