package io.hrnugr.sample.exceptions;

public class CartItemNotExistException extends Exception {

    public CartItemNotExistException(String message) {
        super(message);
    }

}
