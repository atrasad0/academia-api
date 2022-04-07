package com.api.cadastroAcademia.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * Representa um conteúdo paginado da aplicação.
 */

@Builder
@Value
public class Paged<T> {
    List<T> content;

    /* Número total de páginas. */
    int totalPages;
    /* Número total de elementos. */
    int totalElements;
    /* Página atual. */
    int pageNumber;
    /* Tamnho da página. */
    int pageSize;
    /* Se esta é a ultima página. */
    boolean last;
}
