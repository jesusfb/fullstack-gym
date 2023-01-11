package com.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{
    private String resourceName = "";
    private String fieldName = "";
    private int fieldValue = 0;

    public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue)
    {
        super(resourceName + " with " + fieldName  + " = " + fieldValue + " does not exist");
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String message)
    {
        super(message);

    }

    public String getResourceName()
    {
        return resourceName;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public int getFieldValue()
    {
        return fieldValue;
    }
}