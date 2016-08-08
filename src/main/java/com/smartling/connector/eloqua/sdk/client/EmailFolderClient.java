package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.EmailFolderApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.EmailFolder;
import org.apache.commons.lang3.StringUtils;

public class EmailFolderClient extends EloquaClient<EmailFolderApi>
{
    public EmailFolderClient(Configuration configuration)
    {
        super(configuration, EmailFolderApi.class);
    }

    public Elements<EmailFolder> listEmailFolders(final int page, final int count, String sortBy, String orderBy, String search)
    {
        String order = StringUtils.isEmpty(sortBy) ? "" : sortBy + ' ' + orderBy;
        return executeCall(emailApi -> emailApi.listEmailFolders(EloquaApi.Depth.MINIMAL, page, count, order, search));
    }

    public EmailFolder getEmailFolder(final long id)
    {
        return executeCall(emailApi -> emailApi.getEmailFolder(EloquaApi.Depth.COMPLETE, id));
    }

   
}
