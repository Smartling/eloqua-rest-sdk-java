package com.smartling.connector.eloqua.sdk;

public class EloquaClientException extends RuntimeException
{
    public EloquaClientException(final String message)
    {
        super(message);
    }

    public EloquaClientException(final String message, final Exception cause)
    {
        super(message, cause);
    }
}
