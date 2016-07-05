package com.smartling.connector.eloqua.sdk.rest;

import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface EmailApi extends EloquaApi
{
    @RequestLine ("GET /API/REST/1.0/assets/emails?depth=complete")
    @Headers ("Authorization: {login_encoded}") Elements<Email>
    listEmails(@Param ("login_encoded") final String loginEncoded);
}
