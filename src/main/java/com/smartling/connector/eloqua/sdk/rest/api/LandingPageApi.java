package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.LandingPage;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface LandingPageApi extends EloquaApi
{
    @RequestLine ("GET /API/REST/1.0/assets/landingPages?depth={depth}&page={page}&count={count}&orderBy={orderBy}&search={search}")
    Elements<LandingPage> listLandingPages(@Param ("depth") Depth depth, @Param("page") int page, @Param("count")int count, @Param("orderBy")String orderBy, @Param("search") String search);

    @RequestLine("GET /API/REST/1.0/assets/landingPage/{id}?depth={depth}")
    LandingPage getLandingPage(@Param("depth") Depth depth, @Param("id") long id);

    @RequestLine("GET /API/REST/1.0/assets/landingPages?depth={depth}&search={search}")
    Elements<LandingPage> searchForLandingPage(@Param("depth") Depth depth, @Param("search") String search);

    @RequestLine("POST /API/REST/1.0/assets/landingPage")
    @Headers ("Content-Type: application/json")
    LandingPage createLandingPage(LandingPage clonedEmail);

    @RequestLine("PUT /API/REST/1.0/assets/landingPage/{id}")
    @Headers ("Content-Type: application/json")
    Void updateLandingPage(@Param("id") long id, LandingPage clonedEmail);

    @RequestLine("DELETE /API/REST/1.0/assets/landingPage/{id}")
    Void deleteLandingPage(@Param("id") long id);
}