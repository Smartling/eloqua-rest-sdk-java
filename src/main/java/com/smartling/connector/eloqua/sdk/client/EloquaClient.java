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
    private static final String LOGIN_URL = "https://login.eloqua.com/id";
    private static final String STATUS_401 = "status 401";

    private LoginApi loginApi;
    private String baseUrl;

    protected Configuration configuration;

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

    private void init()
    {
        baseUrl = loginApi.getAccountInfo().getBaseUrl();
    }

    protected abstract T getApi();

    protected T buildApi(Class<T> apiType)
    {
        return Feign.builder()
                    .requestInterceptor(configuration.getAuthenticationInterceptor())
                    .decoder(new GsonDecoder())
                    .options(configuration.getOptions())
                    .target(apiType, baseUrl);
    }

    private LoginApi buildLoginApi()
    {
        return Feign.builder()
                    .requestInterceptor(configuration.getAuthenticationInterceptor())
                    .decoder(new GsonDecoder())
                    .target(LoginApi.class, LOGIN_URL);
    }
}
