package com.smartling.connector.eloqua.sdk.rest;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import feign.Feign;
import feign.gson.GsonDecoder;

public class EmailEloquaClient extends EloquaClient<EmailApi, Email>
{
    public EmailEloquaClient(final Configuration configuration)
    {
        super(configuration);
    }

    public Elements<Email> listEmails()
    {
        return executeCallWithRetry(emailApi -> emailApi.listEmails(configuration.getLoginEncoded()));
    }

    @Override
    public EmailApi getApi()
    {
        if(baseUrl == null)
        {
            init();
        }
        return Feign.builder().decoder(new GsonDecoder()).target(EmailApi.class, baseUrl);
    }
}
