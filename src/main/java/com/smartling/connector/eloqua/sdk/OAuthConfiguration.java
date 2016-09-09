package com.smartling.connector.eloqua.sdk;

import feign.Request;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;

public class OAuthConfiguration
{
    private final String clientId;
    private final String clientPassword;
    private int connectTimeoutMillis = 10_000;
    private int readTimeoutMillis = 60_000;

    public OAuthConfiguration(final String clientId, final String clientPassword)
    {
        this.clientId = clientId;
        this.clientPassword = clientPassword;
    }

    public RequestInterceptor getAuthenticationInterceptorForOAuthFlow()
    {
        return new BasicAuthRequestInterceptor(clientId ,clientPassword);
    }

    public Request.Options getOptions()
    {
        return new Request.Options(connectTimeoutMillis, readTimeoutMillis);
    }

    public void setConnectTimeoutMillis(final int connectTimeoutMillis)
    {
        this.connectTimeoutMillis = connectTimeoutMillis;
    }

    public void setReadTimeoutMillis(final int readTimeoutMillis)
    {
        this.readTimeoutMillis = readTimeoutMillis;
    }
}
