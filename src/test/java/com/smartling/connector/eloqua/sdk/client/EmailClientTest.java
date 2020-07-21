package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.EmailApi;
import com.smartling.connector.eloqua.sdk.rest.api.LoginApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import com.smartling.connector.eloqua.sdk.rest.model.login.AccountInfo;
import com.smartling.connector.eloqua.sdk.rest.model.login.Urls;
import org.apache.http.client.HttpClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmailClientTest
{
    private EmailClient emailClient;
    private EmailApi emailApi = mock(EmailApi.class);
    private LoginApi loginApi;

    @Before
    public void setup()
    {
        loginApi = mock(LoginApi.class);
        emailClient = spy(new EmailClient(mock(Configuration.class), EmailApi.class, loginApi, mock(HttpClient.class)));
        AccountInfo accountInfo = new AccountInfo();
        final Urls urls = new Urls();
        urls.setBase("http://smth.com");
        accountInfo.setUrls(urls);
        when(loginApi.getAccountInfo()).thenReturn(accountInfo);

        doReturn(emailApi).when(emailClient).getApi();
    }

    @Test
    public void shouldCallGetEmailsOnce()
    {
        final Elements<Email> elements = new Elements<>();
        elements.setTotal(0);
        elements.setElements(new ArrayList<>());

        when(emailApi.listEmails(eq(EloquaApi.Depth.MINIMAL), eq(1), eq(1000), anyString(), anyString())).thenReturn(elements);

        emailClient.listEmails(1, 5000, "", "", "");

        verify(emailApi).listEmails(eq(EloquaApi.Depth.MINIMAL), eq(1), eq(1000), anyString(), anyString());
        verify(emailApi, times(0)).listEmails(eq(EloquaApi.Depth.MINIMAL), eq(2), eq(1000), anyString(), anyString());
    }

    @Test
    public void shouldCallGetEmails5times()
    {
        final Elements<Email> elements = new Elements<>();
        elements.setTotal(4010);
        ArrayList<Email> emails = new ArrayList<>();
        for (int i=0; i<1000; i++)
        {
            emails.add(new Email());
        }
        elements.setElements(emails);

        final Elements<Email> elements2 = new Elements<>();
        elements2.setTotal(4010);
        ArrayList<Email> emails2 = new ArrayList<>();
        for (int i=0; i<1000; i++)
        {
            emails2.add(new Email());
        }
        elements2.setElements(emails2);

        final Elements<Email> elements3 = new Elements<>();
        elements3.setTotal(4010);
        ArrayList<Email> emails3 = new ArrayList<>();
        for (int i=0; i<1000; i++)
        {
            emails3.add(new Email());
        }
        elements3.setElements(emails3);

        final Elements<Email> elements4 = new Elements<>();
        elements4.setTotal(4010);
        ArrayList<Email> emails4= new ArrayList<>();
        for (int i=0; i<1000; i++)
        {
            emails4.add(new Email());
        }
        elements4.setElements(emails4);

        final Elements<Email> lastElements = new Elements<>();
        lastElements.setTotal(4010);
        ArrayList<Email> lastEmails = new ArrayList<>();
        for (int i=0; i<10; i++)
        {
            lastEmails.add(new Email());
        }
        lastElements.setElements(lastEmails);

        when(emailApi.listEmails(eq(EloquaApi.Depth.MINIMAL), eq(1), eq(1000), anyString(), anyString())).thenReturn(elements);
        when(emailApi.listEmails(eq(EloquaApi.Depth.MINIMAL), eq(2), eq(1000), anyString(), anyString())).thenReturn(elements2);
        when(emailApi.listEmails(eq(EloquaApi.Depth.MINIMAL), eq(3), eq(1000), anyString(), anyString())).thenReturn(elements3);
        when(emailApi.listEmails(eq(EloquaApi.Depth.MINIMAL), eq(4), eq(1000), anyString(), anyString())).thenReturn(elements4);
        when(emailApi.listEmails(eq(EloquaApi.Depth.MINIMAL), eq(5), eq(1000), anyString(), anyString())).thenReturn(lastElements);

        final Elements<Email> listEmails = emailClient.listEmails(1, 4010, "", "", "");

        verify(emailApi).listEmails(eq(EloquaApi.Depth.MINIMAL), eq(1), eq(1000), anyString(), anyString());
        verify(emailApi).listEmails(eq(EloquaApi.Depth.MINIMAL), eq(2), eq(1000), anyString(), anyString());
        verify(emailApi).listEmails(eq(EloquaApi.Depth.MINIMAL), eq(3), eq(1000), anyString(), anyString());
        verify(emailApi).listEmails(eq(EloquaApi.Depth.MINIMAL), eq(4), eq(1000), anyString(), anyString());
        verify(emailApi).listEmails(eq(EloquaApi.Depth.MINIMAL), eq(5), eq(1000), anyString(), anyString());

        assertThat(listEmails.getElements().size()).isEqualTo(4010);
    }

    @Test
    public void shouldCallListEmailsByFolderId()
    {
        emailClient.listEmailsByFolder(12, 2, 10, "sort", "order", "search");

        verify(emailApi).listEmailsByFolder(12, EloquaApi.Depth.MINIMAL, 2, 10, "sort order", "[name =] \"search\"");
    }

    @Test
    public void shouldCallListEmailsByFolderIdEmptySortSearch()
    {
        emailClient.listEmailsByFolder(12, 2, 10, "", "order", "");

        verify(emailApi).listEmailsByFolder(12, EloquaApi.Depth.MINIMAL, 2, 10, "", "");
    }
}