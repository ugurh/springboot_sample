package io.hrnugr.ecommerce.handler.exceptions;

/**
 * @author harun ugur
 */
public class CustomException extends Exception {

    private final String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }
}
