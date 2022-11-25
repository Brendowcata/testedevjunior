package com.nexti.teste.support;

public class ErrorMessage {


    private final String field;
    private final String message;

    public ErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }

}
