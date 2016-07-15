package com.smartling.connector.eloqua.sdk;

import feign.FeignException;

public class EloquaClientException extends RuntimeException
{
    public EloquaClientException(final String message)
    {
        super(message);
    }

    public EloquaClientException(final String message, final FeignException cause)
    {
        super(message, cause);
    }
}
