package com.exam.account.exception;

public class ServiceErrorException extends RuntimeException {

    public ServiceErrorException(String message) {
        super(message);
    }

}
