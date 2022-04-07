package com.api.cadastroAcademia.util;

import org.apache.ibatis.session.RowBounds;

/**
 * Classe de suporte para a utilização de paginação nesta aplicação.
 */
public class PaginationHelper {

    /* A página a ser buscada quando não for explicitamente requisitada pelo usuário. */
    private static final int DEFAULT_PAGE = 0;
    /* O tamanho da página a ser buscada quando não for explicitamente requisitado pelo usuário. */
    private static final int DEFAULT_SIZE = 25;
    /* O tamanho máximo de itens por página. */
    private static final int MAX_SIZE = 100;

    /**
     * Obtém o {@link RowBounds} default do sistema.
     * @return Uma instância default do {@link RowBounds}
     */
    public static RowBounds getDefaultBounds() {
        return new RowBounds(DEFAULT_PAGE, DEFAULT_SIZE);
    }

    /**
     * Calcula a paginação a ser realizada.
     * @param offSet A pagina requisitada.
     * @param limit O tamanho de itens por página
     * @return Uma instância do {@link RowBounds} com os valores requisitados.
     */
    public static RowBounds calculateBounds(int offSet, int limit) {
        if (offSet < 1)
            offSet = 1;

        if (limit > MAX_SIZE)
            limit = MAX_SIZE;

        return new RowBounds((offSet - 1) * limit, limit);
    }

    /**
     * Conta o numero de páginas.
     * @param totalObjects O número total de objetos salvos.
     * @param pageSize O tamanho de itens por página.
     * @return O numero total de páginas.
     */
    public static int countNumberOfPages(int totalObjects, int pageSize) {
        return totalObjects / pageSize + (totalObjects % pageSize == 0 ? 0 : 1);
    }


}
