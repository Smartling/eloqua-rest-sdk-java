package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.ContactFieldApi;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.model.ContactField;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import org.apache.commons.lang3.StringUtils;

public class ContactFieldClient extends EloquaClient<ContactFieldApi>
{
    public ContactFieldClient(final Configuration configuration)
    {
        super(configuration, ContactFieldApi.class);
    }

    public Elements<ContactField> listContentFields(final int page, final int count, String sortBy, String order, String searchTerm)
    {
        final String orderBy = StringUtils.isEmpty(sortBy) ? "" : sortBy + ' ' + order;
        final String search = StringUtils.isEmpty(searchTerm) ? "" : "[name =] \"" + searchTerm + '"';
        return executeCall(contactFieldApi -> contactFieldApi.listContactFields(EloquaApi.Depth.MINIMAL, page, count, orderBy, search));
    }
}
