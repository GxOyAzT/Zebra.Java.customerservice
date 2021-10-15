package com.customerservice.domain.exceptions.bussineslogic;

public class InvalidModelException extends Exception {

    public InvalidModelException(String errorMessage) {
        super(errorMessage);
    }
}
