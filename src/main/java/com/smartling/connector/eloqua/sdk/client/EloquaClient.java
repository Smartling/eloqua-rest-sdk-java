package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.EloquaAuthenticationException;
import com.smartling.connector.eloqua.sdk.EloquaClientException;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.LoginApi;
import feign.Feign;
import feign.FeignException;
import feign.jackson.JacksonDecoder;

import java.util.function.Function;

public abstract class EloquaClient<T extends EloquaApi>
{
    private static final String LOGIN_URL = "https://login.eloqua.com/id";

    private final Configuration configuration;
    private final LoginApi loginApi;

    private String baseUrl;

    public EloquaClient(final Configuration configuration)
    {
        this.configuration = configuration;
        this.loginApi = buildLoginApi();
    }

    public EloquaClient(final Configuration configuration, final LoginApi loginApi)
    {
        this.configuration = configuration;
        this.loginApi = loginApi;
    }

    protected <R> R executeCall(Function<T, R> function)
    {
        if (baseUrl == null)
        {
            init();
        }

        try
        {
            return executeAndWrapExceptions(function);
        }
        catch (EloquaAuthenticationException e)
        {
            init();
            return executeCall(function);
        }
    }

    private <R> R executeAndWrapExceptions(final Function<T, R> function)
    {
        try
        {
            return function.apply(getApi());
        }
        catch (FeignException e)
        {
            throw new EloquaClientException("Failed to perform a call to Eloqua API", e);
        }
    }

    private void init()
    {
        try
        {
            baseUrl = loginApi.getAccountInfo().getBaseUrl();
        }
        catch (FeignException e)
        {
            throw new EloquaAuthenticationException("Failed determine base URL", e);
        }
    }

    protected abstract T getApi();

    protected T buildApi(Class<T> apiType)
    {
        return Feign.builder()
                    .requestInterceptor(configuration.getAuthenticationInterceptor())
                    .decoder(new JacksonDecoder())
                    .errorDecoder(new EloquaApiErrorDecoder())
                    .options(configuration.getOptions())
                    .target(apiType, baseUrl);
    }

    private LoginApi buildLoginApi()
    {
        return Feign.builder()
                    .requestInterceptor(configuration.getAuthenticationInterceptor())
                    .decoder(new JacksonDecoder())
                    .errorDecoder(new EloquaApiErrorDecoder())
                    .options(configuration.getOptions())
                    .target(LoginApi.class, LOGIN_URL);
    }
}
