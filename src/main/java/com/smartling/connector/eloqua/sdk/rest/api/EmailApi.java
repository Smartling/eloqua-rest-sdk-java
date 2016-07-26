package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import com.smartling.connector.eloqua.sdk.rest.model.EmailDtoForCreation;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface EmailApi extends EloquaApi
{
    @RequestLine("GET /API/REST/1.0/assets/emails?depth={depth}&page={page}&count={count}")
    Elements<Email> listEmails(@Param("depth") Depth depth, @Param("page") int page, @Param("count")int count);

    @RequestLine("GET /API/REST/1.0/assets/email/{id}?depth={depth}")
    Email getEmail(@Param("depth") Depth depth, @Param("id") long id);

    @RequestLine("GET /API/REST/1.0/assets/emails?search={search}")
    Elements<Email> searchForEmail(@Param("search") String search);

    @RequestLine("POST /API/REST/1.0/assets/email")
    @Headers ("Content-Type: application/json")
    Void createEmail(EmailDtoForCreation clonedEmail);

    @RequestLine("PUT /API/REST/1.0/assets/email/{id}")
    @Headers ("Content-Type: application/json")
    Void updateEmail(@Param("id") long id, Email clonedEmail);

    @RequestLine("DELETE /API/REST/1.0/assets/email/{id}")
    Void deleteEmail(@Param("id") long id);

}
