package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.EmailClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import com.smartling.connector.eloqua.sdk.rest.model.HtmlContent;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmailIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void shouldThrowAuthenticationExceptionIfPasswordIncorrect() throws Exception
    {
        EmailClient client = new EmailClient(new Configuration(siteName, username, "invalid"));

        assertThatThrownBy(() -> client.listEmails(1, 10, "", "", ""))
                .isInstanceOf(EloquaAuthenticationException.class);
    }

    @Test
    public void shouldThrowApiExceptionIfApiCallFailed() throws Exception
    {
        EmailClient client = new EmailClient(configuration);

        // 0 is invalid parameter so emulating an API error
        assertThatThrownBy(() -> client.listEmails(0, 10, "", "", ""))
                .isExactlyInstanceOf(EloquaClientException.class);
    }

    @Test
    public void shouldWorkWithEmailsCorrectly()
    {
        EmailClient emailClient = new EmailClient(configuration);

        Elements<Email> emails = emailClient.listEmails(1, 10, "createdAt", "DESC", "");

        assertThat(emails).isNotNull();
        assertThat(emails.page).isEqualTo(1);
        assertThat(emails.pageSize).isEqualTo(10);
        assertThat(emails.total).isGreaterThan(0);
        assertThat(emails.elements).isNotEmpty();
        assertThat(emails.elements.get(0)).isNotNull();

        if(emails.total > 1)
        {
            assertThat(emails.elements.get(0).getUpdatedAt().after(emails.elements.get(1).getUpdatedAt()));
        }

        Email email = emailClient.getEmail(emails.elements.get(0).getId());

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

        Email createdEmail = emailClient.createEmail(email);
        createdEmail.setName(oldTitle);

        emailClient.updateEmail(createdEmail.getId(), createdEmail);

        Elements<Email> newEmails = emailClient.searchForEmail(oldTitle);
        assertThat(newEmails).isNotNull();
        assertThat(newEmails.elements).isNotEmpty();
        assertThat(newEmails.total).isEqualTo(1);

        Email testEmail = newEmails.elements.get(0);

        assertThat(testEmail.getName()).isEqualTo(oldTitle);

        emailClient.deleteEmail(testEmail.getId());
    }
}