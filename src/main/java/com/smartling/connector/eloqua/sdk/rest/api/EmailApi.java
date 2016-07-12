package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import feign.RequestLine;

public interface EmailApi extends EloquaApi
{
    @RequestLine("GET /API/REST/1.0/assets/emails?depth=complete")
    Elements<Email> listEmails();
}
