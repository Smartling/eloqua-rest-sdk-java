package com.smartling.connector.eloqua.sdk.rest.model.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshTokenDTO
{
    @JsonProperty ("grant_type")
    private final String grantType = "refresh_token";
    @JsonProperty ("refresh_token")
    private final String refreshToken;
    @JsonProperty ("redirect_uri")
    private final String redirectUri;
    private final String scope = "full";

    public RefreshTokenDTO(final String refreshToken, final String redirectUri)
    {
        this.refreshToken = refreshToken;
        this.redirectUri = redirectUri;
    }

    public String getGrantType()
    {
        return grantType;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    public String getRedirectUri()
    {
        return redirectUri;
    }

    public String getScope()
    {
        return scope;
    }
}
