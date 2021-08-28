package com.api.cadastroAcademia.business;

import com.api.cadastroAcademia.model.Plano;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface PlanoBusiness {

    /**
     * Busca todos os planos disponíveis na academia
     * @return Os {@link Plano}s da academia, {@code Optional.empty()} se não encontrado.
     */
    Optional<List<Plano>> buscaPlanos();
}
