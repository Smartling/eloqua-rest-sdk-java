package com.smartling.connector.eloqua.sdk.rest.model.login;

public class AccountInfo
{
    private Site site;
    private ApiAccount user;
    private Urls urls;

    public String getBaseUrl()
    {
        return urls.getBase();
    }

    public Urls getUrls()
    {
        return urls;
    }

    public void setUrls(final Urls urls)
    {
        this.urls = urls;
    }
}
