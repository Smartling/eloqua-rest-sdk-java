package com.smartling.connector.eloqua.sdk;

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

    @Test
    public void testGetLoginEncoded() throws Exception
    {
        final String expected = "Basic " + new String(Base64.getEncoder().encode("siteName\\username:password".getBytes()));

        assertThat(configuration.getLoginEncoded()).isEqualTo(expected);
    }

    @Test
    public void testGetOptions() throws Exception
    {
        assertThat(configuration.getOptions().connectTimeoutMillis()).isEqualTo(configuration.getConnectTimeoutMillis());
        assertThat(configuration.getOptions().readTimeoutMillis()).isEqualTo(configuration.getReadTimeoutMillis());
    }
}