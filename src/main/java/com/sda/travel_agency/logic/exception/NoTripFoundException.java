package com.sda.travel_agency.logic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoTripFoundException extends Exception {
    public NoTripFoundException(String message) {
        super(message);
    }
}
