package com.api.cadastroAcademia.exception;

public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -2080010323356462361L;

    public ApiException(String message) {
        super(message);
    }
}
