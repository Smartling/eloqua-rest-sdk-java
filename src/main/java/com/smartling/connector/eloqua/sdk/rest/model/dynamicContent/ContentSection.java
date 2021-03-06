package com.smartling.connector.eloqua.sdk.rest.model.dynamicContent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.smartling.connector.eloqua.sdk.rest.model.CustomJsonCalendarDeserializer;

import java.util.Calendar;
import java.util.List;

public class ContentSection
{
    private String type;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private Calendar createdAt;
    private Integer createdBy;
    private String depth;
    private Long folderId;
    private String name;
    private Calendar updatedAt;
    private Integer updatedBy;
    private String contentHtml;
    private String contentText;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private List<Object> forms;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private List<Object> hyperlinks;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private List<Object> images;
    private String scope;
    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private Size size;

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
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

    public Long getFolderId()
    {
        return folderId;
    }

    public void setFolderId(final Long folderId)
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

    public String getContentHtml()
    {
        return contentHtml;
    }

    public void setContentHtml(final String contentHtml)
    {
        this.contentHtml = contentHtml;
    }

    public String getContentText()
    {
        return contentText;
    }

    public void setContentText(final String contentText)
    {
        this.contentText = contentText;
    }

    public List<Object> getForms()
    {
        return forms;
    }

    public void setForms(final List<Object> forms)
    {
        this.forms = forms;
    }

    public List<Object> getHyperlinks()
    {
        return hyperlinks;
    }

    public void setHyperlinks(final List<Object> hyperlinks)
    {
        this.hyperlinks = hyperlinks;
    }

    public List<Object> getImages()
    {
        return images;
    }

    public void setImages(final List<Object> images)
    {
        this.images = images;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(final String scope)
    {
        this.scope = scope;
    }

    public Size getSize()
    {
        return size;
    }

    public void setSize(final Size size)
    {
        this.size = size;
    }
}
