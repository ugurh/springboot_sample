package io.hrnugr.sample.handler.exceptions;

/**
 * @author harun ugur
 */
public class CartItemNotExistException extends Exception {

    public CartItemNotExistException(String message) {
        super(message);
    }

}
