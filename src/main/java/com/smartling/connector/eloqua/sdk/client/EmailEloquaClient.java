package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EmailApi;
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
    protected EmailApi getApi()
    {
        return Feign.builder()
                    .decoder(new GsonDecoder())
                    .options(configuration.getOptions())
                    .target(EmailApi.class, baseUrl);
    }
}
