package com.smartling.connector.eloqua.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Calendar;

public class LandingPage
{
    private String type;
    private String currentStatus;
    private Long id;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Calendar createdAt;
    private String depth;
    private Integer folderId;
    private String name;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String permissions;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Calendar updatedAt;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Integer updatedBy;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Calendar deployedAt;
    private HtmlContent htmlContent;
    private String layout;
    private Integer micrositeId;
    private Calendar refreshedAt;
    private String style;

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public String getCurrentStatus()
    {
        return currentStatus;
    }

    public void setCurrentStatus(final String currentStatus)
    {
        this.currentStatus = currentStatus;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public Calendar getCreatedAt()
    {
        return createdAt;
    }

    @JsonDeserialize (using = CustomJsonCalendarDeserializer.class)
    public void setCreatedAt(final Calendar createdAt)
    {
        this.createdAt = createdAt;
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

    public String getPermissions()
    {
        return permissions;
    }

    public void setPermissions(final String permissions)
    {
        this.permissions = permissions;
    }

    public Calendar getUpdatedAt()
    {
        return updatedAt;
    }

    @JsonDeserialize (using = CustomJsonCalendarDeserializer.class)
    public void setUpdatedAt(final Calendar updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy()
    {
        return updatedBy;
    }

    public void setUpdatedBy(final Integer updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    @JsonDeserialize (using = CustomJsonCalendarDeserializer.class)
    public Calendar getDeployedAt()
    {
        return deployedAt;
    }

    public void setDeployedAt(final Calendar deployedAt)
    {
        this.deployedAt = deployedAt;
    }

    public HtmlContent getHtmlContent()
    {
        return htmlContent;
    }

    public void setHtmlContent(final HtmlContent htmlContent)
    {
        this.htmlContent = htmlContent;
    }

    public String getLayout()
    {
        return layout;
    }

    public void setLayout(final String layout)
    {
        this.layout = layout;
    }

    public Integer getMicrositeId()
    {
        return micrositeId;
    }

    public void setMicrositeId(final Integer micrositeId)
    {
        this.micrositeId = micrositeId;
    }

    public Calendar getRefreshedAt()
    {
        return refreshedAt;
    }

    @JsonDeserialize (using = CustomJsonCalendarDeserializer.class)
    public void setRefreshedAt(final Calendar refreshedAt)
    {
        this.refreshedAt = refreshedAt;
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
