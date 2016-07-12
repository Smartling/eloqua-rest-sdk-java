package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EmailApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;

public class EmailEloquaClient extends EloquaClient<EmailApi, Email>
{
    public EmailEloquaClient(final Configuration configuration)
    {
        super(configuration);
    }

    public Elements<Email> listEmails()
    {
        return executeCallWithRetry(EmailApi::listEmails);
    }

    @Override
    protected EmailApi getApi()
    {
        return buildApi(EmailApi.class);
    }
}
