package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.Contact;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import feign.Param;
import feign.RequestLine;

public interface ContactApi extends EloquaApi
{
    @RequestLine ("GET /API/REST/2.0/data/contacts?depth={depth}")
    Elements<Contact> listContacts(@Param ("depth")EloquaApi.Depth depth);
}
