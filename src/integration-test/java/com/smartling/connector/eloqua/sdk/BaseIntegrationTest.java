package com.smartling.connector.eloqua.sdk;

import org.junit.Before;

import static org.junit.Assume.assumeNotNull;

public class BaseIntegrationTest
{
    public static final String POSTFIX = "(test)";
    public static final String HTML = "<body>Test</body>";
    protected Configuration configuration;

    protected String siteName;
    protected String username;

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
}
