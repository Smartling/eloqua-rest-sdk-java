package com.smartling.connector.eloqua.sdk.client;

import com.google.common.base.Charsets;
import com.smartling.connector.eloqua.sdk.EloquaAuthenticationException;
import com.smartling.connector.eloqua.sdk.EloquaClientException;
import com.smartling.connector.eloqua.sdk.EloquaObjectNotFoundException;
import feign.Response;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class EloquaApiErrorDecoderTest
{
    private final EloquaApiErrorDecoder testedInstance = new EloquaApiErrorDecoder();

    @Test
    public void shouldReturnApiException() throws Exception
    {
        Response response = Response.create(400, "Validation error", Collections.emptyMap(), "content...", Charsets.UTF_8);

        Exception exception = testedInstance.decode("", response);

        assertThat(exception)
                .isExactlyInstanceOf(EloquaClientException.class)
                .hasMessage("Eloqua API responded with HTTP 400: Validation error. Details: content...");
    }

    @Test
    public void shouldReturnAuthenticationExceptionFor401() throws Exception
    {
        Response response = Response.create(401, "Unauthorized", Collections.emptyMap(), new byte[0]);

        Exception exception = testedInstance.decode("", response);

        assertThat(exception)
                .isExactlyInstanceOf(EloquaAuthenticationException.class)
                .hasMessage("Authentication failed with HTTP 401: Unauthorized. Details: null");
    }

    @Test
    public void shouldReturnAuthenticationExceptionFor400() throws Exception
    {
        Response response = Response.create(400, "Bad Request", Collections.emptyMap(), "{error: invalid_grant}", Charsets.UTF_8);

        Exception exception = testedInstance.decode("", response);

        assertThat(exception)
                .isExactlyInstanceOf(EloquaAuthenticationException.class)
                .hasMessage("Authentication failed with HTTP 400: Bad Request. Details: {error: invalid_grant}");
    }

    @Test
    public void shouldReturnEloquaObjectNotFoundExceptionFor404() throws Exception
    {
        Response response = Response.create(404, "Object not found", Collections.emptyMap(), "", Charsets.UTF_8);

        Exception exception = testedInstance.decode("", response);

        assertThat(exception)
                .isExactlyInstanceOf(EloquaObjectNotFoundException.class)
                .hasMessage("Object not found with HTTP 404: Object not found. Details: null");
    }
}