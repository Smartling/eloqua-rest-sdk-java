package com.smartling.connector.eloqua.sdk.rest.model.login;

public class AccountInfo {
	public Site site;
    public ApiAccount user;
    public Urls urls;

    public String getBaseUrl()
    {
        return urls.base;
    }

}
