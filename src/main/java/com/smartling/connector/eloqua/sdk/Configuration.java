package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.rest.model.login.TokenInfo;
import feign.Request;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.apache.commons.lang3.Validate;

public class Configuration
{
    private final String siteName;
    private final String username;
    private final String password;
    private TokenInfo tokenInfo;

    private int connectTimeoutMillis = 10_000;
    private int readTimeoutMillis = 60_000;

    public Configuration(final String siteName, final String username, final String password, final TokenInfo tokenInfo)
    {
        this.tokenInfo = tokenInfo;
        this.siteName = Validate.notEmpty(siteName, "Site name can not be empty");
        this.username = Validate.notEmpty(username, "Username can not be empty");
        this.password = Validate.notEmpty(password, "Password can not be empty");
    }

    public Configuration(final String siteName, final String username, final String password)
    {
        this.tokenInfo = null;
        this.siteName = Validate.notEmpty(siteName, "Site name can not be empty");
        this.username = Validate.notEmpty(username, "Username can not be empty");
        this.password = Validate.notEmpty(password, "Password can not be empty");
    }

    public RequestInterceptor getAuthenticationInterceptor()
    {
        return new BasicAuthRequestInterceptor(siteName + '\\' + username , password);
    }

    public RequestInterceptor getOAuthInterceptor()
    {
        return new OAuthRequestInterceptor(tokenInfo);
    }

    public Request.Options getOptions()
    {
        return new Request.Options(connectTimeoutMillis, readTimeoutMillis);
    }

    public int getConnectTimeoutMillis()
    {
        return connectTimeoutMillis;
    }

    public void setConnectTimeoutMillis(final int connectTimeoutMillis)
    {
        this.connectTimeoutMillis = validateNotNegative(connectTimeoutMillis);
    }

    public int getReadTimeoutMillis()
    {
        return readTimeoutMillis;
    }

    public void setReadTimeoutMillis(final int readTimeoutMillis)
    {
        this.readTimeoutMillis = validateNotNegative(readTimeoutMillis);
    }

    public TokenInfo getTokenInfo()
    {
        return tokenInfo;
    }

    public void setTokenInfo(final TokenInfo tokenInfo)
    {
        this.tokenInfo = tokenInfo;
    }

    private static int validateNotNegative(int value)
    {
        Validate.inclusiveBetween(0, Integer.MAX_VALUE, value);
        return value;
    }
}
