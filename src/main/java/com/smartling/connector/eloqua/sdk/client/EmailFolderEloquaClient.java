package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.EmailFolderApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.EmailFolder;

public class EmailFolderEloquaClient extends EloquaClient<EmailFolderApi>
{
    public EmailFolderEloquaClient(Configuration configuration)
    {
        super(configuration, EmailFolderApi.class);
    }

    public Elements<EmailFolder> listEmailFolders(final int page, final int count, String orderBy, String search)
    {
        return executeCall(emailApi -> emailApi.listEmailFolders(EloquaApi.Depth.MINIMAL, page, count, orderBy, search));
    }

    public EmailFolder getEmailFolder(final long id)
    {
        return executeCall(emailApi -> emailApi.getEmailFolder(EloquaApi.Depth.COMPLETE, id));
    }

   
}
