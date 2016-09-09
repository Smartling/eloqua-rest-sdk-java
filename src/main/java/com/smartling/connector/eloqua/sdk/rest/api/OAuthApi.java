package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.login.GrantCode;
import com.smartling.connector.eloqua.sdk.rest.model.login.RefreshToken;
import com.smartling.connector.eloqua.sdk.rest.model.login.TokenInfo;
import feign.Headers;
import feign.RequestLine;

public interface OAuthApi
{
    @RequestLine ("POST /auth/oauth2/token")
    @Headers ("Content-Type: application/json")
    TokenInfo getTokenUsingGrantCode(GrantCode grantCode);

    @RequestLine("POST /auth/oauth2/token")
    @Headers ("Content-Type: application/json")
    TokenInfo getTokenUsingRefreshToken(RefreshToken refreshToken);
}
