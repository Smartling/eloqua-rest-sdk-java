package com.smartling.connector.eloqua.sdk.rest.model;

import java.util.List;

public class HtmlContent
{
    public static final String STRUCTURED_HTML_CONTENT = "StructuredHtmlContent";
    private String type;
    private String docType;
    private String htmlBody;
    private List<String> metaTags;
    private String root;
    private String systemHeader;
    private String html;

    public String getHtmlBody()
    {
        return htmlBody;
    }

    public void setHtmlBody(final String htmlBody)
    {
        this.htmlBody = htmlBody;
    }

    public String getHtml()
    {
        return html;
    }

    public void setHtml(final String html)
    {
        this.html = html;
    }

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public String getPlainHtml()
    {
        if (STRUCTURED_HTML_CONTENT.equals(type))
        {
            return htmlBody;
        } else {
            return html;
        }
    }
}
