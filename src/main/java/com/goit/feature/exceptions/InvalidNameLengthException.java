package com.goit.feature.exceptions;

public class InvalidNameLengthException extends RuntimeException{
    public InvalidNameLengthException(String message) {
        super(message);
    }
}
