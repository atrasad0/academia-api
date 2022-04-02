package com.api.cadastroAcademia.business;

import com.api.cadastroAcademia.model.Plano;

import java.util.List;
import java.util.Optional;

public interface PlanoBusiness {

    /**
     * Busca todos os planos disponíveis na academia
     * @return Os {@link Plano}s da academia, {@code Optional.empty()} se não encontrado.
     */
    List<Plano> buscaPlanos();
}
