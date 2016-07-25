package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.EmailApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;

public class EmailEloquaClient extends EloquaClient<EmailApi>
{
    public EmailEloquaClient(final Configuration configuration)
    {
        super(configuration, EmailApi.class);
    }

    public Elements<Email> listEmails(final int page, final int count)
    {
        return executeCall(emailApi -> emailApi.listEmails(EloquaApi.Depth.MINIMAL, page, count));
    }

    public Email getEmail(final long id)
    {
        return executeCall(emailApi -> emailApi.getEmail(EloquaApi.Depth.COMPLETE, id));
    }
}
