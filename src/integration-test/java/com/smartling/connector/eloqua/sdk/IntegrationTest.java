package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.EmailEloquaClient;
import com.smartling.connector.eloqua.sdk.client.EmailFolderEloquaClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import com.smartling.connector.eloqua.sdk.rest.model.EmailFolder;
import com.smartling.connector.eloqua.sdk.rest.model.HtmlContent;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assume.assumeNotNull;

public class IntegrationTest
{
    public static final String POSTFIX = "(test)";
    public static final String HTML = "<h4>Test</h4>";
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

        assertThatThrownBy(() -> client.listEmails(1, 10, "", ""))
                .isInstanceOf(EloquaAuthenticationException.class);
    }

    @Test
    public void shouldThrowApiExceptionIfApiCallFailed() throws Exception
    {
        EmailEloquaClient client = new EmailEloquaClient(configuration);

        // 0 is invalid parameter so emulating an API error
        assertThatThrownBy(() -> client.listEmails(0, 10, "", ""))
                .isExactlyInstanceOf(EloquaClientException.class);
    }

    @Test
    public void shouldWorkWithEmailsCorrectly()
    {
        EmailEloquaClient emailEloquaClient = new EmailEloquaClient(configuration);

        Elements<Email> emails = emailEloquaClient.listEmails(1, 10, "createdAt", "DESC");

        assertThat(emails).isNotNull();
        assertThat(emails.page).isEqualTo(1);
        assertThat(emails.pageSize).isEqualTo(10);
        assertThat(emails.total).isGreaterThan(0);
        assertThat(emails.elements).isNotEmpty();
        assertThat(emails.elements.get(0)).isNotNull();

        if(emails.total > 1)
        {
            assertThat(emails.elements.get(0).getUpdatedAt() > emails.elements.get(1).getUpdatedAt());
        }

        Email email = emailEloquaClient.getEmail(emails.elements.get(0).getId());

        assertThat(email).isNotNull();
        assertThat(email.getHtmlContent()).isNotNull();
        assertThat(email.getHtmlContent().getPlainHtml()).isNotNull();
        if((email.getHtmlContent().getType().equals(HtmlContent.RAW_HTML_CONTENT)))
        {
            email.getHtmlContent().setHtml(HTML);
        }else{
            email.getHtmlContent().setHtmlBody(HTML);
        }
        final String oldTitle = email.getName() + POSTFIX;
        emailEloquaClient.createOrUpdateEmail(oldTitle, email);

        Elements<Email> newEmails = emailEloquaClient.searchForEmail(oldTitle);
        assertThat(newEmails).isNotNull();
        assertThat(newEmails.elements).isNotEmpty();
        assertThat(newEmails.total).isEqualTo(1);

        Email testEmail = newEmails.elements.get(0);

        assertThat(testEmail.getName()).isEqualTo(oldTitle);
        assertThat(testEmail.getHtmlContent().getPlainHtml()).isEqualTo(HTML);
        assertThat(testEmail.getHtmlContent().getType()).isEqualTo(HtmlContent.RAW_HTML_CONTENT);

        emailEloquaClient.deleteEmail(testEmail.getId());
    }

    @Test
    public void shouldListEmailFolders()
    {
        EmailFolderEloquaClient emailFolderEloquaClient = new EmailFolderEloquaClient(configuration);

        Elements<EmailFolder> folders = emailFolderEloquaClient.listEmailFolders(1, 10, "name", "");

        assertThat(folders).isNotNull();
        assertThat(folders.page).isEqualTo(1);
        assertThat(folders.pageSize).isEqualTo(10);
        assertThat(folders.total).isGreaterThan(0);
        assertThat(folders.elements).isNotEmpty();
        assertThat(folders.elements.get(0)).isNotNull();

        EmailFolder folder = emailFolderEloquaClient.getEmailFolder(folders.elements.get(0).getId());
        assertThat(folder).isNotNull();

    }
}
