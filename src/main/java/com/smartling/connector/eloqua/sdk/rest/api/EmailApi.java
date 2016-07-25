package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import feign.Param;
import feign.RequestLine;

public interface EmailApi extends EloquaApi
{
    @RequestLine("GET /API/REST/1.0/assets/emails?depth={depth}&page={page}&count={count}")
    Elements<Email> listEmails(@Param("depth") Depth depth, @Param("page") int page, @Param("count")int count);

    @RequestLine("GET /API/REST/1.0/assets/email/{id}?depth={depth}")
    Email getEmail(@Param("depth") Depth depth, @Param("id") long id);

}
