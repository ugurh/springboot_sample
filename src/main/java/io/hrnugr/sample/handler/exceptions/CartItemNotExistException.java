package io.hrnugr.sample.handler.exceptions;

public class CartItemNotExistException extends Exception {

    public CartItemNotExistException(String message) {
        super(message);
    }

}
