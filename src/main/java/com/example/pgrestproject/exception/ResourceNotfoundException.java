package com.example.pgrestproject.exception;

public class ResourceNotfoundException extends RuntimeException{

    private static final long serialVarsionUID = 1L;

    public ResourceNotfoundException(String message){
        super(message);
    }
}
