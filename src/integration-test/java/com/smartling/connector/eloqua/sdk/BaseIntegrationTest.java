package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.EmailEloquaClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assume.assumeNotNull;

public class BaseIntegrationTest
{
    private Configuration configuration;

    private String siteName;
    private String username;

    @Before
    public void setUp() throws Exception
    {
        siteName = System.getProperty("eloqua.siteName");
        username = System.getProperty("eloqua.username");
        final String password = System.getProperty("eloqua.password");

        assumeNotNull("Site name is not specified", siteName);
        assumeNotNull("Username is not specified", username);
        assumeNotNull("Password is not specified", password);

        this.configuration = new Configuration(siteName, username, password);
    }

    @Test
    public void shouldThrowAuthenticationExceptionIfPasswordIncorrect() throws Exception
    {
        EmailEloquaClient client = new EmailEloquaClient(new Configuration(siteName, username, "invalid"));

        assertThatThrownBy(() -> client.listEmails(1, 10))
                .isInstanceOf(EloquaAuthenticationException.class);
    }

    @Test
    public void shouldThrowApiExceptionIfApiCallFailed() throws Exception
    {
        EmailEloquaClient client = new EmailEloquaClient(configuration);

        // 0 is invalid parameter so emulating an API error
        assertThatThrownBy(() -> client.listEmails(0, 10))
                .isExactlyInstanceOf(EloquaClientException.class);
    }

    @Test
    public void shouldListEmails()
    {
        EmailEloquaClient emailEloquaClient = new EmailEloquaClient(configuration);

        Elements<Email> emails = emailEloquaClient.listEmails(1, 10);

        assertThat(emails).isNotNull();
        assertThat(emails.page).isEqualTo(1);
        assertThat(emails.pageSize).isEqualTo(10);
        assertThat(emails.total).isGreaterThan(0);
        assertThat(emails.elements).isNotEmpty();
        assertThat(emails.elements.get(0)).isNotNull();
    }
}
