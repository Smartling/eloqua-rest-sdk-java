package com.smartling.connector.eloqua.sdk.rest.model;

import java.util.List;

public class HtmlContent
{
    private String type;
    private String docType;
    private String htmlBody;
    private List<String> metaTags;
    private String root;
    private String systemHeader;

    public String getHtmlBody()
    {
        return htmlBody;
    }

    public void setHtmlBody(final String htmlBody)
    {
        this.htmlBody = htmlBody;
    }
}
