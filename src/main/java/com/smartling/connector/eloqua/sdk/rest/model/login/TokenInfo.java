package com.smartling.connector.eloqua.sdk.rest.model.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenInfo
{
    @JsonProperty ("access_token")
    private String accessToken;
    @JsonProperty ("refresh_token")
    private String refreshToken;
    @JsonProperty ("token_type")
    private String tokenType;
    @JsonProperty ("expires_in")
    private Integer expiresIn;

    public TokenInfo()
    {
    }

    public TokenInfo(final String refreshToken, final String tokenType)
    {
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(final String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    public void setRefreshToken(final String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    public String getTokenType()
    {
        return tokenType;
    }

    public void setTokenType(final String tokenType)
    {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn()
    {
        return expiresIn;
    }

    public void setExpiresIn(final Integer expiresIn)
    {
        this.expiresIn = expiresIn;
    }

    @Override public String toString()
    {
        return "TokenInfo{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
