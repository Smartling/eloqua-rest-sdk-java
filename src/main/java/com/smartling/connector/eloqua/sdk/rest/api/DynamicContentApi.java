package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.CopyRequest;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.dynamicContent.DynamicContent;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface DynamicContentApi extends EloquaApi
{
    @RequestLine ("GET /API/REST/2.0/assets/dynamicContents?depth={depth}&page={page}&count={count}&orderBy={orderBy}&search={search}")
    Elements<DynamicContent> listDynamicContent (@Param ("depth") Depth depth, @Param ("page") int page, @Param ("count") int count, @Param ("orderBy") String orderBy, @Param ("search") String search);

    @RequestLine("GET /API/REST/2.0/assets/dynamicContent/{id}?depth={depth}")
    DynamicContent getDynamicContent(@Param("depth") Depth depth, @Param("id") long id);

    @RequestLine("GET /API/REST/2.0/assets/dynamicContents?depth={depth}&search={search}")
    Elements<DynamicContent> searchForDynamicContent(@Param("depth") Depth depth, @Param("search") String search);

    @RequestLine("POST /API/REST/2.0/assets/dynamicContent")
    @Headers ("Content-Type: application/json")
    DynamicContent createDynamicContent(DynamicContent clonedDynamicContent);

    @RequestLine("PUT /API/REST/2.0/assets/dynamicContent/{id}")
    @Headers ("Content-Type: application/json")
    Void updateDynamicContent(@Param("id") long id, DynamicContent clonedDynamicContent);

    @RequestLine("POST /API/REST/2.0/assets/dynamicContent/{id}/copy")
    @Headers ("Content-Type: application/json")
    DynamicContent copyDynamicContent(@Param("id") long id, CopyRequest copyRequest);

    @RequestLine("DELETE /API/REST/2.0/assets/dynamicContent/{id}")
    Void deleteDynamicContent(@Param("id") long id);
}
