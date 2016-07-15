package com.smartling.connector.eloqua.sdk;

import feign.FeignException;

public class EloquaAuthenticationException extends EloquaClientException
{
    public EloquaAuthenticationException(final String message, final FeignException cause)
    {
        super(message, cause);
    }
}
