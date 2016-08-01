package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class HtmlContent
{
    public static final String RAW_HTML_CONTENT = "RawHtmlContent";
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

    public String getDocType()
    {
        return docType;
    }

    public void setDocType(final String docType)
    {
        this.docType = docType;
    }

    public List<String> getMetaTags()
    {
        return metaTags;
    }

    public void setMetaTags(final List<String> metaTags)
    {
        this.metaTags = metaTags;
    }

    public String getRoot()
    {
        return root;
    }

    public void setRoot(final String root)
    {
        this.root = root;
    }

    public String getSystemHeader()
    {
        return systemHeader;
    }

    public void setSystemHeader(final String systemHeader)
    {
        this.systemHeader = systemHeader;
    }

    @JsonIgnore
    public String getPlainHtml()
    {
        return STRUCTURED_HTML_CONTENT.equals(type) ? htmlBody : html;
    }

    @JsonIgnore
    public void setPlainHtml(String html)
    {
        if (STRUCTURED_HTML_CONTENT.equals(type))
        {
            htmlBody = html;
        } else {
            this.html = html;
        }
    }

    @JsonIgnore
    public String getHtmlDirectivePath()
    {
        return STRUCTURED_HTML_CONTENT.equals(type) ? "/htmlContent/htmlBody" : "/htmlContent/html";
    }
}
