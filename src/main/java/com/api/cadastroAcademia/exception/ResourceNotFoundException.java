package com.api.cadastroAcademia.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2550317678717585766L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
