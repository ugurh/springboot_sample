package io.hrnugr.ecommerce.handler.exceptions;

/**
 * @author harun ugur
 */
public class AuthFailException extends Exception {
    private final String message;

    public AuthFailException(String message) {
        super(message);
        this.message = message;
    }

}
