package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.LoginApi;
import feign.Feign;
import feign.FeignException;
import feign.jackson.JacksonDecoder;

import java.util.function.Function;

public abstract class EloquaClient<T extends EloquaApi>
{
    private static final String LOGIN_URL = "https://login.eloqua.com/id";
    private static final String STATUS_401 = "status 401";

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

        R elements = null;
        try
        {
            elements = function.apply(getApi());
        }
        catch (FeignException ex)
        {
            if (ex.getMessage().contains(STATUS_401))
            {
                init();
                elements = function.apply(getApi());
            }
        }
        return elements;
    }

    private void init()
    {
        baseUrl = loginApi.getAccountInfo().getBaseUrl();
    }

    protected abstract T getApi();

    protected T buildApi(Class<T> apiType)
    {
        return Feign.builder()
                    .requestInterceptor(configuration.getAuthenticationInterceptor())
                    .decoder(new JacksonDecoder())
                    .options(configuration.getOptions())
                    .target(apiType, baseUrl);
    }

    private LoginApi buildLoginApi()
    {
        return Feign.builder()
                    .requestInterceptor(configuration.getAuthenticationInterceptor())
                    .decoder(new JacksonDecoder())
                    .options(configuration.getOptions())
                    .target(LoginApi.class, LOGIN_URL);
    }
}
