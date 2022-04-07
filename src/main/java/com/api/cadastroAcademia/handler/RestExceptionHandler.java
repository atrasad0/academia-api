package com.api.cadastroAcademia.handler;

import com.api.cadastroAcademia.exception.ApiException;
import com.api.cadastroAcademia.exception.ResourceNotFoundException;
import com.api.cadastroAcademia.exception.StandardExceptionDetails;
import com.api.cadastroAcademia.exception.ValidationExceptionDetails;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Classe que lida com a padronização de mensagens de Erro da aplicação para o usuário
 */
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException manvException) {
        val fields = manvException.getFieldErrors().stream().map(FieldError::getField).collect(Collectors.joining(","));
        val messages = manvException.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        val response = ValidationExceptionDetails.builder()
                .when(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Erro na valida\u00E7\u00E3o dos campos")
                .field(fields)
                .fieldMessage(messages)
                .detail(manvException.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentExceptio(IllegalArgumentException iaException) {

        val response = StandardExceptionDetails.builder()
                .when(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Erro nos par\u00E2metros da requisi\u00E7\u00E3o")
                .detail(iaException.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
