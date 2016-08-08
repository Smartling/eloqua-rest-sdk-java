package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;

public class Email
{
    public static final String DRAFT = "Draft";
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String type;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String currentStatus;
    private Long id;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    /*
        It's timestamp in seconds totaly not Java style be aware
     */
    private long createdAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer createdBy;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String depth; //minimal / partial / complete
    private Integer folderId;
    private String name;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String permissions; //read / write / fullControl
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    /*
        It's timestamp in seconds totaly not Java style be aware
     */
    private long updatedAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer updatedBy;
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

    public Long getId()
    {
        return id;
    }

    @Deprecated
    public long getUpdatedAt()
    {
        return updatedAt;
    }

    public Calendar getUpdatedAtAsCalendar()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(createdAt*1000);
        return calendar;
    }

    public String getCurrentStatus()
    {
        return currentStatus;
    }

    public String getName()
    {
        return name;
    }

    public Integer getFolderId()
    {
        return folderId;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setFolderId(final Integer folderId)
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

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    @Deprecated
    public long getCreatedAt()
    {
        return createdAt;
    }

    public Calendar getCreatedAtAsCalendar()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(updatedAt*1000);
        return calendar;
    }

    public void setCreatedAt(final long createdAt)
    {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(final Integer createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getDepth()
    {
        return depth;
    }

    public void setDepth(final String depth)
    {
        this.depth = depth;
    }

    public String getPermissions()
    {
        return permissions;
    }

    public void setPermissions(final String permissions)
    {
        this.permissions = permissions;
    }

    public Integer getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(final Integer updatedBy)
    {
        this.updatedBy = updatedBy;
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

    public Boolean isDraft()
    {
        return DRAFT.equalsIgnoreCase(currentStatus);
    }
}
