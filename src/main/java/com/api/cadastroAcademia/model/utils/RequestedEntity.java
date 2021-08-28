package com.api.cadastroAcademia.model.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Esta classe possui objetos que podem ser transportados pelo sistema junto com o status da requisição.
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder (builderMethodName = "RequestedEntity")
@Data
public class RequestedEntity extends RequestStatus {
    /** O objeto requisitado.*/
    private Object entity;

    /**
     * Verifica se o objeto atual carregado nesta classe é uma {@link List}.
     *
     * @return {@code true} se for uma List.
     */
    public boolean isList() {
        if (this.entity == null)
            return false;
        return this.entity instanceof List;
  }
}
