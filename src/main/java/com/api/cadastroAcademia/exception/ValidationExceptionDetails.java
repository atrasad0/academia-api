package com.api.cadastroAcademia.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
/**
 * Classe que representa um padrão de mensagem de Exceptions de validação da aplicação.
 */
@SuperBuilder
@Getter
public class ValidationExceptionDetails extends StandardExceptionDetails {
     private final String field;
     private final String fieldMessage;

}