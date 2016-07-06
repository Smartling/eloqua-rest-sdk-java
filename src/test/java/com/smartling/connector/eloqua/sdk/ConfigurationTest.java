package com.smartling.connector.eloqua.sdk;

import org.junit.Before;
import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.assertEquals;

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
        assertEquals(expected, configuration.getLoginEncoded());
    }

    @Test
    public void testGetOptions() throws Exception
    {
        assertEquals(configuration.getConnectTimeoutMillis(), configuration.getOptions().connectTimeoutMillis());
        assertEquals(configuration.getReadTimeoutMillis(), configuration.getOptions().readTimeoutMillis());

    }
}