package com.smartling.connector.eloqua.sdk.rest.model;

public class EmailDtoForCreation
{
    private String depth; //minimal / partial / complete
    private Integer folderId;
    private String name;
    private String bounceBackEmail;
    private Integer emailFooterId;
    private Integer emailGroupId;
    private Integer emailHeaderId;
    private Integer encodingId;
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

    public EmailDtoForCreation(Email email)
    {
        this.depth = email.getDepth();
        this.folderId = email.getFolderId();
        this.name = email.getName();
        this.bounceBackEmail = email.getBounceBackEmail();
        this.emailFooterId = email.getEmailFooterId();
        this.emailGroupId = email.getEmailGroupId();
        this.emailHeaderId = email.getEmailHeaderId();
        this.encodingId = email.getEncodingId();
        this.htmlContent = email.getHtmlContent();
        this.isPlainTextEditable = email.isPlainTextEditable();
        this.isTracked = email.isTracked();
        this.subject = email.getSubject();
        this.layout = email.getLayout();
        this.plainText = email.getPlainText();
        this.replyToEmail = email.getReplyToEmail();
        this.replyToName = email.getReplyToName();
        this.sendPlainTextOnly = email.isSendPlainTextOnly();
        this.senderEmail = email.getSenderEmail();
        this.senderName = email.getSenderName();
        this.style = email.getStyle();
    }

    public String getDepth()
    {
        return depth;
    }

    public void setDepth(final String depth)
    {
        this.depth = depth;
    }

    public Integer getFolderId()
    {
        return folderId;
    }

    public void setFolderId(final Integer folderId)
    {
        this.folderId = folderId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getBounceBackEmail()
    {
        return bounceBackEmail;
    }

    public void setBounceBackEmail(final String bounceBackEmail)
    {
        this.bounceBackEmail = bounceBackEmail;
    }

    public Integer getEmailFooterId()
    {
        return emailFooterId;
    }

    public void setEmailFooterId(final Integer emailFooterId)
    {
        this.emailFooterId = emailFooterId;
    }

    public Integer getEmailGroupId()
    {
        return emailGroupId;
    }

    public void setEmailGroupId(final Integer emailGroupId)
    {
        this.emailGroupId = emailGroupId;
    }

    public Integer getEmailHeaderId()
    {
        return emailHeaderId;
    }

    public void setEmailHeaderId(final Integer emailHeaderId)
    {
        this.emailHeaderId = emailHeaderId;
    }

    public Integer getEncodingId()
    {
        return encodingId;
    }

    public void setEncodingId(final Integer encodingId)
    {
        this.encodingId = encodingId;
    }

    public HtmlContent getHtmlContent()
    {
        return htmlContent;
    }

    public void setHtmlContent(final HtmlContent htmlContent)
    {
        this.htmlContent = htmlContent;
    }

    public boolean isPlainTextEditable()
    {
        return isPlainTextEditable;
    }

    public void setIsPlainTextEditable(final boolean isPlainTextEditable)
    {
        this.isPlainTextEditable = isPlainTextEditable;
    }

    public boolean isTracked()
    {
        return isTracked;
    }

    public void setIsTracked(final boolean isTracked)
    {
        this.isTracked = isTracked;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(final String subject)
    {
        this.subject = subject;
    }

    public String getLayout()
    {
        return layout;
    }

    public void setLayout(final String layout)
    {
        this.layout = layout;
    }

    public String getPlainText()
    {
        return plainText;
    }

    public void setPlainText(final String plainText)
    {
        this.plainText = plainText;
    }

    public String getReplyToEmail()
    {
        return replyToEmail;
    }

    public void setReplyToEmail(final String replyToEmail)
    {
        this.replyToEmail = replyToEmail;
    }

    public String getReplyToName()
    {
        return replyToName;
    }

    public void setReplyToName(final String replyToName)
    {
        this.replyToName = replyToName;
    }

    public boolean isSendPlainTextOnly()
    {
        return sendPlainTextOnly;
    }

    public void setSendPlainTextOnly(final boolean sendPlainTextOnly)
    {
        this.sendPlainTextOnly = sendPlainTextOnly;
    }

    public String getSenderEmail()
    {
        return senderEmail;
    }

    public void setSenderEmail(final String senderEmail)
    {
        this.senderEmail = senderEmail;
    }

    public String getSenderName()
    {
        return senderName;
    }

    public void setSenderName(final String senderName)
    {
        this.senderName = senderName;
    }

    public String getStyle()
    {
        return style;
    }

    public void setStyle(final String style)
    {
        this.style = style;
    }
}