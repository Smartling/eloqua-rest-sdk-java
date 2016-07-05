package com.smartling.connector.eloqua.sdk;

import java.util.Base64;

public class Configuration
{
    private final String siteName;
    private final String username;
    private final String password;

    public Configuration(final String siteName, final String username, final String password)
    {
        this.siteName = siteName;
        this.username = username;
        this.password = password;
    }

    public String getLoginEncoded()
    {
        return "Basic " + new String(Base64.getEncoder().encode((siteName + '\\' + username + ':' + password).getBytes()));
    }
}
