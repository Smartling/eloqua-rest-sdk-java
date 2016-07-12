package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.EmailEloquaClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeNotNull;

public class BaseIntegrationTest
{
    private Configuration configuration;

    @Before
    public void setUp() throws Exception {
        String siteName = System.getProperty("eloqua.siteName");
        String username = System.getProperty("eloqua.username");
        String password = System.getProperty("eloqua.password");

        assumeNotNull("Site name is not specified", siteName);
        assumeNotNull("Username is not specified", username);
        assumeNotNull("Password is not specified", password);

        this.configuration = new Configuration(siteName, username, password);
    }

    @Test
    public void testCall()
    {
        EmailEloquaClient emailEloquaClient = new EmailEloquaClient(configuration);

        Elements<Email> emails = emailEloquaClient.listEmails();

        assertThat(emails).isNotNull();
        assertThat(emails.total).isEqualTo(2);
    }
}
