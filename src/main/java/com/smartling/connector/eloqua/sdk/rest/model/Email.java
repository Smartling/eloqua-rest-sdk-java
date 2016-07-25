package com.smartling.connector.eloqua.sdk.rest.model;

public class Email
{
    private String type;
    private String currentStatus;
    private int id;
    private long createdAt;
    private int createdBy;
    private String depth; //minimal / partial / complete
    private int folderId;
    private String name;
    private String permissions; //read / write / fullControl
    private long updatedAt;
    private int updatedBy;
    private String bounceBackEmail;
    private int emailFooterId;
    private int emailGroupId;
    private int emailHeaderId;
    private int encodingId;
    private HtmlContent htmlContent;
    private boolean isPlainTextEditable;
    private boolean isTracked;
    private String subject;
    private String layout;
    private String plainText;
    private String replyToEmail;
    private String replyToName;
    private boolean sendPlainTextOnly;
    private String senderEmail;
    private String senderName;
    private String style;

    public int getId()
    {
        return id;
    }

    public long getUpdatedAt()
    {
        return updatedAt;
    }

    public String getCurrentStatus()
    {
        return currentStatus;
    }

    public String getName()
    {
        return name;
    }

    public int getFolderId()
    {
        return folderId;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setFolderId(final int folderId)
    {
        this.folderId = folderId;
    }

    public void setUpdatedAt(final long updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public void setCurrentStatus(final String currentStatus)
    {
        this.currentStatus = currentStatus;
    }

    public HtmlContent getHtmlContent()
    {
        return htmlContent;
    }

    public void setHtmlContent(final HtmlContent htmlContent)
    {
        this.htmlContent = htmlContent;
    }
}
