package com.smartling.connector.eloqua.sdk;

import com.smartling.connector.eloqua.sdk.client.ContactClient;
import com.smartling.connector.eloqua.sdk.client.EmailClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import com.smartling.connector.eloqua.sdk.rest.model.HtmlContent;
import org.junit.Test;

import java.util.UUID;

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
    public void shouldThrowObjectNotFoundException() throws Exception
    {
        EmailClient client = new EmailClient(configuration);
        assertThatThrownBy(() -> client.getEmail(100500)).isInstanceOf(EloquaObjectNotFoundException.class);
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
        assertThat(emails.getPage()).isEqualTo(1);
        assertThat(emails.getPageSize()).isEqualTo(10);
        assertThat(emails.getTotal()).isGreaterThan(0);
        assertThat(emails.getElements()).isNotEmpty();
        assertThat(emails.getElements().get(0)).isNotNull();

        if(emails.getTotal() > 1)
        {
            assertThat(emails.getElements().get(0).getUpdatedAt().after(emails.getElements().get(1).getUpdatedAt()));
        }

        Email email = emailClient.getEmail(emails.getElements().get(0).getId());

        assertThat(email).isNotNull();
        assertThat(email.getHtmlContent()).isNotNull();
        assertThat(email.getHtmlContent().getPlainHtml()).isNotNull();

        //
        //      Create
        //

        if((email.getHtmlContent().getType().equals(HtmlContent.RAW_HTML_CONTENT)))
        {
            email.getHtmlContent().setHtml(HTML);
        }
        else
        {
            email.getHtmlContent().setHtmlBody(HTML);
        }
        email.setTracked(true);
        email.setPlainTextEditable(true);

        Email createdEmail = emailClient.createEmail(email);

        assertThat(createdEmail).isNotNull();
        assertThat(createdEmail.isTracked()).isTrue();
        assertThat(createdEmail.isPlainTextEditable()).isTrue();

        //
        //      Update
        //

        final String oldTitle = email.getName() + POSTFIX;
        createdEmail.setName(oldTitle);

        emailClient.updateEmail(createdEmail.getId(), createdEmail);

        Elements<Email> newEmails = emailClient.searchForEmail(oldTitle);
        assertThat(newEmails).isNotNull();
        assertThat(newEmails.getElements()).isNotEmpty();
        assertThat(newEmails.getTotal()).isEqualTo(1);

        Email testEmail = newEmails.getElements().get(0);

        assertThat(testEmail.getName()).isEqualTo(oldTitle);

        emailClient.deleteEmail(testEmail.getId());
    }

    @Test
    public void getPreviewHtml()
    {
        EmailClient emailClient = new EmailClient(configuration);
        ContactClient contactClient = new ContactClient(configuration);

        Elements<Email> emails = emailClient.listEmails(1, 10, "createdAt", "DESC", "");

        assertThat(emails.getPage()).isEqualTo(1);
        assertThat(emails.getElements().get(0).getUpdatedAt().after(emails.getElements().get(1).getUpdatedAt()));

        final String htmlPreview = emailClient.getHtmlPreviewLink(emails.getElements().get(0).getId(), contactClient.getContactIdForPreview());

        assertThat(htmlPreview).isNotEmpty();
    }

    @Test
    public void shouldCopyEmail()
    {
        EmailClient emailClient = new EmailClient(configuration);
        String newName = "cloned landingPage " + UUID.randomUUID();
        Email clonedEmail = emailClient.copyEmail(102, newName);

        assertThat(clonedEmail.getId()).isNotEqualTo(102);
        assertThat(clonedEmail.getName()).isEqualTo(newName);

        emailClient.deleteEmail(clonedEmail.getId());
    }
}
