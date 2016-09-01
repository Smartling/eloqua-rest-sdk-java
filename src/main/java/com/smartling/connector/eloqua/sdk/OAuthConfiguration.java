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

    public static final String CLIENT_ID = "b48e55f2-5a5d-4b0c-994b-78fdb6ca6124";
    public static final String CLIENT_PASSWORD = "1IhWMMeyhakiSKmoxb0XeDgNYETKhcXMh~qH3~L9WBE8xEfvFFguAMzwL3f57j-zKwP4qqi4OX0KjXjTlwAky-6j4f-zmf1Avupr";

    public OAuthConfiguration(final String clientId, final String clientPassword)
    {
        this.clientId = clientId;
        this.clientPassword = clientPassword;
    }

    public OAuthConfiguration()
    {
        this.clientId = CLIENT_ID;
        this.clientPassword = CLIENT_PASSWORD;
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
