package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.rest.model.login.TokenInfo;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class OAuthRequestInterceptor implements RequestInterceptor
{
    private final String headerValue;

    public OAuthRequestInterceptor (TokenInfo tokenInfo)
    {
        headerValue = tokenInfo.getTokenType() + ' ' + tokenInfo.getAccessToken();
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", headerValue);
    }
}
