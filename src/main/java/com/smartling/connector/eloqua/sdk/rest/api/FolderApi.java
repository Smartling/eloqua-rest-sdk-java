package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Folder;
import feign.Param;
import feign.RequestLine;

public interface FolderApi extends EloquaApi
{
    @RequestLine("GET /API/REST/1.0/assets/{assetType}/folders?depth={depth}&page={page}&count={count}&orderBy={orderBy}&search={search}")
    Elements<Folder> listFolders(@Param("assetType") String assetType, @Param ("depth") Depth depth, @Param ("page") int page, @Param ("count") int count, @Param ("orderBy") String orderBy, @Param ("search") String search);

    @RequestLine("GET /API/REST/1.0/assets/{assetType}/folder/{id}?depth={depth}")
    Folder getFolder(@Param ("assetType") String assetType, @Param ("depth") Depth depth, @Param ("id") long id);

}
