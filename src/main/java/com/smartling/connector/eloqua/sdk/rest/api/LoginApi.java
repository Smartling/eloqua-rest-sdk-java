package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.login.AccountInfo;
import feign.RequestLine;

public interface LoginApi extends EloquaApi
{
    @RequestLine("GET /id")
    AccountInfo getAccountInfo();
}
