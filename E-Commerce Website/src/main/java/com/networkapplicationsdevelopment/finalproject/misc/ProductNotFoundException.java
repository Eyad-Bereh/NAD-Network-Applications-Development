package com.networkapplicationsdevelopment.finalproject.misc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The requested product was not found")
public class ProductNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 8922443159889495859L;
    
}