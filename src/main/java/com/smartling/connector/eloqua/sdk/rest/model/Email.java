package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.smartling.connector.eloqua.sdk.rest.model.dynamicContent.DynamicContent;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class Email
{
    private static final String DRAFT = "Draft";

    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String type;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String currentStatus;
    private Long id;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private long createdAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer createdBy;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String depth; //minimal / partial / complete
    private Long folderId;
    private String name;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private List<String> permissions;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Calendar updatedAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer updatedBy;
    private String bounceBackEmail;
    private Integer emailFooterId;
    private Integer emailGroupId;
    private Integer emailHeaderId;
    private Integer encodingId;
    private HtmlContent htmlContent;
    @JsonProperty("isPlainTextEditable")
    private boolean plainTextEditable;
    @JsonProperty("isTracked")
    private boolean tracked;
    private String subject;
    private String layout;
    private String plainText;
    private String replyToEmail;
    private String replyToName;
    private boolean sendPlainTextOnly;
    private String senderEmail;
    private String senderName;
    private String style;
    private Collection<DynamicContent> dynamicContents;
    private String previewText;

    public Long getId()
    {
        return id;
    }

    public Calendar getUpdatedAt()
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

    public Long getFolderId()
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

    public void setFolderId(final Long folderId)
    {
        this.folderId = folderId;
    }

    @JsonDeserialize (using = CustomJsonCalendarDeserializer.class)
    public void setUpdatedAt(final Calendar updatedAt)
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

    public List<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(final List<String> permissions)
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
        return plainTextEditable;
    }

    public void setPlainTextEditable(final boolean plainTextEditable)
    {
        this.plainTextEditable = plainTextEditable;
    }

    public boolean isTracked()
    {
        return tracked;
    }

    public void setTracked(final boolean tracked)
    {
        this.tracked = tracked;
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

    public Collection<DynamicContent> getDynamicContents()
    {
        return dynamicContents;
    }

    public void setDynamicContents(Collection<DynamicContent> dynamicContents)
    {
        this.dynamicContents = dynamicContents;
    }

    public String getPreviewText()
    {
        return previewText;
    }

    public void setPreviewText(String previewText)
    {
        this.previewText = previewText;
    }
}
