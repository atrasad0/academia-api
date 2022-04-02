package com.api.cadastroAcademia.exception;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class StandardExceptionDetails {
    String title;
    String detail;
    int status;
    LocalDateTime when;

}
