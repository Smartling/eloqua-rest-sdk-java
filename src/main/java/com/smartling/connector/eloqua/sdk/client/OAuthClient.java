package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.EloquaAuthenticationException;
import com.smartling.connector.eloqua.sdk.OAuthConfiguration;
import com.smartling.connector.eloqua.sdk.rest.api.OAuthApi;
import com.smartling.connector.eloqua.sdk.rest.model.login.GrantCode;
import com.smartling.connector.eloqua.sdk.rest.model.login.RefreshToken;
import com.smartling.connector.eloqua.sdk.rest.model.login.TokenInfo;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class OAuthClient
{
    private final OAuthApi oAuthApi;
    private final OAuthConfiguration configuration;

    public OAuthClient(final OAuthApi oAuthApi, final OAuthConfiguration configuration)
    {
        this.oAuthApi = oAuthApi;
        this.configuration = configuration;
    }

    public OAuthClient(final OAuthConfiguration configuration)
    {
        this.configuration = configuration;
        oAuthApi = buildOAuthApi(OAuthApi.class, EloquaClient.LOGIN_URL);
    }

    public TokenInfo getTokenUsingGrantCode(GrantCode grantCode) throws EloquaAuthenticationException
    {
        return oAuthApi.getTokenUsingGrantCode(grantCode);
    }

    public TokenInfo getTokenUsingRefreshToken(RefreshToken refreshToken) throws EloquaAuthenticationException
    {
        return oAuthApi.getTokenUsingRefreshToken(refreshToken);
    }

    private <A> A buildOAuthApi(final Class<A> apiClass, final String apiBaseUrl)
    {
        return Feign.builder()
                    .requestInterceptor(configuration.getAuthenticationInterceptorForOAuthFlow())
                    .encoder(new JacksonEncoder())
                    .decoder(new JacksonDecoder())
                    .errorDecoder(new EloquaApiErrorDecoder())
                    .options(configuration.getOptions())
                    .target(apiClass, apiBaseUrl);
    }
}
