package com.expleo.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExpensesNotFoundException extends RuntimeException {

    public ExpensesNotFoundException(String message) {
        super(message);
    }

}
