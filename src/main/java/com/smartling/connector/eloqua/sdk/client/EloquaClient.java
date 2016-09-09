package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.EloquaAuthenticationException;
import com.smartling.connector.eloqua.sdk.EloquaClientException;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.LoginApi;
import feign.Feign;
import feign.FeignException;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public abstract class EloquaClient<T extends EloquaApi>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EloquaClient.class);

    public static final String LOGIN_URL = "https://login.eloqua.com";

    private final Configuration configuration;
    private final Class<T>      apiType;
    private final LoginApi loginApi;

    private String baseUrl;

    protected EloquaClient(final Configuration configuration, Class<T> apiType)
    {
        this.configuration = configuration;
        this.apiType = apiType;
        loginApi = configuration.getTokenInfo() == null ? buildApi(LoginApi.class, LOGIN_URL) : buildApiWithOAuthAuthentication(LoginApi.class, LOGIN_URL);
    }

    public EloquaClient(final Configuration configuration, final Class<T> apiType, final LoginApi loginApi)
    {
        this.configuration = configuration;
        this.apiType = apiType;
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
            LOGGER.debug("Got authentication exception so trying to determine base URL once again:", e);

            init();
            return executeAndWrapExceptions(function);
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

    protected T getApi()
    {
        return configuration.getTokenInfo() == null ? buildApi(apiType, baseUrl) : buildApiWithOAuthAuthentication(apiType, baseUrl);
    }

    private <A> A buildApi(final Class<A> apiClass, final String apiBaseUrl)
    {
        return Feign.builder()
                    .requestInterceptor(configuration.getAuthenticationInterceptor())
                    .encoder(new JacksonEncoder())
                    .decoder(new JacksonDecoder())
                    .errorDecoder(new EloquaApiErrorDecoder())
                    .options(configuration.getOptions())
                    .target(apiClass, apiBaseUrl);
    }

    private <A> A buildApiWithOAuthAuthentication(final Class<A> apiClass, final String apiBaseUrl)
    {
        return Feign.builder()
                    .requestInterceptor(configuration.getOAuthInterceptor())
                    .encoder(new JacksonEncoder())
                    .decoder(new JacksonDecoder())
                    .errorDecoder(new EloquaApiErrorDecoder())
                    .options(configuration.getOptions())
                    .target(apiClass, apiBaseUrl);
    }

}
