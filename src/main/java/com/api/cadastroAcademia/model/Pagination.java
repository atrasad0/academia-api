package com.api.cadastroAcademia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * Classe que representa a requisição de uma página na aplicação.
 */

@Getter
@Value
@AllArgsConstructor
public class Pagination {
    /* A página "pedida" nesta requisição. */
    int page;
    /*O tamanho da página. */
    int size;

}
