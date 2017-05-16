package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.ContactField;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import feign.Param;
import feign.RequestLine;

public interface ContactFieldApi extends EloquaApi
{
    @RequestLine ("GET /API/REST/1.0/assets/contact/fields?depth={depth}&page={page}&count={count}&orderBy={orderBy}&search={search}")
    Elements<ContactField> listContactFields (@Param ("depth") Depth depth, @Param ("page") int page, @Param ("count") int count, @Param ("orderBy") String orderBy, @Param ("search") String search);
}
