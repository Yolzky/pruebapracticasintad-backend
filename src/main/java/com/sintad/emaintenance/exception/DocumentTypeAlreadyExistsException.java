package com.sintad.emaintenance.exception;

public class DocumentTypeAlreadyExistsException extends RuntimeException {
    public DocumentTypeAlreadyExistsException(String message) {
        super(message);
    }
}
