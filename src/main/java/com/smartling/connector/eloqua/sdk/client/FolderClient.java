package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.FolderApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Folder;
import org.apache.commons.lang3.StringUtils;

public class FolderClient extends EloquaClient<FolderApi>
{
    public FolderClient(Configuration configuration)
    {
        super(configuration, FolderApi.class);
    }

    public Elements<Folder> listFolders(final String assetType, final int page, final int count, String sortBy, String orderBy, String search)
    {
        String order = StringUtils.isEmpty(sortBy) ? "" : sortBy + ' ' + orderBy;
        return executeCall(folderApi -> folderApi.listFolders(assetType, EloquaApi.Depth.MINIMAL, page, count, order, search));
    }

    public Folder getFolder(final String assetType, final long id)
    {
        return executeCall(folderApi -> folderApi.getFolder(assetType, EloquaApi.Depth.COMPLETE, id));
    }

   
}
