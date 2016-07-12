package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EmailApi;
import com.smartling.connector.eloqua.sdk.rest.api.LoginApi;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.Email;
import com.smartling.connector.eloqua.sdk.rest.model.login.AccountInfo;
import com.smartling.connector.eloqua.sdk.rest.model.login.Urls;
import feign.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static feign.FeignException.errorStatus;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EloquaClientTest
{
    @Mock
    private LoginApi loginApi;
    @Mock
    private Configuration configuration;
    @Mock
    private EmailApi emailApi;

    @InjectMocks
    private TestEloquaClient testedInstance;

    private final static class TestEloquaClient extends EloquaClient<EmailApi, Email>
    {
        private final EmailApi emailApi;

        private TestEloquaClient(final Configuration configuration, final LoginApi loginApi, final EmailApi emailApi)
        {
            super(configuration, loginApi);
            this.emailApi = emailApi;
        }

        @Override
        public EmailApi getApi()
        {
            return emailApi;
        }
    }

    @Before
    public void setUp() throws Exception
    {
        Urls urls = new Urls();
        urls.setBase("dasd");
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUrls(urls);

        when(loginApi.getAccountInfo(any())).thenReturn(accountInfo);
    }

    @Test
    public void testExecuteCallWithRetry() throws Exception
    {
        when(emailApi.listEmails(anyString()))
                .thenThrow(errorStatus("EmailApi#listEmails(String)", unauthorizedResponse()))
                .thenReturn(new Elements<>());

        testedInstance.executeCallWithRetry(emailApi -> emailApi.listEmails("dafafdasdf"));

        verify(emailApi, times(2)).listEmails(anyString());
    }

    private static Response unauthorizedResponse()
    {
        return Response.create(401, "", new HashMap<>(), new byte[1]);
    }
}