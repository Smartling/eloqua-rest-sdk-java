package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.login.AccountInfo;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface LoginApi extends EloquaApi
{
    @RequestLine ("GET")
    @Headers ("Authorization: {login_encoded}")
    AccountInfo getAccountInfo(@Param ("login_encoded") final String loginEncoded);
}
