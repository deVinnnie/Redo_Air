package com.realdolmen.air.service;

public class InvalidIdExeption extends Exception{

    public InvalidIdExeption() {
        super();
    }

    public InvalidIdExeption(String message) {
        super(message);
    }

    public InvalidIdExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIdExeption(Throwable cause) {
        super(cause);
    }
}
