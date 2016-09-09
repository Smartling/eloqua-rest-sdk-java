package com.smartling.connector.eloqua.sdk.rest.model.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GrantCode
{
    @JsonProperty ("grant_type")
    private final String grantType = "authorization_code";
    private final String code;
    @JsonProperty ("redirect_uri")
    private final String redirectUri;

    public GrantCode(final String code, final String redirectUri)
    {
        this.code = code;
        this.redirectUri = redirectUri;
    }

    public String getGrantType()
    {
        return grantType;
    }

    public String getCode()
    {
        return code;
    }

    public String getRedirectUri()
    {
        return redirectUri;
    }
}
