package io.hrnugr.sample.handler.exceptions;

public class ResourceNotExistException extends Exception {
    public ResourceNotExistException(String msg) {
        super(msg);
    }
}
