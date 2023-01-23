package com.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
public class RegistryAlreadyExistsException extends RuntimeException
{
    public RegistryAlreadyExistsException(String message)
    {
        super(message);
    }
}
