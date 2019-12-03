package dev.vagnergrossklaus.simplespringrestcrud.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Recurso não encontrado");
    }

    public ResourceNotFoundException(String message) {
        super("Recurso não encontrado: " + message);
    }

}
