package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.login.GrantCodeDTO;
import com.smartling.connector.eloqua.sdk.rest.model.login.RefreshTokenDTO;
import com.smartling.connector.eloqua.sdk.rest.model.login.TokenInfo;
import feign.Headers;
import feign.RequestLine;

public interface OAuthApi
{
    @RequestLine ("POST /auth/oauth2/token")
    @Headers ("Content-Type: application/json")
    TokenInfo getTokenUsingGrantCode(GrantCodeDTO grantCodeDTO);

    @RequestLine("POST /auth/oauth2/token")
    @Headers ("Content-Type: application/json")
    TokenInfo getTokenUsingRefreshToken(RefreshTokenDTO refreshTokenDTO);
}
