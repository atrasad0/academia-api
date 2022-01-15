package com.api.cadastroAcademia.model.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Esta classe contêm informações de status da requisição atual.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RequestStatus {
    /** Um possível id gerado nesta requisição. */
    private int id;
    /** A exception que esta requisição possa ter gerado.*/
    private Exception error;
    /** Se esta é uma "nova" entidade.*/
    private boolean newEntity;
    /*Codigo de Stuatus da requisicao*/
    private HttpStatus statusCod;


    public RequestStatus(int id) {
        this.id = id;
    }

    /**
     * Se o objeto requisitado foi salvo com sucesso.
     * @return {@code true} se o objeto foi criado.
     */
    public boolean isCreated() {
        if (!this.newEntity)
            return false;
        return this.id != 0 && this.error == null;
    }

    /**
     * Se esta requisição causou alguma {@link Exception}
     * @return {@code true} se alguma Exception foi gerada.
     */
    public boolean hasError() {
        return this.error != null;
    }

    /**
     * Retorna uma mensagem de erro gerada de uma Exception.
     * @return A mensagem de erro.
     */
    public String getMessageError() {
        if (!hasError())
            return "Message error not found.";
        return error.getMessage();

    }


}
