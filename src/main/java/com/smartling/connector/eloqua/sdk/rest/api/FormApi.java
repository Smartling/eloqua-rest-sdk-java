package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.CopyRequest;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Form;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface FormApi extends EloquaApi {
    @RequestLine("GET /API/REST/2.0/assets/forms?depth={depth}&page={page}&count={count}&orderBy={orderBy}&search={search}")
    Elements<Form> listForms(@Param("depth") Depth depth, @Param("page") int page, @Param("count") int count, @Param("orderBy") String orderBy, @Param("search") String search);

    @RequestLine("GET /API/REST/2.0/assets/form/{id}?depth={depth}")
    Form getForm(@Param("depth") Depth depth, @Param("id") long id);

    @RequestLine("GET /API/REST/2.0/assets/forms?depth={depth}&search={search}")
    Elements<Form> searchForForm(@Param("depth") Depth depth, @Param("search") String search);

    @RequestLine("POST /API/REST/2.0/assets/form")
    @Headers("Content-Type: application/json")
    Form createForm(Form form);

    @RequestLine("PUT /API/REST/2.0/assets/form/{id}")
    @Headers("Content-Type: application/json")
    Void updateForm(@Param("id") long id, Form form);

    @RequestLine("POST /API/REST/2.0/assets/form/{id}/copy")
    @Headers ("Content-Type: application/json")
    Form copyForm(@Param("id") long id, CopyRequest copyRequest);

    @RequestLine("DELETE /API/REST/2.0/assets/form/{id}")
    Void deleteForm(@Param("id") long id);
}
