package com.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
public class GymPolicyException extends RuntimeException
{
    public GymPolicyException(String message)
    {
        super(message);
    }
}