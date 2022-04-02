package com.api.cadastroAcademia.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
public class StandardExceptionDetails {
    private final String title;
    private final String detail;
    private final int status;
    private final LocalDateTime when;

}
