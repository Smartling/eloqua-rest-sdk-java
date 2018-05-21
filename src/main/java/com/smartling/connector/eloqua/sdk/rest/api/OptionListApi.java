package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.OptionList;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface OptionListApi extends EloquaApi
{
    @RequestLine("GET /API/REST/2.0/assets/optionLists?depth={depth}&page={page}&count={count}&orderBy={orderBy}&search={search}")
    Elements<OptionList> listOptionLists(@Param("depth") Depth depth, @Param("page") int page, @Param("count") int count, @Param("orderBy") String orderBy, @Param("search") String search);

    @RequestLine("GET /API/REST/2.0/assets/optionList/{id}?depth={depth}")
    OptionList getOptionList(@Param("depth") Depth depth, @Param("id") long id);

    @RequestLine("GET /API/REST/2.0/assets/optionLists?depth={depth}&search={search}")
    Elements<OptionList> searchForOptionList(@Param("depth") Depth depth, @Param("search") String search);

    @RequestLine("POST /API/REST/2.0/assets/optionList")
    @Headers("Content-Type: application/json")
    OptionList createOptionList(OptionList optionList);

    @RequestLine("PUT /API/REST/2.0/assets/optionList/{id}")
    @Headers("Content-Type: application/json")
    Void updateOptionList(@Param("id") long id, OptionList optionList);

    @RequestLine("DELETE /API/REST/2.0/assets/optionList/{id}")
    Void deleteOptionList(@Param("id") long id);
}