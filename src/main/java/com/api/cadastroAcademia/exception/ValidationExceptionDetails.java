package com.api.cadastroAcademia.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ValidationExceptionDetails extends StandardExceptionDetails {
     private final String field;
     private final String fieldMessage;

}