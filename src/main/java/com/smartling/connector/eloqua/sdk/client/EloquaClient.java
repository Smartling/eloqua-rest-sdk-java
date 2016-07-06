package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.LoginApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;

import java.util.function.Function;

public abstract class EloquaClient<T extends EloquaApi, E>
{
    public static final String STATUS_401 = "status 401";
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
        if (baseUrl == null)
        {
            init();
        }
        Elements<E> elements = null;
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

    protected void init()
    {
        baseUrl = loginApi.getAccountInfo(configuration.getLoginEncoded()).getBaseUrl();
    }

    protected abstract T getApi();

}
