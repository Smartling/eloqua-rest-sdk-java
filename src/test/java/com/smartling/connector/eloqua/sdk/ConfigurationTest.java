package com.smartling.connector.eloqua.sdk;

import feign.RequestTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest
{
    private Configuration configuration;

    @Before
    public void setup()
    {
        configuration = new Configuration("siteName", "username", "password");
    }

    @Test(expected = NullPointerException.class)
    public void shouldValidateSiteName() throws Exception
    {
        new Configuration(null, "username", "password");
    }

    @Test(expected = NullPointerException.class)
    public void shouldValidateUsername() throws Exception
    {
        new Configuration("site", null, "password");
    }

    @Test(expected = NullPointerException.class)
    public void shouldValidatePassword() throws Exception
    {
        new Configuration("site", "username", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldValidateConnectionTimeout() throws Exception
    {
        configuration.setConnectTimeoutMillis(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldValidateReadTimeout() throws Exception
    {
        configuration.setReadTimeoutMillis(-1);
    }

    @Test
    public void authenticationInterceptorShouldAddBasicAuthHeader() throws Exception
    {
        RequestTemplate requestTemplate = new RequestTemplate();
        String expected = "Basic " + new String(Base64.getEncoder().encode("siteName\\username:password".getBytes()));

        configuration.getAuthenticationInterceptor().apply(requestTemplate);

        assertThat(requestTemplate.headers()).containsKeys("Authorization");
        assertThat(requestTemplate.headers().get("Authorization")).containsOnly(expected);
    }

    @Test
    public void testGetOptions() throws Exception
    {
        assertThat(configuration.getOptions().connectTimeoutMillis()).isEqualTo(configuration.getConnectTimeoutMillis());
        assertThat(configuration.getOptions().readTimeoutMillis()).isEqualTo(configuration.getReadTimeoutMillis());
    }
}