package com.smartling.connector.eloqua.sdk.rest;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;

import java.util.function.Function;

public abstract class EloquaClient<T extends EloquaApi, E>
{
    protected Configuration configuration;
    private LoginApi loginApi;
    protected String baseUrl = null;
    private static final String LOGIN_URL = "https://login.eloqua.com/id";

    public EloquaClient(final Configuration configuration)
    {
        this.configuration = configuration;
        loginApi = Feign.builder().decoder(new GsonDecoder()).target(LoginApi.class, LOGIN_URL);
    }

    protected Elements<E> executeCallWithRetry(Function<T, Elements<E>> function)
    {
        Elements<E> elements = null;
        try
        {
            elements = function.apply(getApi());
        }
        catch (FeignException ex)
        {
            if (ex.getMessage().contains("status 401"))
            {
                init();
                elements = function.apply(getApi());
            }
        }
        return elements;
    }

    protected void init()
    {
        baseUrl = loginApi.getAccountInfo(configuration.getLoginEncoded()).getBaseUrl();
    }

    public abstract T getApi();

}
