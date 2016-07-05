package com.smartling.connector.eloqua.sdk.rest;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import com.smartling.connector.eloqua.sdk.rest.model.login.AccountInfo;
import com.smartling.connector.eloqua.sdk.rest.model.login.Urls;
import feign.FeignException;
import feign.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static feign.FeignException.errorStatus;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EloquaClientTest
{
    @InjectMocks
    TestEloquaClient client;
    @Mock
    LoginApi loginApi;
    @Mock
    Configuration configuration;
    @Mock
    EmailApi emailApi;

    class TestEloquaClient extends EloquaClient<EmailApi, Email>
    {
        TestEloquaClient(final Configuration configuration)
        {
            super(configuration);
        }

        @Override public EmailApi getApi()
        {
            return emailApi;
        }

        public Elements<Email> listEmails()
        {
            return executeCallWithRetry(emailApi -> emailApi.listEmails(configuration.getLoginEncoded()));
        }
    }

    @Before
    public void setUp() throws Exception
    {
        client = new TestEloquaClient(configuration);
        MockitoAnnotations.initMocks(this);

        AccountInfo accountInfo = new AccountInfo();
        Urls urls = new Urls();
        urls.base = "dasd";
        accountInfo.urls = urls;

        when(configuration.getLoginEncoded()).thenReturn("");
        when(loginApi.getAccountInfo(anyString())).thenReturn(accountInfo);
        when(emailApi.listEmails(anyString())).thenThrow(errorStatus("EmailApi#listEmails(String)", Response.create(401, "", new HashMap<>(), new byte[1])));
    }

    @Test(expected = FeignException.class)
    public void testExecuteCallWithRetry() throws Exception
    {
        client.executeCallWithRetry(emailApi -> emailApi.listEmails("dafafdasdf"));
        verify(emailApi, times(2)).listEmails(anyString());
    }
}