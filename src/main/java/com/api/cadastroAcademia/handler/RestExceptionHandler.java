package com.api.cadastroAcademia.handler;

import com.api.cadastroAcademia.exception.ApiException;
import com.api.cadastroAcademia.exception.ResourceNotFoundException;
import com.api.cadastroAcademia.exception.StandardExceptionDetails;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfeException) {
        val response = StandardExceptionDetails.builder()
                .when(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Erro, recurso n\u00E3o encontrado")
                .detail(rnfeException.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException apiException) {
        val response = StandardExceptionDetails.builder()
                .when(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Erro no servi\u00E7o da aplica\u00E7\u00E3o")
                .detail(apiException.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
