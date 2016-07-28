package com.smartling.connector.eloqua.sdk.rest.api;

import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.EmailFolder;
import feign.Param;
import feign.RequestLine;

public interface EmailFolderApi extends EloquaApi
{
    @RequestLine("GET /API/REST/1.0/assets/email/folders?depth={depth}&page={page}&count={count}&orderBy={orderBy}&search={search}")
    Elements<EmailFolder> listEmailFolders(@Param("depth") Depth depth, @Param("page") int page, @Param("count") int count, @Param("orderBy") String orderBy, @Param("search") String search);

    @RequestLine("GET /API/REST/1.0/assets/email/folder/{id}?depth={depth}")
    EmailFolder getEmailFolder(@Param("depth") Depth depth, @Param("id") long id);

}
