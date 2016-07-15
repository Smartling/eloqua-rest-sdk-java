package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.EloquaAuthenticationException;
import com.smartling.connector.eloqua.sdk.EloquaClientException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class EloquaApiErrorDecoder implements ErrorDecoder
{
    @Override
    public Exception decode(final String methodKey, final Response response)
    {
        String responseBody = bodyAsString(response);

        if (response.status() == 401)
        {
            String message = String.format("Authentication failed with HTTP %s: %s. Details: %s", response.status(), response.reason(), responseBody);
            return new EloquaAuthenticationException(message);
        }
        else
        {
            String message = String.format("Eloqua API responded with HTTP %s: %s. Details: %s", response.status(), response.reason(), responseBody);
            return new EloquaClientException(message);
        }
    }

    private static String bodyAsString(final Response response)
    {
        if (response.body().length() > 0)
        {
            try (BufferedReader reader = new BufferedReader(response.body().asReader()))
            {
                return reader.lines().collect(Collectors.joining());
            }
            catch (IOException ignored)
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
}
