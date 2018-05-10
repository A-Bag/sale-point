package com.sale.point.exception;

public class InvalidBarCodeException extends Exception {

    public InvalidBarCodeException() {
    }

    public InvalidBarCodeException(String message) {
        super(message);
    }
}
