package com.corecs.javase.exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException{
    public InvalidRoomsCountException() {
    }

    public InvalidRoomsCountException(String s) {
        super(s);
    }
}
