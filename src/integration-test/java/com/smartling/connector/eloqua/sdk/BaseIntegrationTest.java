package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.rest.EmailEloquaClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BaseIntegrationTest
{
    @Test
    public void testCall()
    {
        Configuration configuration = new Configuration(System.getProperty("eloqua.siteName"), System.getProperty("eloqua.username"), System.getProperty("eloqua.password"));

        EmailEloquaClient emailEloquaClient = new EmailEloquaClient(configuration);
        Elements<Email> emails = emailEloquaClient.listEmails();
        assertEquals(2, emails.total.intValue());
    }
}
