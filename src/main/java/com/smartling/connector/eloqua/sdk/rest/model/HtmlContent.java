package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class HtmlContent
{
    public static final String RAW_HTML_CONTENT = "RawHtmlContent";
    public static final String STRUCTURED_HTML_CONTENT = "StructuredHtmlContent";
    public static final String CONTENT_SOURCE_EDITOR = "editor";
    public static final String CONTENT_SOURCE_UPLOAD = "upload";
    private String type;
    private String docType;
    private String htmlBody;
    private List<String> metaTags;
    private String root;
    private String cssHeader;
    private String systemHeader;
    private String html;
    private String contentSource;

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

    public String getCssHeader()
    {
        return cssHeader;
    }

    public void setCssHeader(final String cssHeader)
    {
        this.cssHeader = cssHeader;
    }

    public String getContentSource()
    {
        return contentSource;
    }

    public void setContentSource(final String contentSource)
    {
        this.contentSource = contentSource;
    }

    @JsonIgnore
    public String getPlainHtml()
    {
        return STRUCTURED_HTML_CONTENT.equals(type) ? getMergedRawHtmlString() : html;
    }

    private String getMergedRawHtmlString()
    {
        StringBuilder merged = new StringBuilder();
        if (systemHeader != null)
        {
            merged.append(systemHeader);
        }
        if (cssHeader != null)
        {
            merged.append(cssHeader);
        }
        if (htmlBody != null)
        {
            merged.append(htmlBody);
        }
        return merged.toString();
    }

    @JsonIgnore
    public void setPlainHtml(String html)
    {
        if (STRUCTURED_HTML_CONTENT.equals(type))
        {
            htmlBody = html;
            this.contentSource = CONTENT_SOURCE_EDITOR;
        } else {
            this.html = html;
            this.contentSource = CONTENT_SOURCE_UPLOAD;
        }
    }

//    @JsonIgnore
//    public String getHtmlDirectivePath()
//    {
//        return STRUCTURED_HTML_CONTENT.equals(type) ? "/htmlContent/htmlBody" : "/htmlContent/html";
//    }
}
