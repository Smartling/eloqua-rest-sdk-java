package com.smartling.connector.eloqua.sdk.client;

import com.smartling.connector.eloqua.sdk.Configuration;
import com.smartling.connector.eloqua.sdk.rest.api.EloquaApi;
import com.smartling.connector.eloqua.sdk.rest.api.LoginApi;
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
import static org.mockito.BDDMockito.given;
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
    private TestApi testApi;

    @InjectMocks
    private TestEloquaClient testedInstance;

    private final static class TestEloquaClient extends EloquaClient<TestApi>
    {
        private final TestApi api;

        private TestEloquaClient(final Configuration configuration, final LoginApi loginApi, final TestApi api)
        {
            super(configuration, loginApi);
            this.api = api;
        }

        @Override
        public TestApi getApi()
        {
            return api;
        }
    }

    private interface TestApi extends EloquaApi
    {
        Object test();
    }

    @Before
    public void setUp() throws Exception
    {
        Urls urls = new Urls();
        urls.setBase("http://some-endpoint.eloqua.com");
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUrls(urls);

        when(loginApi.getAccountInfo()).thenReturn(accountInfo);
    }

    @Test
    public void testExecuteCallWithRetry() throws Exception
    {
        given(testApi.test())
                  .willThrow(errorStatus("EmailApi#listEmails(String)", unauthorizedResponse()))
                  .willReturn(new Object());

        testedInstance.executeCall(TestApi::test);

        verify(testApi, times(2)).test();
    }

    private static Response unauthorizedResponse()
    {
        return Response.create(401, "", new HashMap<>(), new byte[1]);
    }
}